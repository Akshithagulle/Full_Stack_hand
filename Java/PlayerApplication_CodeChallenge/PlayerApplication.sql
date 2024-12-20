CREATE DATABASE PlayerDB;
drop database PlayerDB;
USE PlayerDB;
CREATE TABLE players (
    id INT PRIMARY KEY,
    name VARCHAR(100),
    skill VARCHAR(50),
    exp INT,
    country VARCHAR(50),
    overall_score DOUBLE
);
select * from players;
