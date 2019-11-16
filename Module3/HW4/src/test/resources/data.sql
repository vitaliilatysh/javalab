delete from Unit;
INSERT INTO Unit(id, unit_name) VALUES (1, 'Java Solutions');
INSERT INTO Unit(id, unit_name) VALUES (2, 'Hybris Department');
INSERT INTO Unit(id, unit_name) VALUES (4, 'Cloud Department');

delete from employee;
INSERT INTO Employee(id, employee_name, surname, status, role, city, state, country, address_line)
        VALUES (12, 'Paulinio', 'Delaver', 'HIRED', 'dev', 'Rio', 'Bercha', 'Brazilia', 'Mineiro,57');
INSERT INTO Employee(id, employee_name, surname, status, role, city, state, country, address_line, unit_id)
        VALUES (13, 'Paulinio', 'Delaver', 'HIRED', 'qa', 'Rio', 'Bercha', 'Brazilia', 'Mineiro,57', 2);
INSERT INTO Employee(id, employee_name, surname, status, role, city, state, country, address_line, unit_id)
        VALUES (14, 'Paulinio', 'Delaver', 'HIRED', 'qa', 'Rio', 'Bercha', 'Brazilia', 'Mineiro,57', 2);
INSERT INTO Employee(id, employee_name, surname, status, role, city, state, country, address_line, unit_id)
        VALUES (15, 'Paulinio', 'Delaver', 'HIRED', 'qa', 'Rio', 'Bercha', 'Brazilia', 'Mineiro,57', 2);

delete from project;
INSERT INTO project(id, project_name) VALUES (11, 'CTO-ORKE-TAB');
INSERT INTO project(id, project_name) VALUES (22, 'HYB-DTO-CTO');
INSERT INTO project(id, project_name) VALUES (43, 'CLOU-MRK-ESS');

delete from personal_info;
INSERT INTO Employee(id, employee_name, surname, status, role, city, state, country, address_line)
        VALUES (20, 'Paulinio', 'Delaver', 'HIRED', 'dev', 'Rio', 'Bercha', 'Brazilia', 'Mineiro,57');
INSERT INTO Employee(id, employee_name, surname, status, role, city, state, country, address_line, unit_id)
        VALUES (21, 'Paulinio', 'Delaver', 'HIRED', 'qa', 'Rio', 'Bercha', 'Brazilia', 'Mineiro,57', 2);
INSERT INTO Employee(id, employee_name, surname, status, role, city, state, country, address_line, unit_id)
        VALUES (22, 'Paulinio', 'Delaver', 'HIRED', 'qa', 'Rio', 'Bercha', 'Brazilia', 'Mineiro,57', 2);
INSERT INTO Employee(id, employee_name, surname, status, role, city, state, country, address_line, unit_id)
        VALUES (23, 'Paulinio', 'Delaver', 'HIRED', 'qa', 'Rio', 'Bercha', 'Brazilia', 'Mineiro,57', 2);

INSERT INTO personal_info(id, birthdate, hiredate, employee_id) VALUES (1, '2000-10-10', '2019-01-09', 20);
INSERT INTO personal_info(id, birthdate, hiredate, employee_id) VALUES (2, '2000-11-10', '2019-02-09', 21);