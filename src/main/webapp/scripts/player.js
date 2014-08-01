var jQ = jQuery.noConflict();

jQ(function() {

	jQ("#owner_nation_id").change(function() {
		var nation = jQ("#owner_nation_id").val();

		divisionManager.listDivisionsByNation(nation, function(data) {
			jQ( "#owner_division_id" ).empty();
			dwr.util.addOptions("owner_division_id", data, "id", "name");

			var division = jQ("#owner_division_id").val();
			teamManager.listTeamsByDivision(nation, division, function(data2) {
				jQ("#owner_team_id").empty();
				dwr.util.addOptions("owner_team_id", data2, "id", "name");
			});

		});

	});

	jQ("#owner_division_id").change(function() {
		var nation = jQ("#owner_nation_id").val();
		var division = jQ("#owner_division_id").val();

		teamManager.listTeamsByDivision(nation, division, function(data2) {
			jQ("#owner_team_id").empty();
			dwr.util.addOptions("owner_team_id", data2, "id", "name");
		});

	});

	jQ("#nation_id").change(function() {
		var nation = jQ("#nation_id").val();

		divisionManager.listDivisionsByNation(nation, function(data) {
			// jQ( "#division_id" ).empty();
			// dwr.util.addOptions("division_id", data, "id", "name");

			var division = jQ("#division_id").val();
			teamManager.listTeamsByDivision(nation, division, function(data2) {
				jQ("#team_id").empty();
				dwr.util.addOptions("team_id", data2, "id", "name");
			});

		});

	});

	jQ("#division_id").change(function() {
		var nation = jQ("#nation_id").val();
		var division = jQ("#division_id").val();

		teamManager.listTeamsByDivision(nation, division, function(data2) {
			jQ("#team_id").empty();
			dwr.util.addOptions("team_id", data2, "id", "name");
		});

	});
	
	jQ( "a.targetLinkPlayers" ).click(function() 
	{  		
		jQ("#divisionId").val(jQ(this).attr("data-division"));
		jQ("#nationId").val(jQ(this).attr("data-nation"));
		
		jQ("#returnTeamsPageForm").submit(); 
	});
	
	jQ( "a.targetLinkPlayer" ).click(function() 
	{  		
		jQ("#viewPlayerPageForm input[id=id]").val(jQ(this).attr("data-id"));

		jQ("#viewPlayerPageForm").submit(); 
	});

	jQ( "a.targetLinkViewPlayer" ).click(function() 
	{  		
		jQ("#teamId").val(jQ(this).attr("data-id"));
		
		jQ("#returnPlayersPageForm").submit(); 
	});	
	
	jQ("#dEditPlayer").dialog({
		title : "Modifica Giocatore",
		width : 700,
		height : 600,
		autoOpen : false,
		modal : true,
		resizable : true,
		autoResize : true,
		dialogClass : "dlg-no-close",
		buttons : {
			"Salva" : function() {
				jQ(".validateTips").show();

				var bValid = true;

				var lastName = jQ("#lastName");
				var birthDate = jQ("#birthDate");

				var allFields = jQ([]).add(lastName).add(birthDate);

				allFields.removeClass("ui-state-error");

				bValid = bValid && checkLength(lastName, "Cognome");
				bValid = bValid && checkLength(birthDate, "Data di Nascita");

				if (bValid) {
					jQ(".validateTips").hide();
					jQ("#editPlayer").submit();
				}
			},
			"Annulla" : function() {
				cleanPlayer();

				jQ(this).dialog("close");
			}
		}
	});

	jQ("#dDeletePlayer").dialog({
		resizable : false,
		height : 170,
		modal : true,
		autoOpen : false,
		buttons : {
			"Elimina" : function() {
				jQ(window.location).attr('href', jQ("#urlDeletePlayer").val());
			},
			"Annulla" : function() {
				jQ(this).dialog("close");
			}
		}
	});

	jQ("#dMovePlayer").dialog({
		title : "Cambia la squadra",
		width : 600,
		height : 450,
		autoOpen : false,
		modal : true,
		resizable : true,
		autoResize : true,
		dialogClass : "dlg-no-close",
		buttons : {
			"Salva" : function() {
				if (jQ('input[name=onLoan]').is(':checked')) {
					jQ("#teamOwnerId").val(jQ("#teamBeforeChange").val());
				}
				jQ("#movePlayer").submit();
			},
			"Annulla" : function() {
				jQ(this).dialog("close");
			}
		}
	});

	jQ("#ruolo_id").change(function() {
		var codRuolo = jQ("#ruolo_id").val();

		positionManager.listPositions(codRuolo, function(data) {
			jQ("#position_id").empty();
			dwr.util.addOptions("position_id", data, "id", "descPosizione");
			jQ("#position_id").trigger("change");
		});
	});

	jQ("#position_id").change(function() {
		var codPosizione = jQ("#position_id").val();
		
		switch (codPosizione) {
		
			case '1':
			case '2':
			case '5':
			case '6':
			case '9':
			case '13':
			case '16':
			case '17':			
				jQ("#foot").val("");
				break;
				
			case '3':
			case '8':
			case '10':
			case '14':			
				jQ("#foot").val("sinistro");
				break;
				
			case '4':
			case '7':
			case '11':
			case '15':
				jQ("#foot").val("destro");
				break;
	
			default:
				break;
		}

	});	


});

