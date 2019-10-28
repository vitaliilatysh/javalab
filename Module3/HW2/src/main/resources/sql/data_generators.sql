-- Marks generator
CREATE OR REPLACE FUNCTION mark_generator(integer) RETURNS SETOF students AS
$BODY$
DECLARE
    student students%rowtype;
	subject subjects%rowtype;
BEGIN
    FOR student IN select * from students limit $1 LOOP
		FOR subject IN select * from subjects LOOP
			if student.id = 1 then
        		insert into exam_results(student_id, subject_id,mark) values (student.id, subject.id, floor(random()*10)+1);
				insert into exam_results(student_id, subject_id,mark) values (student.id, subject.id, floor(random()*10)+1);
			else
				insert into exam_results(student_id, subject_id,mark) values (student.id, subject.id, floor(random()*10)+1);
			end if;
    	END LOOP;
		RETURN NEXT student;
	END LOOP;
    RETURN;
END
$BODY$
LANGUAGE plpgsql;

-- Unique student row generator
CREATE OR REPLACE FUNCTION students_generator(integer) RETURNS SETOF students AS
$BODY$
BEGIN
    insert into students (student_name, surname, date_of_birth, primary_skill, created_datetime) select
        md5(random()::text), md5(random()::text), birthday_generator(), md5(random()::text), current_timestamp
    from generate_series(1, $1);
    RETURN;
END
$BODY$
LANGUAGE plpgsql;

-- Subjects generator
CREATE OR REPLACE FUNCTION subjects_generator(integer) RETURNS void AS
$BODY$
BEGIN
    insert into subjects (subject_name, tutor) select
        md5(random()::text), md5(random()::text)
    from generate_series(1, $1);
    RETURN;
END
$BODY$
LANGUAGE plpgsql;
