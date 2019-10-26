-- 1. Select all primary skills that contain more than one word
-- (please note that both ‘-‘ and ‘ ’ could be used as a separator)

select * from students where array_length(regexp_split_to_array(primary_skill, '[\s|-]'),1) > 1;

------------------------------------------------------------------------------------------------------------------------
-- 2. Select all students who does not have second name
-- (it is absent or consists from only one letter/letter with dot)

select * from students where char_length(surname) in (0,1) or surname ~ '\w\.';

------------------------------------------------------------------------------------------------------------------------
-- 3. Select number of students passed exams for each subject and order result by number of student descending.

select subjects.subject_name, count(students.student_name) as "how much passed" from subjects
	inner join exam_results on subjects.id=exam_results.subject_id
	inner join students on students.id=exam_results.student_id
	group by subjects.subject_name order by "how much passed" desc

------------------------------------------------------------------------------------------------------------------------
-- 4. Select number of students with the same exam marks for each subject

select subjects.subject_name, count(distinct exam_results.mark) as "how much have same mark" from subjects
	inner join exam_results on subjects.id=exam_results.subject_id
	inner join students on students.id=exam_results.student_id
	group by subjects.subject_name order by "how much have same mark" desc

------------------------------------------------------------------------------------------------------------------------
-- 5. Select students who passed at least two exams for different subject

select students.student_name, count(distinct subjects.subject_name) from subjects
	inner join exam_results on subjects.id=exam_results.subject_id
	inner join students on students.id=exam_results.student_id
	group by students.student_name having count(distinct subjects.subject_name) > 1

------------------------------------------------------------------------------------------------------------------------
-- 6. Select students who passed at least two exams for the same subject

select students.student_name, subjects.subject_name, count(exam_results.mark) as "exams passed per subject" from subjects
	inner join exam_results on subjects.id=exam_results.subject_id
	inner join students on students.id=exam_results.student_id
	group by students.student_name,subjects.subject_name having count(exam_results.mark) > 1

------------------------------------------------------------------------------------------------------------------------
-- 7. Select all subjects which exams passed only students with the same primary skills

select subjects.subject_name, count(distinct students.primary_skill) as "passed with same prime skill"  from subjects
	inner join exam_results on subjects.id=exam_results.subject_id
	inner join students on students.id=exam_results.student_id
	group by subjects.subject_name having count(distinct students.primary_skill) = 1

------------------------------------------------------------------------------------------------------------------------
-- 8. Select all subjects which exams passed only students with the different primary skills.
-- It means that all students passed the exam for the one subject must have different primary skill.

select subjects.subject_name, count(distinct students.primary_skill) as "passed with same prime skill"  from subjects
	inner join exam_results on subjects.id=exam_results.subject_id
	inner join students on students.id=exam_results.student_id
	group by subjects.subject_name having count(distinct students.primary_skill) > 1

------------------------------------------------------------------------------------------------------------------------
-- 9. Select students who does not pass any exam using each the following operator
-- a. Outer join
select students.student_name, exam_results.mark
from students
	left outer join exam_results on students.id=exam_results.student_id
	where exam_results.mark is null

-- b. Subquery with ‘not in’ clause
select students.student_name
from students
	where students.id not in (select exam_results.student_id from exam_results)

-- c. Subquery with ‘any‘ clause
select students.student_name
from students
	where NOT (students.id = any(select exam_results.student_id from exam_results))

------------------------------------------------------------------------------------------------------------------------
-- 10. Select all students whose average mark is bigger than overall average mark

select students.student_name, TO_CHAR(AVG (exam_results.mark),'FM999999999.00') as "average mark"  from subjects
	inner join exam_results on subjects.id=exam_results.subject_id
	inner join students on students.id=exam_results.student_id
	group by students.student_name having AVG(exam_results.mark) > (
	select AVG (exam_results.mark) from subjects
	inner join exam_results on subjects.id=exam_results.subject_id
	inner join students on students.id=exam_results.student_id)

------------------------------------------------------------------------------------------------------------------------
-- 11. Select top 5 students who passed their last exam better than average students

select distinct on(students.student_name) students.student_name, exam_results.passed_date as "last exam date", exam_results.mark  from subjects
	inner join exam_results on subjects.id=exam_results.subject_id
	inner join students on students.id=exam_results.student_id
		where exam_results.mark > (	select AVG (exam_results.mark) from subjects
									inner join exam_results on subjects.id=exam_results.subject_id
									inner join students on students.id=exam_results.student_id)
		order by students.student_name, exam_results.passed_date desc limit 5

------------------------------------------------------------------------------------------------------------------------
-- 12. Select biggest mark for each student and add text description for the mark (use COALESCE and WHEN operators).
-- a. In case if student has not passed any exam ‘not passed' should be returned.
-- b. If student mark is 1,2,3 – it should be returned as ‘BAD’
-- c. If student mark is 4,5,6 – it should be returned as ‘AVERAGE’
-- d. If student mark is 7,8 – it should be returned as ‘GOOD’
-- e. If student mark is 9,10 – it should be returned as ‘EXCELLENT’

select students.student_name, max(exam_results.mark) as "biggest mark",
	CASE
		WHEN max(exam_results.mark) in (1,2,3) THEN 'BAD'
		WHEN max(exam_results.mark) in (4,5,6) THEN 'AVERAGE'
		WHEN max(exam_results.mark) in (7,8) THEN 'GOOD'
		WHEN max(exam_results.mark) in (9,10) THEN 'EXCELLENT'
		ELSE 'not passed'
	END
from exam_results
	right outer join students on students.id=exam_results.student_id
	group by students.student_name

------------------------------------------------------------------------------------------------------------------------
-- 13. Select number of all marks for each mark type (‘BAD’, ‘AVERAGE’,…)

select count(exam_results.mark),
	CASE
		WHEN exam_results.mark in (1,2,3) THEN 'BAD'
		WHEN exam_results.mark in (4,5,6) THEN 'AVERAGE'
		WHEN exam_results.mark in (7,8) THEN 'GOOD'
		WHEN exam_results.mark in (9,10) THEN 'EXCELLENT'
	END as mark_type
from exam_results group by mark_type