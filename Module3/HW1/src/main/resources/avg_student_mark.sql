CREATE OR REPLACE FUNCTION avg_student_mark(integer) RETURNS float
     AS $$ select avg(mark)::float from exam_results where student_id=$1; $$
     LANGUAGE SQL;