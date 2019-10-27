insert into students (student_name, surname,date_of_birth, primary_skill, created_datetime) values
                            ('David', 'Craig', '1988-12-01', 'Java', current_timestamp),
                            ('Daniel', 'Camp', '1996-11-05', 'PHP', current_timestamp),
                            ('Mark', 'Jader', '1994-07-28', 'SQL', current_timestamp),
                            ('Antonio', 'M.', '1988-12-01', 'Java', current_timestamp),
                            ('Pavlo', 'M', '1988-12-01', 'Java', current_timestamp),
                            ('Ievgen', '', '1988-12-01', 'Java', current_timestamp),
                            ('Derek', '.', '1988-12-01', 'Java', current_timestamp),
                            ('Camel', 'Chimp', '1999-01-05', 'C++', current_timestamp);

insert into subjects (subject_name, tutor) values   ('Algorithms', 'Barber'),
                                                    ('Programming basics', 'Drain'),
                                                    ('Data science', 'Pedro'),
                                                    ('Data structures', 'Martin');


insert into exam_results (student_id,subject_id, passed_date, mark) values    (1, 1,'2019-12-01', 2),
                                                                (1, 1,'2019-12-11', 8),
                                                                (1, 1,'2019-12-20',10),
                                                                (1, 2,'2019-11-01', 2),
                                                                (1, 4,'2019-10-15', 3),
                                                                (1, 3,'2019-10-01', 8),
                                                                (2, 1,'2019-12-01', 8),
                                                                (2, 2,'2019-11-01',10),
                                                                (2, 3,'2019-10-01', 2),
                                                                (3, 1,'2019-12-01', 2),
                                                                (3, 1,'2019-12-11', 8),
                                                                (3, 2,'2019-11-01', 2),
                                                                (3, 3,'2019-10-01', 2),
                                                                (4, 1,'2019-12-01', 8),
                                                                (4, 4,'2019-10-15', 8),
                                                                (4, 2,'2019-11-01', 8),
                                                                (5, 1,'2019-12-11', 5),
                                                                (5, 4,'2019-10-15', 7),
                                                                (5, 4,'2019-10-20', 7),
                                                                (6, 1,'2019-12-11', 5),
                                                                (6, 3,'2019-12-20', 10),
                                                                (6, 4,'2019-10-15', 5),
                                                                (6, 4,'2019-11-15', 10);
