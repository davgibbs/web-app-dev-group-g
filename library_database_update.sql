-- Database For EE417 Group G David Mullen 
-- Database schema is called EE417
-- Three tables defined - books, library_records and members


USE EE417;
DROP TABLE IF EXISTS library_records;
DROP TABLE IF EXISTS login;
DROP TABLE IF EXISTS books;
DROP TABLE IF EXISTS members;

CREATE TABLE books
(isbn INTEGER DEFAULT '0000',
title VARCHAR(30) NOT NULL,
author VARCHAR(30) NOT NULL,
available VARCHAR(80),
date date,
image VARCHAR(45),
PRIMARY KEY (isbn));
insert into books values 
(102334,'The DaVinci Code','Dan Brown','yes','2023-01-01',NULL);
insert into books values 
(1882276,'Dubliners','James Joyce','yes','2023-03-04',NULL);
insert into books values 
(1892465,'The Firm','John Grisham','yes','2023-02-23',NULL);
insert into books values 
(10112233,'The Time Machine','H.G Wells','yes','2023-03-10',NULL);
insert into books values 
(14459678,'The Great Gatsby','Scott.F Fitzgerald','yes','2023-01-07',NULL);
insert into books values 
(18233443,'Red Rabbit','Tom Clancy','yes','2023-02-14',NULL);
insert into books values 
(92210234,'A Tale Of Two Cities','Charles Dickens','yes','2023-01-21',NULL);
insert into books values 
(123456789,'The Shining','Stephen King','yes','2023-03-22',NULL);

CREATE TABLE members
(memberid INTEGER DEFAULT '0000',
name VARCHAR(30),
surname VARCHAR(30),
email VARCHAR(45),
PRIMARY KEY (memberid));
insert into members values (1001,'Joe','Bloggs','joe.bloggs@gmail.com');
insert into members values (1002,'Jane','Doe','jane.doe@gmail.com');

CREATE TABLE library_records
(recordid INTEGER DEFAULT '0000',
memberid INTEGER,
isbn INTEGER,
borrowed_date date,
due_date date,
overdue VARCHAR(30),
PRIMARY KEY (recordid),
foreign key (memberid)
REFERENCES members(memberid),
foreign key (isbn)
REFERENCES books(isbn));
insert into library_records values (12345,1001,102334,'2023-03-04','2023-04-24','NO');
insert into library_records values (678910,1002,14459678,'2023-02-20','2023-03-20','YES');

CREATE TABLE login
(loginid INTEGER DEFAULT '0000',
username VARCHAR(45),
pass VARCHAR(45),
PRIMARY KEY (loginid),
FOREIGN KEY (loginid)
REFERENCES members(memberid));
insert into login values (1001,'JoeBlogg','kdff9995jg');
insert into login values (1002,'JaneDoe','Xur6754');
