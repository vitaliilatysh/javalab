CREATE OR REPLACE FUNCTION avg_subject_mark(text) RETURNS float
     AS $$ select avg(mark)::float from exam_results inner join subjects on subjects.id=exam_results.subject_id where subject_name=$1; $$
     LANGUAGE SQL;