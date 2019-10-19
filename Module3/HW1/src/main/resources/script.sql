-- 5. check the trigger for updated_datetime set when student row is updated
update students set student_name = 'Donn' where  id = 123;
select * from students;

-- 6. check student name validation (#,$,@)
update students set student_name = 'Donn#' where  id = 123;

-- 7. making snapshot when update
update students set student_name = 'Donnio' where  id = 123;
select * from students_snapshots;

-- 8. get average mark for student with specified id
select * from avg_student_mark(123);

-- 9. get average mark for specified subject name
select * from avg_subject_mark('Algorithms');

-- 10. get all students with average points less then 75
Select * from students_at_redline();

