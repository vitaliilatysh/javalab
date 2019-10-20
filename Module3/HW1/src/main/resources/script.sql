-- 4. Triggers
SELECT * FROM students_generator();
SELECT * FROM phone_number_generator();
SELECT * FROM subjects_generator();

-- B-tree index
CREATE EXTENSION pg_trgm;

-- Find user by name (exact match)
explain analyze
select student_name from students where student_name='377f9452ca2d629521c9e24aad903519'

-- Find user by surname (partial match)
explain analyze
select student_name from students where student_name like '%a74a%';

-- Find user by phone number (partial match)
explain analyze
select student_name, phone_number from students inner join phones on students.id = phones.student_id
where CAST(phones.phone_number AS TEXT) LIKE '%9999%';
------------------------------------------------------------------------------------------------------------------------
-- 5. check the trigger for updated_datetime set when student row is updated
update students set student_name = 'Donn' where  id = 1;
select * from students;

-- 6. check student name validation (#,$,@)
update students set student_name = 'Donn#' where  id = 1;

-- 7. making snapshot when update
update students set student_name = 'Donnio' where  id = 1;
select * from students_snapshots;

-- 8. get average mark for student with specified id
select * from avg_student_mark(1);

-- 9. get average mark for specified subject name
select * from avg_subject_mark('Algorithms');

-- 10. get all students with average points less then 75
Select * from students_at_redline();

