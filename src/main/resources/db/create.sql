SET MODE PostgreSQL;

CREATE TABLE IF NOT EXISTS users (
 id int PRIMARY KEY auto_increment,
 name VARCHAR,
 position VARCHAR,
 role VARCHAR,
 department_id INTEGER
);

CREATE TABLE IF NOT EXISTS departments (
 id int PRIMARY KEY auto_increment,
 department_name VARCHAR,
 description VARCHAR,
 size INTEGER
);

CREATE TABLE IF NOT EXISTS news (
 id int PRIMARY KEY auto_increment,
 writtenby VARCHAR,
 post VARCHAR,
 department_id INTEGER
);
