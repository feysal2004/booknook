BEGIN TRANSACTION;

DROP TABLE IF EXISTS users, books_table;

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

CREATE TABLE topics (
	
	topic_name varchar (50) not null,
	topic_id serial not null
);



COMMIT TRANSACTION;
