drop index if exists idx_hash_student_name, idx_hash_student_surname,idx_hash_subject_name, idx_hash_phone_number;

CREATE INDEX idx_hash_student_name ON students using hash (student_name);
CREATE INDEX idx_hash_student_surname ON students using hash (surname);
SELECT pg_size_pretty (pg_indexes_size('students'));

CREATE INDEX idx_hash_subject_name ON subjects using hash (subject_name);
SELECT pg_size_pretty (pg_indexes_size('subjects'));

CREATE index idx_hash_phone_number ON phones using hash (phone_number);
SELECT pg_size_pretty (pg_indexes_size('phones'));