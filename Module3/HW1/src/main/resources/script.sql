create table students(
   id                   integer primary  key    not null,
   student_name         text                    not null,
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

update students set student_name = 'Pavel' where  students.id = 123;

update students set student_name = 'Daniel' where  id = 124;

delete  from students;

select * from students;

CREATE OR REPLACE FUNCTION log_last_name_changes()
  RETURNS trigger AS
$BODY$
BEGIN
	IF old.student_name <> new.student_name OR
	old.surname <> new.surname OR
	old.date_of_birth <> new.date_of_birth OR
	old.primary_skill <> new.primary_skill THEN
   UPDATE students SET updated_datetime = now() where id=new.id or
   student_name=new.student_name or
   surname=new.surname or
   date_of_birth=new.date_of_birth or
   primary_skill=new.primary_skill;
   end if;
   RETURN NEW;
END;
$BODY$
LANGUAGE PLPGSQL;

CREATE TRIGGER last_name_changes
    AFTER UPDATE
    ON students
	for each row
    EXECUTE PROCEDURE log_last_name_changes();

