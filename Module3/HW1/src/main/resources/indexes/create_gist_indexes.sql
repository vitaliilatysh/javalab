drop index if exists idx_gist_student_name,idx_gist_student_surname,idx_gist_subject_name,idx_gist_phone_number;

CREATE INDEX idx_gist_student_name ON students USING gist (student_name gist_trgm_ops);
CREATE INDEX idx_gist_student_surname ON students USING gist (surname gist_trgm_ops);
SELECT pg_size_pretty (pg_indexes_size('students'));

CREATE INDEX idx_gist_subject_name ON subjects USING gist (subject_name gist_trgm_ops);
SELECT pg_size_pretty (pg_indexes_size('subjects'));

 CREATE INDEX idx_gist_phone_number ON phones USING gist (phone_number gist_trgm_ops);
 SELECT pg_size_pretty (pg_indexes_size('phones'));