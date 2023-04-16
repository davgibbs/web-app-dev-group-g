# Five tables in this database

# Drop previous database if it existed
#DROP database if exists group_g_library;

# Create a database to hold the tables
#CREATE database group_g_library;

# Create a table for the roles
#create table group_g_library.roles
#(id int not null auto_increment primary key,
# name varchar(100) not null unique
#);

# Create a table for the members (admins are also considered members)
#CREATE TABLE group_g_library.members
#(id INTEGER AUTO_INCREMENT PRIMARY KEY,
# username VARCHAR(60) UNIQUE,
# password VARCHAR(255) NOT NULL,
# firstname VARCHAR(30),
# surname VARCHAR(30),
# email VARCHAR(45) UNIQUE
#);

# Create a table to link members to roles
#create table group_g_library.member_role
#(member_id int not null,
# CONSTRAINT FK_members FOREIGN KEY (member_id) REFERENCES group_g_library.members (id) ON UPDATE CASCADE ON DELETE CASCADE,
# role_id int not null DEFAULT '2',
# CONSTRAINT FK_roles FOREIGN KEY (role_id) REFERENCES group_g_library.roles (id) ON UPDATE CASCADE ON DELETE CASCADE,
# CONSTRAINT members_roles_pk PRIMARY KEY (member_id, role_id)
#);

# Create a table for books
#CREATE TABLE group_g_library.books
#(id INTEGER AUTO_INCREMENT primary key,
# isbn INTEGER UNIQUE,
# title VARCHAR(60) NOT NULL,
# author VARCHAR(60) NOT NULL,
# publish_date DATE NOT NULL,
# description VARCHAR(2000),
# image_path VARCHAR(200) DEFAULT './images/book.png'
#);

# Create a table for library records (the checkouts)
#CREATE TABLE group_g_library.library_records
#(id INTEGER AUTO_INCREMENT primary key,
# member_id int not null,
# CONSTRAINT FK_member_ids FOREIGN KEY (member_id) REFERENCES group_g_library.members (id) ON UPDATE CASCADE ON DELETE CASCADE,
# book_id int not null,
# CONSTRAINT FK_books FOREIGN KEY (book_id) REFERENCES group_g_library.books (id) ON UPDATE CASCADE ON DELETE CASCADE,
# borrowed_date DATE NOT NULL,
# due_date DATE NOT NULL,
# is_returned BOOLEAN NOT NULL DEFAULT false
#);