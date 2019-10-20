drop table if exists exam_results, subjects, phones,students,students_snapshots cascade;

create table students(
   id                   serial  primary  key    not null,
   student_name         text                    not null check (student_name ~ '^[a-zA-Z0-9 ]*$'),
   surname              text                    not null,
   date_of_birth        date                    not null,
   primary_skill        text,
   created_datetime     timestamp               WITH TIME ZONE DEFAULT current_timestamp,
   updated_datetime     timestamp               WITH TIME ZONE

);

create table phones(
    phone_number        integer   primary key    not null,
    student_id          integer                 references students(id) on delete cascade on update cascade
);

create table subjects(
   id                   serial  primary  key    not null,
   subject_name         text                    not null,
   tutor                text                    not null
);

create table exam_results(
   student_id           integer                 references students(id) on delete cascade on update cascade,
   subject_id           integer                 references subjects(id) on delete cascade on update cascade,
   mark                 integer,
   PRIMARY KEY (student_id, subject_id)
);

create table students_snapshots(
    id                  serial  primary key     not null,
    student_name        text,
    student_surname     text,
    subject_name        text,
    mark                integer
);