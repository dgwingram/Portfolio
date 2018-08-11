DROP DATABASE IF EXISTS cbe;
CREATE DATABASE cbe;
USE cbe;

CREATE TABLE Student(
	studentId 		INT(4)			PRIMARY KEY AUTO_INCREMENT,
	firstname 		VARCHAR(20),
	lastname 		VARCHAR(50)
);


CREATE TABLE Book(
	bookId			INT(11)			PRIMARY KEY AUTO_INCREMENT,
	author			VARCHAR(50),
	title			VARCHAR(100)
);

CREATE TABLE Course(
	courseId		INT(11)			PRIMARY KEY AUTO_INCREMENT,
	title			VARCHAR(40),
	bookId			INT(11),
	CONSTRAINT fk_course_bookId FOREIGN KEY (bookId) REFERENCES Book(bookId)
);

CREATE TABLE Sale_item(
	itemID 			INT(11)			PRIMARY KEY AUTO_INCREMENT,
	courseId		INT(11),
	sellerId		INT(11),
	bookId			INT(11),
	CONSTRAINT fk_courseId	FOREIGN KEY (courseId) REFERENCES course(courseId),
	CONSTRAINT fk_sellerId	FOREIGN KEY (sellerId) REFERENCES Student(studentId),
	CONSTRAINT fk_bookId	FOREIGN KEY	(bookId)	 REFERENCES	Book(bookId)
);

LOAD DATA
	LOCAL INFILE 'H:\\Semester3\\COMP206_Web\\classes\\2015Sept22\\students.csv'
    INTO TABLE Student
    COLUMNS TERMINATED BY ','
    LINES TERMINATED BY '\r\n';