function Player() {
}

// METHODS
Player.prototype = {

	confirmDeletePlayer : function(urlDelete) {
		jQ("#urlDeletePlayer").val(urlDelete);

		jQ('#dDeletePlayer').dialog('open');
	},

	newPlayer : function() {

		jQ('#dEditPlayer').dialog('open');

		cleanPlayer();
				

		var nationID = jQ('#editPlayer input[id=team_nation_id]').val();
		var divisionID = jQ('#editPlayer input[id=team_division_id]').val();
		var teamID = jQ('#editPlayer input[id=team_id]').val();

		var nation = jQ("#owner_nation_id").val();
		
		// nation id default
		jQ("#editPlayer select[id=nation_id] option").each(function() {
			if (jQ(this).val() == jQ("#team_nation_id").val())
				jQ(this).attr("selected", "selected");
		});
		
		jQ("#owner_nation_id").val(nation);
		divisionManager.listDivisionsByNation(nation,
				function(data4) {
					dwr.util.removeAllOptions("owner_division_id");
					dwr.util.addOptions("owner_division_id", data4, "id", "name");

					// OWNER nation ID
					checkOnSelectByVal('editPlayer', 'owner_division_id', divisionID);

		});	

		teamManager.listTeamsByDivision(
					nationID,
					divisionID,
					function(data3) {
						dwr.util.removeAllOptions("owner_team_id");
						dwr.util.addOptions("owner_team_id", data3, "id", "name");

						// OWNER TEAM ID
						checkOnSelectByVal('editPlayer', 'owner_team_id', teamID);

						controlloPrestito();
		});
		
	},

	editPlayer : function(playerId) {
		
		jQ('#dEditPlayer').dialog('open');

		cleanPlayer();
		
		playerManager.getPlayerByID(playerId,
						function(data) {
							var urlImage = jQ('#editPlayer input[name=urlImage]').val();
							jQ('#editPlayer img[id=myImage]').attr('src', urlImage + "?id=" + playerId);

							setTextByName('editPlayer', 'id', playerId);
							setTextByName('editPlayer', 'firstName', data.firstName);
							setTextByName('editPlayer', 'lastName', data.lastName);
							setTextByName('editPlayer', 'completeName',  data.completeName);
							setTextByName('editPlayer', 'birthDate', dateToYMD(data.birthDate));
							setTextByName('editPlayer', 'birthPlace', data.birthPlace);

							setTextByName('editPlayer', 'endCareer', data.endCareer);
							setTextByName('editPlayer', 'withoutTeam', data.withoutTeam);
							
							if (data.teamPrev != null)
								setTextByName('editPlayer', 'team_prev_id', data.teamPrev.id);

							if(data.team != null)
							{
								setTextByName('editPlayer', 'team_id', data.team.id);
								setTextByName('editPlayer', 'team_nation_id', data.team.nation.id);
								setTextByName('editPlayer', 'team_division_id', data.team.division.id);								
							}


							// NATION ID
							//checkOnSelectByText('editPlayer', 'nation_id', data.nation.name);							
							jQ("#nation_id").val(data.nation.id);


							// NATION2 ID
							if(data.nation2 != null)
								//checkOnSelectByText('editPlayer', 'nation2_id', data.nation2.name);
								jQ("#nation2_id").val(data.nation2.id);

							// RUOLO ID							

							//checkOnSelectByText('editPlayer', 'ruolo_id', data.position.descRuolo);
							jQ("#ruolo_id").val(data.position.codRuolo);


							// NATIONAL
							//checkOnSelectByVal('editPlayer', 'national', data.national);
							jQ("#national").val(data.national);

							// TEAM NATION ID
							if(data.team != null)
								//checkOnSelectByVal('editPlayer', 'team_nation_id', data.team.nation.name);
								jQ("#team_nation_id").val(data.team.nation.id);

							// TEAM CATEGORY
							//checkOnSelectByVal('editPlayer', 'teamCategory', data.teamCategory);
							jQ("#teamCategory").val(data.teamCategory);

							// FOOT
							//checkOnSelectByVal('editPlayer', 'foot', data.foot);
							jQ("#foot").val(data.foot);

							positionManager.listPositions(jQ("#ruolo_id").val(),
											function(data2) {
												dwr.util.removeAllOptions("position_id");
												dwr.util.addOptions("position_id", data2, "id", "descPosizione");

												//checkOnSelectByText('editPlayer', 'position_id', data.position.descPosizione);
												jQ("#position_id").val(data.position.id);
											});

							setTextByName('editPlayer', 'nation', data.nation.id);
							setTextByName('editPlayer', 'position', data.position.id);
							setTextByName('editPlayer', 'height', data.height);
							setTextByName('editPlayer', 'weight', data.weight);
							setTextByName('editPlayer', 'number', data.number);

							// VALUE
							checkOnText('editPlayer', 'value', data.value);

							// INCOME
							checkOnText('editPlayer', 'income', data.income);
							
							formatCurrency(jQ("#income"));
							formatCurrency(jQ("#value"));

							// DATE CONTRACT
							checkOnText('editPlayer', 'dateContract', data.dateContract);

							// CAPTAIN
							checkOnCheckBox('editPlayer', 'captain', data.captain);

							// ON LOAN
							if(data.team != null)
								checkOnCheckBox('editPlayer', 'prestito', data.team.id != data.teamOwner.id);

							if(data.teamOwner != null)
							{
								jQ("#owner_nation_id").val(data.teamOwner.nation.id);
								divisionManager.listDivisionsByNation(data.teamOwner.nation.id,
										function(data4) {
											dwr.util.removeAllOptions("owner_division_id");
											dwr.util.addOptions("owner_division_id", data4, "id", "name");
			
											// OWNER nation ID
											//checkOnSelectByVal('editPlayer', 'owner_division_id', data.teamOwner.division.id);
											jQ("#owner_division_id").val(data.teamOwner.division.id);
								});	

								teamManager.listTeamsByDivision(
											data.teamOwner.nation.id,
											data.teamOwner.division.id,
											function(data3) {
												dwr.util.removeAllOptions("owner_team_id");
												dwr.util.addOptions("owner_team_id", data3, "id", "name");

												// OWNER TEAM ID
												//checkOnSelectByVal('editPlayer', 'owner_team_id', data.teamOwner.id);
												jQ("#owner_team_id").val(data.teamOwner.id);
												controlloPrestito();
								});

							}

						});					
						
	},

	movePlayer : function() {

		jQ('#dMovePlayer').dialog('open');

		teamManager.listTeamsByDivision(jQ("#teamNationId").val(), jQ("#teamDivisionId").val(), function(data) {
			dwr.util.removeAllOptions("team_id");
			dwr.util.addOptions("team_id", data, "id", "name");

			// TEAM ID
			checkOnSelectByVal('movePlayer', 'team_id', jQ("#teamBeforeChange").val());

		});
	},
	
	changePlayer : function() {
		var playerId = jQ("#view_player_id").val();

		jQ("#id").val(playerId);
		
		jQ("#viewPlayerPageForm").submit(); 
	},
	
	goWithoutTeam : function() {		
		jQ("#withoutTeamPlayer").submit(); 
	},	

	goEndCareer : function() {
		jQ("#endCareerPlayer").submit(); 
	}

}

