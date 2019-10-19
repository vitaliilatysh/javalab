drop function if exists students_at_redline();

CREATE OR REPLACE FUNCTION students_at_redline() 
RETURNS TABLE (student_name text, avarage_mark float)
as
$$ 
	select students.student_name, avg(mark)::float from students 
	inner join exam_results on exam_results.student_id=students.id 
	inner join subjects on exam_results.subject_id=subjects.id 
	group by students.student_name
	having avg(mark)::float < 60 
$$
LANGUAGE SQL;