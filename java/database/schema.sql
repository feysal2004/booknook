ROLLBACK;
BEGIN TRANSACTION;

DROP TABLE IF EXISTS users, books_table, genre_table, topics, messages, my_books;
DROP SEQUENCE IF EXISTS topic_id_serial, message_id_serial;

CREATE TABLE users (
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

CREATE TABLE books_table (
book_name varchar(200) NOT NULL,
	author varchar (100) NOT NULL,
	author_second varchar(100) NULL,
	description varchar(260) NOT NULL,
	series varchar(50) NULL,
	book_id serial NOT NULL,
	release_date DATE NOT NULL,
	date_added_to_collection DATE NULL
);

CREATE TABLE genre_table (
	book_id serial NOT NULL,
	genre_id serial NOT NULL,
	genre_name varchar(50) NOT NULL
);

CREATE SEQUENCE topic_id_serial;
CREATE TABLE topics (
	topic_name varchar (50) not null,
	topic_id int not null DEFAULT nextval('topic_id_serial'),
	CONSTRAINT PK_topic_id PRIMARY KEY (topic_id)
);

INSERT INTO topics (topic_name) VALUES ('test name');

CREATE SEQUENCE message_id_serial;
CREATE TABLE messages (
	message_id int not null default nextval('message_id_serial'),
	topic_id int not null,
	message_text varchar (300) not null,
	message_written_by varchar(100),
	CONSTRAINT PK_message_id PRIMARY KEY (message_id)
);
CREATE TABLE my_books(

	user_id int not null,
	username varchar(50) NOT NULL UNIQUE,
	book_name varchar(200) NOT NULL,
	isbn varchar null,
	author varchar (100) NOT NULL,
	thumbnail varchar(260) null,
	description varchar(260) null,
	read boolean null,
	wishlist boolean null

);


INSERT INTO messages (topic_id, message_text, message_written_by) VALUES ('1', 'sample text', 'some user');

COMMIT TRANSACTION;
