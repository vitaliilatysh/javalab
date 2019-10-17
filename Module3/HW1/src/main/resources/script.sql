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
)

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

insert into students values (123, 'David', 'Craig', '1988-12-01', 'Java', current_timestamp);

insert into students values (124, 'Bartosh', 'Kurek', '1988-10-05', 'Volleyball', current_timestamp);

update students set student_name = 'Pavel' where  id = 123;

update students set student_name = 'Daniel' where  id = 124;

delete  from students;

select * from students;

CREATE OR REPLACE FUNCTION log_last_update()
  RETURNS trigger AS
$BODY$
BEGIN
   New.updated_datetime = now();
   RETURN NEW;
END;
$BODY$
LANGUAGE PLPGSQL;

drop trigger if exists last_row_update on students;

CREATE TRIGGER last_row_update
    BEFORE UPDATE
    ON students
	for each row
	when (OLD IS DISTINCT FROM NEW)
    EXECUTE PROCEDURE public.log_last_update();

