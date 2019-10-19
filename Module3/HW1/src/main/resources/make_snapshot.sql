drop trigger if exists make_snapshot on students;

CREATE OR REPLACE FUNCTION make_snapshot()
  RETURNS trigger AS
$BODY$
BEGIN
    insert into students_snapshots (student_name, student_surname, subject_name, mark) select students.student_name, students.surname, subjects.subject_name, exam_results.mark from students
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