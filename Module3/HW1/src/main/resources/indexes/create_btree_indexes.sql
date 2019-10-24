drop index if exists idx_student_name_surname,idx_subject_name,idx_phone_number;

CREATE UNIQUE INDEX idx_student_name_surname ON students (student_name, surname);
SELECT pg_size_pretty (pg_indexes_size('students'));

CREATE UNIQUE INDEX idx_subject_name ON subjects (subject_name);
SELECT pg_size_pretty (pg_indexes_size('subjects'));

CREATE UNIQUE index idx_phone_number ON phones (phone_number);
SELECT pg_size_pretty (pg_indexes_size('phones'));