drop table if exists employee, unit,address, employee_status,project,employee_project,personal_info cascade;

create table unit(
    id                  bigint  primary key auto_increment,
    unit_name           varchar
);

create table employee(
   id                   bigint                     primary key auto_increment,
   employee_name        varchar                    not null,
   surname              varchar                    not null,
   role                 varchar,
   status               varchar                    not null,
   city                 varchar                    not null,
   state                varchar                    not null,
   country              varchar                    not null,
   address_line         varchar                    not null,
   unit_id         		bigint  				   references unit(id) on delete cascade on update cascade
);

create table project(
   id                   bigint                        primary key auto_increment,
   project_name         varchar
);

create table employee_project(
	id 					bigint              		   primary key auto_increment,
	employee_id			bigint 					    references employee(id) on delete cascade on update cascade,
	project_id			bigint 					    references project(id) on delete cascade on update cascade
);

create table personal_info(
    id                  bigint                        primary key auto_increment,
    birthdate        	date,
	hiredate     		date,
    firedate        	date,
    employee_id         bigint  unique                 references employee(id) on delete cascade on update cascade
);