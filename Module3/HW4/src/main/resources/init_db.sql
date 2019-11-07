drop table if exists employee, unit,address, employee_status,project,employee_project,personal cascade;

create table unit(
    id                  serial  primary key     not null,
    unit_name           text
);

create table employee(
   id                   serial  primary  key    not null,
   employee_name        text                    not null,
   surname              text                    not null,
   unit_id         		integer 				references unit(id) on delete cascade on update cascade
);

create table address(
    id                  serial  primary key     not null ,
    city		        text,
    employee_id         integer                 references employee(id) on delete cascade on update cascade
);

create table employee_status(
   id                   serial  primary  key    not null,
   status		        text                    not null,
   employee_id         	integer unique          references employee(id) on delete cascade on update cascade
);

create table project(
   id                   serial  primary key     not null,
   project_name         text
);

create table employee_project(
	id 					serial primary key		not null,
	employee_id			integer					references employee(id) on delete cascade on update cascade,
	project_id			integer					references project(id) on delete cascade on update cascade
);

create table personal(
    id                  serial  primary key     not null,
    birthdate        	date,
	hiredate     		date,
    firedate        	date,
    employee_id         integer unique          references employee(id) on delete cascade on update cascade
);