var player = new Player();

function cleanPlayer() {
	var lastName = jQ("#lastName"), birthDate = jQ("#birthDate"), allFields = jQ([]).add(lastName).add(birthDate);

	jQ(".validateTips").hide();
	allFields.removeClass("ui-state-error");

	jQ('#editPlayer img[id=myImage]').attr('src',
			'../images/players/notFound.jpg');
	jQ('#editPlayer input[name=id]').val('');
	jQ('#editPlayer input[name=firstName]').val('');
	jQ('#editPlayer input[name=lastName]').val('');
	jQ('#editPlayer input[name=completeName]').val('');
	jQ('#editPlayer input[name=birthDate]').val('');
	jQ('#editPlayer input[name=birthPlace]').val('');
	jQ('#editPlayer input[name=nation]').val('');
	jQ('#editPlayer input[name=position]').val('');
	jQ('#editPlayer input[name=height]').val('?');
	jQ('#editPlayer input[name=weight]').val('?');
	jQ('#editPlayer input[name=number]').val('');
	jQ('#editPlayer input[name=value]').val('');
	jQ('#editPlayer input[name=income]').val('');
	jQ('#editPlayer input[name=dateContract]').val('?');
	jQ('#editPlayer input[name=captain]').attr('checked', false);

	jQ("#editPlayer select[id=ruolo_id] option").each(function() {
		if (jQ(this).val() == '01')
			jQ(this).attr("selected", "selected");
	});

	var codRuolo = jQ("#ruolo_id").val();
	positionManager.listPositions(codRuolo, function(data) {
		dwr.util.removeAllOptions("position_id");
		dwr.util.addOptions("position_id", data, "id", "descPosizione");
	});

	jQ("#owner_team_id").val(jQ("#team_id").val());
	jQ("#owner_nation_id").val(jQ("#team_nation_id").val());
	jQ("#owner_division_id").val(jQ("#team_division_id").val());
	
	// CAPTAIN
	checkOnCheckBox('editPlayer', 'prestito', false);
	

	jQ("#trPrestito").hide();

}