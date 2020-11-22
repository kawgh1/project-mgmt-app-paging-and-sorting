


-- These commands are for PostgreSQL not MySQL
CREATE SEQUENCE IF NOT EXISTS employee_seq;

CREATE TABLE IF NOT EXISTS employee (
                                        employee_id bigint NOT NULL DEFAULT nextval('employee_seq') PRIMARY KEY,
                                        email varchar(50) NOT NULL,
                                        first_name varchar(50) NOT NULL,
                                        last_name varchar(50) NOT NULL,
                                        position varchar(50) DEFAULT NULL
);

CREATE SEQUENCE IF NOT EXISTS manager_seq;

CREATE TABLE IF NOT EXISTS manager (
                                       manager_id bigint NOT NULL DEFAULT nextval('manager_seq') PRIMARY KEY,
                                       email varchar(50) NOT NULL,
                                       first_name varchar(50) NOT NULL,
                                       last_name varchar(50) NOT NULL
);

CREATE SEQUENCE IF NOT EXISTS project_seq;

CREATE TABLE IF NOT EXISTS project (
                                       project_id bigint NOT NULL DEFAULT nextval('project_seq') PRIMARY KEY,
                                       description varchar(255) NOT NULL,
                                       name varchar(100) NOT NULL,
                                       stage varchar(50) NOT NULL,
                                       manager_id bigint DEFAULT NULL,
                                       location varchar(50) NOT NULL,
                                       start_date date DEFAULT NULL,
                                       end_date date DEFAULT NULL,
);


CREATE TABLE IF NOT EXISTS project_employee (
                                                project_id bigint REFERENCES project,
                                                employee_id bigint REFERENCES employee
);


CREATE TABLE IF NOT EXISTS project_manager (
                                               project_id bigint REFERENCES project,
                                               manager_id bigint REFERENCES manager
);

