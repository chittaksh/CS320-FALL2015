CREATE DATABASE `cs320stu115`;

USE cs320stu115;

DROP TABLE IF EXISTS RENTLOG;
DROP TABLE IF EXISTS RENTDETAILS;
DROP TABLE IF EXISTS APPOINTMENTDECLINED;
DROP TABLE IF EXISTS APPOINTMENTDETAILS;
DROP TABLE IF EXISTS APARTMENTDETAILS;
DROP TABLE IF EXISTS USERS;


CREATE TABLE USERS(
ID INT AUTO_INCREMENT PRIMARY KEY,
ROLE INT NOT NULL,
USERNAME VARCHAR(150) NOT NULL,
PASS VARCHAR(150) NOT NULL,
EMAIL VARCHAR(150) NOT NULL,
CONTACT VARCHAR(15) NOT NULL,
NOOFPEOPLE INT NOT NULL,
OCCUPATION INT NOT NULL,
APTTYPE VARCHAR(50) NOT NULL,
PREFERENCES VARCHAR(250) NOT NULL,
NEEDFROM DATETIME NOT NULL
);

CREATE TABLE APARTMENTDETAILS (
ID INT AUTO_INCREMENT PRIMARY KEY,
APARTMENTNO INT NOT NULL,
APTTYPE VARCHAR(50) NOT NULL,
FACILITIES VARCHAR(256) NOT NULL,
MAXPEOPLE INT NOT NULL,
RENT INT NOT NULL,
DEPOSIT INT NOT NULL,
VACANT BOOL NOT NULL,
UPDATEDONE DATETIME NOT NULL
); 

CREATE TABLE APPOINTMENTDETAILS(
ID INT AUTO_INCREMENT PRIMARY KEY,
USERID INT NOT NULL,
APTID INT NOT NULL, 
STATUS INT NOT NULL,
APPOINTMENTDATE DATETIME,
APPOINTCREATED DATETIME NOT NULL,

FOREIGN KEY(USERID) REFERENCES USERS(ID),
FOREIGN KEY(APTID) REFERENCES APARTMENTDETAILS(ID)
);


CREATE TABLE APPOINTMENTDECLINED(
APPOINTID INT NOT NULL,
DECLINEDDATE DATETIME NULL,

FOREIGN KEY(APPOINTID) REFERENCES APPOINTMENTDETAILS(ID)
);

CREATE TABLE RENTDETAILS(
ID INT AUTO_INCREMENT PRIMARY KEY,
USERID INT NOT NULL,
APTID INT NOT NULL, 
APPOINTID INT NOT NULL,
LEASETERMINATION DATETIME NOT NULL,
LEASEHOLDERNAME VARCHAR(250) NOT NULL,
IDPROOF VARCHAR(250) NOT NULL,
RENTRENT INT NOT NULL,
RENTDEPOSIT INT NOT NULL,

FOREIGN KEY(USERID) REFERENCES USERS(ID),
FOREIGN KEY(APTID) REFERENCES APARTMENTDETAILS(ID),
FOREIGN KEY(APPOINTID) REFERENCES APPOINTMENTDETAILS(ID)
);

CREATE TABLE RENTLOG(
ID INT AUTO_INCREMENT PRIMARY KEY,
USERID INT NOT NULL,
APTID INT NOT NULL, 
APPOINTID INT NOT NULL,
RENTID INT NOT NULL,
RENTAMOUNT INT NOT NULL,
RENTMONTH INT NOT NULL,
RENTYEAR YEAR NOT NULL,
PAYDATE DATE NOT NULL,

FOREIGN KEY(USERID) REFERENCES USERS(ID),
FOREIGN KEY(APTID) REFERENCES APARTMENTDETAILS(ID),
FOREIGN KEY(APPOINTID) REFERENCES APPOINTMENTDETAILS(ID),
FOREIGN KEY(RENTID) REFERENCES RENTDETAILS(ID)
);
