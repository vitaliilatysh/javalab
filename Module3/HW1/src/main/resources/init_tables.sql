drop table if exists exam_results, subjects, phones,students,students_snapshots,student_address,log_student_address cascade;

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
    id                  serial  primary key     not null ,
    phone_number        text,
    student_id          integer                 references students(id) on delete cascade on update cascade
);

create table subjects(
   id                   serial  primary  key    not null,
   subject_name         text                    not null,
   tutor                text                    not null
);

create table exam_results(
   id                   serial  primary key     not null,
   student_id           integer                 references students(id) on delete cascade on update cascade,
   subject_id           integer                 references subjects(id) on delete cascade on update cascade,
   mark                 integer
);

create table students_snapshots(
    id                  serial  primary key     not null,
    student_name        text,
    student_surname     text,
    subject_name        text,
    mark                integer
);

create table student_address(
    id                  serial  primary key     not null,
    town                text,
    street              text,
    student_id          integer references students(id) on delete cascade on update cascade
);

create table log_student_address(
    id                  serial  primary key     not null,
    town                text,
    street              text,
    student_id          integer
);