CREATE TABLE PLAYER (  
ID int NOT NULL,  
TEAM_ID int,
FIRST_NAME varchar(50) default NULL,  
LAST_NAME varchar(50) default NULL,  
BIRTH_DATE DATE default NULL,  
NATION_ID int,  
POSITION_ID int,
HEIGHT varchar(50) default NULL,
APPEARANCES int default 0,  
GOAL_SCORED int default 0,
PRIMARY KEY  (PLAYER_ID)) 

CREATE TABLE CAREER (  
ID int NOT NULL, 
PLAYER_ID int NOT NULL, 
STAGIONE varchar(50) default NULL,  
--TEAM_ID int,
TEAM varchar(50) default NULL,  
SERIE varchar(50) default NULL,  
PARTITE_GIOCATE varchar(50) default NULL,  
PARTITE_TOTALI varchar(50) default NULL,
GOAL varchar(50) default NULL,
MINUTI_GIOCATI varchar(50) default NULL,
MINUTI_TOTALI varchar(50) default NULL,  
SOST_FATTE varchar(50) default NULL,  
SOST_AVUTE varchar(50) default NULL,  
AMMONIZIONI varchar(50) default NULL,  
ESPULSIONI varchar(50) default NULL,
GIORNATE_SQUALIFICA varchar(50) default NULL,  
PRIMARY KEY  (ID)) 


ALTER TABLE CAREER ADD COLUMN TEAM varchar(50) default NULL

ALTER TABLE PLAYER ADD COLUMN IMAGE BLOB NULL;





UPDATE PLAYER SET TEAM_PREV_ID = (select ID from team where name = ?) WHERE ID = ?


// MODIFICA DELL'ULTIMA SQUADRA DEL GIOCATORE
UPDATE PLAYER SET team_prev_id = 
(
	SELECT id as team_id from TEAM where name = 
	(
		SELECT squadra from CAREER where player_id = ? order by periodo desc limit 1
	)
)
where id = ?