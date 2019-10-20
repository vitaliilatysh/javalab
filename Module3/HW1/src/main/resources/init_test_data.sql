insert into students (student_name, surname,date_of_birth, primary_skill, created_datetime) values
                            ('David', 'Craig', '1988-12-01', 'Java', current_timestamp),
                            ('Daniel', 'Camp', '1996-11-05', 'PHP', current_timestamp),
                            ('Camel', 'Chimp', '1999-01-05', 'C++', current_timestamp);

insert into subjects (subject_name, tutor) values   ('Algorithms', 'Barber'),
                                                    ('Programming basics', 'Drain'),
                                                    ('Data structures', 'Martin');


insert into exam_results values (1, 1, 2),
                                (1, 2, 2),
                                (1, 3, 8),
                                (2, 1, 8),
                                (2, 2, 10),
                                (2, 3, 2),
                                (3, 1, 2),
                                (3, 2, 2),
                                (3, 3, 2);