CREATE TABLE POSITION (  
ID int NOT NULL,  
NAME varchar(50) default NULL,  
VALUE varchar(3) default NULL,  
PRIMARY KEY  (ID)) 

INSERT INTO POSITION(ID, NAME, VALUE) VALUES (1, 'Portiere', 'POR');
INSERT INTO POSITION(ID, NAME, VALUE) VALUES (2, 'Difensore', 'DIF');
INSERT INTO POSITION(ID, NAME, VALUE) VALUES (3, 'Centrocampista', 'CEN');
INSERT INTO POSITION(ID, NAME, VALUE) VALUES (4, 'Attaccante', 'ATT');