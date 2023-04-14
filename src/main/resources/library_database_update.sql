-- Database For EE417 Group G David Mullen 
-- Database schema is called EE417
-- Three tables defined - books, library_records and members

USE EE417;
DROP TABLE IF EXISTS library_records;
DROP TABLE IF EXISTS user_role;
DROP TABLE IF EXISTS members;
DROP TABLE IF EXISTS roles;
DROP TABLE IF EXISTS books;

CREATE TABLE books
(id INTEGER AUTO_INCREMENT,
isbn INTEGER UNIQUE,
title VARCHAR(30) NOT NULL,
author VARCHAR(30) NOT NULL,
available BOOL,
publish_date date,
descr text,
image_path VARCHAR(100),
PRIMARY KEY (id));
insert into books(isbn,title,author,available,publish_date,descr,image_path)
 values (10001,'The DaVinci Code','Dan Brown',true,'2023-01-01',NULL,NULL);
insert into books(isbn,title,author,available,publish_date,descr,image_path)
values(10002, 'Dubliners','James Joyce',true,'2023-03-04',NULL,NULL);
insert into books(isbn,title,author,available,publish_date,descr,image_path)
 values(11232,'The Firm','John Grisham',true,'2023-02-23',NULL,NULL);
insert into books(isbn,title,author,available,publish_date,descr,image_path)
 values(12345,'The Time Machine','H.G Wells',true,'2023-03-10',NULL,NULL);
insert into books(isbn,title,author,available,publish_date,descr,image_path)
 values(11111,'The Great Gatsby','Scott.F Fitzgerald',true,'2023-01-07',NULL,NULL);
insert into books(isbn,title,author,available,publish_date,descr,image_path)
 values(43215,'Red Rabbit','Tom Clancy',true,'2023-02-14',NULL,NULL);
insert into books(isbn,title,author,available,publish_date,descr,image_path)
 values(22222,'A Tale Of Two Cities','Charles Dickens',true,'2023-01-21',NULL,NULL);
insert into books(isbn,title,author,available,publish_date,descr,image_path)
 values(56784,'The Shining','Stephen King',true,'2023-03-22',NULL,NULL);

CREATE TABLE roles
(id INTEGER,
 rolename VARCHAR(10),
 PRIMARY KEY (id));
insert into roles values 
(1,'ADMIN');
insert into roles values 
(2,'USER');

CREATE TABLE members
(id INTEGER AUTO_INCREMENT,
username VARCHAR(10) UNIQUE,
password VARCHAR(255) NOT NULL,
firstname VARCHAR(30),
surname VARCHAR(30),
email VARCHAR(45) UNIQUE,
PRIMARY KEY (id));

CREATE TABLE user_role
(id INTEGER AUTO_INCREMENT,
user_id INTEGER,
role_id INTEGER DEFAULT '2',
foreign key (user_id)
REFERENCES members(id),
foreign key (role_id)
REFERENCES roles(id),
PRIMARY KEY (id));

-- Sign up using http://localhost:8080/auth/signup for inserting data to the 'members' table
-- as password is stored in encrypted format (bcrypt)


-- the insert statements below will work only once there are entries in the 'members' table
insert into user_role(user_id,role_id)
 values (1000,1);
insert into user_role(user_id,role_id)
 values (1001,2);
insert into user_role(user_id,role_id)
 values (1002,2);


CREATE TABLE library_records
(recordid INTEGER AUTO_INCREMENT,
memberid INTEGER,
isbn INTEGER UNIQUE,
borrowed_date date,
due_date date,
overdue VARCHAR(30),
PRIMARY KEY (recordid),
foreign key (memberid)
REFERENCES members(id),
foreign key (isbn)
REFERENCES books(isbn));
insert into library_records(memberid,isbn,borrowed_date,due_date,overdue)
 values (3,11111,'2023-03-04','2023-04-24','NO');
insert into library_records(memberid,isbn,borrowed_date,due_date,overdue)
 values (4,12345,'2023-02-20','2023-03-20','YES');