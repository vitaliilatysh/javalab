create table students(
   id                   integer primary  key    not null,
   student_name         text                    not null,
   surname              text                    not null,
   date_of_birth        date                    not null,
   primary_skill        text,
   created_datetime     date,
   updated_datetime     date,
   phone_number         text

);

create table subjects(
   id                   integer primary  key    not null,
   subject_name         text                    not null,
   tutor                text                    not null
);

create table exam_results(
   id                   integer primary  key    not null,
   student_id           integer                 references students(id) on delete cascade on update cascade,
   subject_id           integer                 references subjects(id) on delete cascade on update cascade,
   mark                 integer
);