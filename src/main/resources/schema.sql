drop table if exists Parameters;

CREATE TABLE IF NOT EXISTS Parameters
(
    parameter_name varchar(20) primary key,
    parameter_value varchar(20)
);