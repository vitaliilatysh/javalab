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
