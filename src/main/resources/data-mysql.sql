
# Create two types of roles
#INSERT INTO group_g_library.roles (id, name) VALUES (1, 'ADMIN');
#INSERT INTO group_g_library.roles (id, name) VALUES (2, 'USER');

# Create an admin user with password: secret (encoded in database)
#insert into group_g_library.members(id, username, email, password) values(1, 'admin', 'admin@gmail.com', '$2a$10$H/eRPpLQVaJTxH8Uod8lS..HaZpZ4XVmUcFouI68d4aSxuJLeV3JK');
# Create a regular user: tom.smith with password: secret (encoded in database)
#insert into group_g_library.members(id, username, email, firstname, surname, password) values(2, 'tom.smith', 'tom.smith@gmail.com', 'Tom', 'Smith', '$2a$10$H/eRPpLQVaJTxH8Uod8lS..HaZpZ4XVmUcFouI68d4aSxuJLeV3JK');

# Add roles to the accounts created
#insert into group_g_library.member_role(member_id,role_id) values (1,1);
#insert into group_g_library.member_role(member_id,role_id) values (2,2);

# Add 8 initial books to the library
#insert into group_g_library.books(id, isbn,title,author,publish_date,image_path,description)
#	values(1, 10001,'The DaVinci Code','Dan Brown','2003-03-18','images/daVinchiCode.jpg', 'While in Paris, Harvard symbologist Robert Langdon is awakened by a phone call in the dead of the night. The elderly curator of the Louvre has been murdered inside the museum, his body covered in baffling symbols. As Langdon and gifted French cryptologist Sophie Neveu sort through the bizarre riddles, they are stunned to discover a trail of clues hidden in the works of Leonardo da Vinci—clues visible for all to see and yet ingeniously disguised by the painter.');
#insert into group_g_library.books(id, isbn,title,author,publish_date,image_path,description)
#	values(2, 10002, 'Dubliners','James Joyce','2023-03-04','images/dubliners.jpg',NULL);
#insert into group_g_library.books(id,isbn,title,author,publish_date,image_path,description)
#	values(3, 11232,'The Firm','John Grisham','2023-02-23','images/theFirm.jpg',NULL);
#insert into group_g_library.books(id,isbn,title,author,publish_date,image_path,description)
#	values(4, 12345,'The Time Machine','H.G Wells','2023-03-10','images/theTimeMachine.jpg',NULL);
#insert into group_g_library.books(id,isbn,title,author,publish_date,image_path,description)
#	values(5, 11111,'The Great Gatsby','Scott.F Fitzgerald','2023-01-07','images/theGreatGatsby.jpg',NULL);
#insert into group_g_library.books(id,isbn,title,author,publish_date,image_path,description)
#	values(6, 43215,'Red Rabbit','Tom Clancy','2023-02-14','images/redRabbit.jpg',NULL);
#insert into group_g_library.books(id,isbn,title,author,publish_date,image_path,description)
#	values(7, 22222,'A Tale Of Two Cities','Charles Dickens','2023-01-21','images/aTaleOfTwoCities.jpg',NULL);
#insert into group_g_library.books(id,isbn,title,author,publish_date,image_path,description)
#	values(8, 56784,'The Shining','Stephen King','2023-03-22','images/theShining.jpg',NULL);
	
# Set two of the books (Dubliners and A Tale of Two cities) as checked out by member 2.
#insert into group_g_library.library_records(member_id,book_id,borrowed_date,due_date,is_returned) values (2, 2,'2023-03-04','2023-04-24',false);
#insert into group_g_library.library_records(member_id,book_id,borrowed_date,due_date,is_returned) values (2, 7,'2023-02-20','2023-03-20',false);
#insert into group_g_library.library_records(member_id,book_id,borrowed_date,due_date,is_returned) values (2, 3,'2023-01-20','2023-01-30',true);
