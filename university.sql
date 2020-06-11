DROP TABLE Faculty;
DROP TABLE Course;

CREATE TABLE Faculty(
	faculty_id varchar (25) NOT NULL,
	faculty_name varchar (30) NOT NULL,
	office varchar (50)  NOT NULL,
	PRIMARY KEY (faculty_id)
);

CREATE TABLE Course(
	course_id varchar (25) NOT NULL,
	course varchar (50) NOT NULl,
	faculty_id varchar (25) NOT NULL,
	PRIMARY KEY (course_id),
	FOREIGN KEY (faculty_id) REFERENCES Faculty (faculty_id)
);

INSERT INTO Faculty(faculty_id, faculty_name, office) 
Values
	('A52990', 'Black Anderson','MTC-218'),
	('A77587','Debby Angles', 'MTC-320'),
	('B66750','Alice Brown','MTC-257'),
	('B78880','Ying Bai','MTC-211'),
	('B86590','Satish Bhalla', 'MTC-214'),
	('H99118','Jeff Henry','MTC-336'),
	('J33486','Steve Johnson','MTC-118'),
	('K69880','Jenney King','MTC-324');

INSERT INTO Course(course_id, course, faculty_id)
VALUES
	('CSC-132A','Introduction to Programming','J33486'),
	('CSC-132B','Introduction to Programming','B78880'),
	('CSC-230','Algorithm & Structures','A77587'),
	('CSC-232A','Programming 1', 'B66750');





































