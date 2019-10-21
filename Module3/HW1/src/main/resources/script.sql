-- 4. Indexes

-- B-tree index extension
CREATE EXTENSION pg_trgm;

-- Auto fill

-- 100K students
SELECT * FROM students_generator();
-- 100K phone numbers
SELECT * FROM phone_number_generator();
-- 1K subjects
SELECT * FROM subjects_generator();
-- 1M marks
select * from mark_generator();

-- Find user by name (exact match)
explain analyze
select * from students where student_name='377f9452ca2d629521c9e24aad903519'

-- Find user by surname (partial match)
explain analyze
select * from students where surname like '%a74a%';

-- Find user by phone number (partial match)
explain analyze
select students.student_name, phones.phone_number from students inner join phones on students.id = phones.student_id
where phones.phone_number LIKE '%9999%';

-- Find user with marks by user surname (partial match)
explain analyze
select * from students inner join exam_results on students.id = exam_results.student_id
where students.surname LIKE '%2343%';

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

