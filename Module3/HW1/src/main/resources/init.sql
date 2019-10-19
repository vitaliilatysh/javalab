drop table if exists exam_results, subjects, phones,students;

create table students(
   id                   integer primary  key    not null,
   student_name         text                    not null check (student_name ~ '^[a-zA-Z0-9 ]*$'),
   surname              text                    not null,
   date_of_birth        date                    not null,
   primary_skill        text,
   created_datetime     timestamp               WITH TIME ZONE DEFAULT current_timestamp,
   updated_datetime     timestamp               WITH TIME ZONE

);

create table phones(
    phone_number        text primary key        not null,
    student_id          integer                 references students(id) on delete cascade on update cascade
);

create table subjects(
   id                   integer primary  key    not null,
   subject_name         text                    not null,
   tutor                text                    not null
);

create table exam_results(
   student_id           integer                 references students(id) on delete cascade on update cascade,
   subject_id           integer                 references subjects(id) on delete cascade on update cascade,
   mark                 integer,
   PRIMARY KEY (student_id, subject_id)
);

insert into students values (123, 'David', 'Craig', '1988-12-01', 'Java', current_timestamp),
                            (124, 'Daniel', 'Camp', '1996-11-05', 'PHP', current_timestamp),
                            (125, 'Camel', 'Chimp', '1999-01-05', 'C++', current_timestamp);

insert into subjects values (234, 'Algorithms', 'Barber'),
                            (235, 'Programming basics', 'Drain');

insert into exam_results values (123, 234, 80),
                                (123, 235, 75),
                                (124, 235, 93),
                                (125, 234, 65),
                                (124, 234, 100);