-- Functions for working with indexes

-- Subjects generator
CREATE OR REPLACE FUNCTION subjects_generator() RETURNS void AS
$BODY$
BEGIN
    insert into subjects (subject_name, tutor) select
        md5(random()::text), md5(random()::text)
    from generate_series(1, 1000);
    RETURN;
END
$BODY$
LANGUAGE plpgsql;

-- Birthday generator
CREATE OR REPLACE FUNCTION birthday_generator() RETURNS timestamp
     AS $$
            select timestamp '1980-01-10 20:00:00' + random() * (timestamp '2000-01-20 20:00:00' -
                   timestamp '1980-01-10 10:00:00')
        $$
     LANGUAGE SQL;

-- Unique student row generator
CREATE OR REPLACE FUNCTION students_generator() RETURNS SETOF students AS
$BODY$
BEGIN
    insert into students (student_name, surname, date_of_birth, primary_skill, created_datetime) select
        md5(random()::text), md5(random()::text), birthday_generator(), md5(random()::text), current_timestamp
    from generate_series(1, 100000);
    RETURN;
END
$BODY$
LANGUAGE plpgsql;

-- Phone numbers generation for created students

CREATE OR REPLACE FUNCTION pseudo_encrypt(VALUE int) returns int AS $$
DECLARE
l1 int;
l2 int;
r1 int;
r2 int;
i int:=0;
BEGIN
 l1:= (VALUE >> 16) & 65535;
 r1:= VALUE & 65535;
 WHILE i < 3 LOOP
   l2 := r1;
   r2 := l1 # ((((1366 * r1 + 150889) % 714025) / 714025.0) * 32767)::int;
   l1 := l2;
   r1 := r2;
   i := i + 1;
 END LOOP;
 RETURN ((r1 << 16) + l1);
END;
$$ LANGUAGE plpgsql strict immutable;


CREATE OR REPLACE FUNCTION phone_number_generator() RETURNS SETOF students AS
$BODY$
DECLARE
    r students%rowtype;
BEGIN
    FOR r IN
        SELECT * FROM students
    LOOP
        insert into phones (phone_number, student_id) values (pseudo_encrypt(r.id), r.id);
        RETURN NEXT r;
    END LOOP;
    RETURN;
END
$BODY$
LANGUAGE plpgsql;

------------------------------------------------------------------------------------------------------------------------
-- 5. Trigger that will update column updated_datetime to current date in case of updating any of student
drop trigger if exists row_update_trigger on students;

CREATE OR REPLACE FUNCTION set_updated_datetime()
  RETURNS trigger AS
$BODY$
BEGIN
   New.updated_datetime = now();
   RETURN NEW;
END;
$BODY$
LANGUAGE PLPGSQL;

CREATE TRIGGER row_update_trigger
    BEFORE UPDATE
    ON students
	for each row
	when (OLD IS DISTINCT FROM NEW)
    EXECUTE PROCEDURE public.set_updated_datetime();

------------------------------------------------------------------------------------------------------------------------
-- 7. Creation snapshot that will contain next data: student name, student surname, subject name, mark

drop trigger if exists make_snapshot on students;

CREATE OR REPLACE FUNCTION make_snapshot()
  RETURNS trigger AS
$BODY$
BEGIN
    insert into students_snapshots (student_name, student_surname, subject_name, mark)
        select students.student_name, students.surname, subjects.subject_name, exam_results.mark from students
	        inner join exam_results on exam_results.student_id=students.id
	        inner join subjects on exam_results.subject_id=subjects.id
	    where students.id=old.id;
   RETURN NEW;
END;
$BODY$
LANGUAGE PLPGSQL;

CREATE TRIGGER make_snapshot
    BEFORE UPDATE
    ON students
	for each row
	when (OLD IS DISTINCT FROM NEW)
    EXECUTE PROCEDURE public.make_snapshot();

------------------------------------------------------------------------------------------------------------------------
-- 8. Function that will return average mark for input user

CREATE OR REPLACE FUNCTION avg_student_mark(integer) RETURNS float
     AS $$
            select avg(mark)::float from exam_results where student_id=$1;
        $$
     LANGUAGE SQL;

------------------------------------------------------------------------------------------------------------------------
-- 9. Function that will return avarage mark for input subject name

CREATE OR REPLACE FUNCTION avg_subject_mark(text) RETURNS float
     AS $$
            select avg(mark)::float from exam_results
            inner join subjects on subjects.id=exam_results.subject_id where subject_name=$1;
        $$
     LANGUAGE SQL;

------------------------------------------------------------------------------------------------------------------------
-- 10. Function that will return student at "red zone" (red zone means at least 2 marks <=3)

CREATE OR REPLACE FUNCTION students_at_redline()
RETURNS TABLE (student_name text, avarage_mark float)
as
$$
	select students.student_name, count(exam_results.mark)::float from students
	inner join exam_results on exam_results.student_id=students.id
	inner join subjects on exam_results.subject_id=subjects.id
	where exam_results.mark = 2
 	group by students.student_name
 	having count(exam_results.mark)::float >=2
$$
LANGUAGE SQL;