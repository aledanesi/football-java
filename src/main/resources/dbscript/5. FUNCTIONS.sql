DELIMITER $$

DROP FUNCTION IF EXISTS progetto.getRank $$

CREATE FUNCTION getRank(p1 BIGINT(20), p2 BIGINT(20)) RETURNS int(11)

BEGIN    

	RETURN (SELECT rank FROM ( select @rownum:=@rownum+1 as rank, id from player, (SELECT @rownum:=0) r where team_id = p1 order by position_id, number, birth_date) q where id = p2); 
	  
END $$



DELIMITER $$

CREATE PROCEDURE fixPlayers()

BEGIN

	DECLARE finished INTEGER DEFAULT 0;
  	DECLARE cursor_ID INT;
  	DECLARE teamName VARCHAR(255);
  	
  

	-- declare cursor for employee email
  DECLARE cursor_i CURSOR FOR SELECT ID FROM progetto.player;

	-- declare NOT FOUND handler
	DECLARE CONTINUE HANDLER FOR NOT FOUND SET finished = 1;

	OPEN cursor_i;

	read_loop: LOOP

		FETCH cursor_i INTO cursor_ID;

		IF finished = 1 THEN 
			LEAVE read_loop;
		END IF;
		
		SET teamName = (select name from progetto.player p 
				left outer join progetto.career c on c.player_ID = p.id 
				left outer join progetto.team t on t.ID != p.team_id				
				where c.SQUADRA = t.NAME
				and c.player_ID = cursor_ID
				order by c.periodo desc limit 1);
		
		update progetto.player set team_prev_id = 
		(
			select id as team_id from progetto.team where name = teamName
		)
		where id = cursor_ID;
		

	END LOOP read_loop;

	CLOSE cursor_i;

END $$



DELIMITER $$

CREATE PROCEDURE endSeasonBatch(IN param_year VARCHAR(50))

BEGIN
	
		-- i calciatori a fine contratto sono svincolati
		update progetto.player set team_id = null, team_owner_id = null, without_team = 1 where date_contract = param_year and on_loan = 0;
		
		-- i calciatori in prestito tornano alla squadra di appartenenza
		update progetto.player set team_id = team_owner_id, number = null, on_loan = 0 where on_loan = 1;
		
END $$