CREATE TABLE TEAM (  
ID int NOT NULL,  
NAME varchar(50) default NULL,  
CITY varchar(50) default NULL,  
ADDRESS varchar(50) default NULL,  
STADIUM varchar(50) default NULL,  
EMAIL varchar(50) default NULL,
PHONE varchar(50) default NULL,
FAX varchar(50) default NULL,   
COLOR_TEAM varchar(50) default NULL,
DIVISION_ID int NOT NULL,
PRIMARY KEY  (ID)) 

INSERT INTO TEAM(ID, NAME, DIVISION_ID) VALUES (1, 'LATINA', 6);
INSERT INTO TEAM(ID, NAME, DIVISION_ID) VALUES (2, 'POMEZIA', 6);
INSERT INTO TEAM(ID, NAME, DIVISION_ID) VALUES (3, 'FONDI', 6);
INSERT INTO TEAM(ID, NAME, DIVISION_ID) VALUES (4, 'TRAPANI', 6);
INSERT INTO TEAM(ID, NAME, DIVISION_ID) VALUES (423, 'ACIREALE', 6);

INSERT INTO TEAM(ID, NAME) VALUES (101, 'HINTERREGGIO');
INSERT INTO TEAM(ID, NAME) VALUES (102, 'REGGIO SUD');



ALTER TABLE TEAM ADD COLUMN IMAGE BLOB NULL;