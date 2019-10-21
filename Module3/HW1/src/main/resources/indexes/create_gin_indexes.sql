drop index if exists idx_gin_student_name,idx_gin_student_surname,idx_gin_subject_name,idx_gin_phone_number;

CREATE INDEX idx_gin_student_name ON students USING gin (student_name gin_trgm_ops) WITH (fastupdate = off);
CREATE INDEX idx_gin_student_surname ON students using gin (surname gin_trgm_ops) WITH (fastupdate = off);
SELECT pg_size_pretty (pg_indexes_size('students'));

CREATE INDEX idx_gin_subject_name ON subjects USING gin (subject_name gin_trgm_ops) WITH (fastupdate = off);
SELECT pg_size_pretty (pg_indexes_size('subjects'));

 CREATE INDEX idx_gin_phone_number ON phones USING gin (phone_number gin_trgm_ops) WITH (fastupdate = off);
 SELECT pg_size_pretty (pg_indexes_size('phones'));