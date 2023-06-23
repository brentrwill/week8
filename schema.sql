DROP SCHEMA IF EXISTS hotel_system;
CREATE SCHEMA hotel_system;
USE hotel_system;

CREATE TABLE guest (
	guest_id INT NOT NULL AUTO_INCREMENT, 
	first_name VARCHAR(45) NOT NULL,
  	last_name VARCHAR(45) NOT NULL,
  	email VARCHAR(50) NOT NULL UNIQUE,
  	address1 VARCHAR(50) NOT NULL,
  	address2 VARCHAR(50) DEFAULT NULL,
  	city VARCHAR(50) NOT NULL,
  	zip VARCHAR(10) NOT NULL,
  	phone VARCHAR(20) NOT NULL,
  	INDEX idx_email (email),
  	PRIMARY KEY (guest_id)
);

CREATE TABLE room (
	room_id INT NOT NULL AUTO_INCREMENT,
	room_number INT NOT NULL,
	number_of_occupents INT NOT NULL,
	bed_options INT NOT NULL,
	description VARCHAR(255) NOT NULL,
	PRIMARY KEY (room_id)
);

CREATE TABLE reservation (
	reservation_id INT NOT NULL AUTO_INCREMENT,
	guest_id INT NOT NULL,
	room_id INT NOT NULL,
	start_date DATETIME NOT NULL,
	end_date DATETIME NOT NULL,
	price DOUBLE NOT NULL,
	PRIMARY KEY (reservation_id),
	INDEX idx_start_date (start_date),
	INDEX idx_end_date (end_date),
	INDEX idx_guest_id (guest_id),
  	INDEX idx_room_id (room_id),
	FOREIGN KEY (guest_id) REFERENCES guest (guest_id),
	FOREIGN KEY (room_id) REFERENCES room (room_id)
);

CREATE TABLE listing (
	listing_id INT NOT NULL AUTO_INCREMENT,
	room_id INT NOT NULL,
	price DOUBLE NOT NULL,
	PRIMARY KEY (listing_id),
	FOREIGN KEY (room_id) REFERENCES room (room_id)
);