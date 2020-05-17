drop table if exists Parameters;

CREATE TABLE IF NOT EXISTS PARAMETERS
(
    parameter_name varchar(20) primary key,
    parameter_value varchar(20)
);

CREATE TABLE IF NOT EXISTS GAMES
(
    game_id int primary key,
    game_name varchar(100)
);