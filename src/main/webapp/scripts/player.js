/*******************************************************************
					players javascript file 
*******************************************************************/

/* var jQ = $.noConflict(); */
var jQ = jQuery.noConflict();

/* jQ(function() {  .... } */
jQ(document).ready(function() {	

	
	/***********************************************************************************
						chiamata change di 'owner_nation_id' in player.js
	 ***********************************************************************************/	
	jQ('#editPlayer select[id=owner_nation_id]').change(function() {
		console.info("chiamata 'change' di 'owner_nation_id' in player.js");

		var own_nation   = jQ('#editPlayer select[id=owner_nation_id]');
		var own_division = jQ('#editPlayer select[id=owner_division_id]');
		var own_team     = jQ('#editPlayer select[id=owner_team_id]');
		
		businessDelegate.getEntitiesBySecondID(own_nation.val(), 'DIVISION', function(data) {
			console.log("lista di division recuperate...");
			own_division.empty();
						
			//dwr.util.addOptions('owner_division_id', data, 'id', 'name');  ------> (vecchia versione dwr)
			jQ.each(data, function(key, value) {				
				own_division.append('<option value=\"' + value.id + '\">' + value.name + '</option>');
			});		
			console.log("select owner_division_id riempita");

			businessDelegate.getEntitiesByIDs(own_nation.val(), own_division.val(), 'TEAM', function(data2) {
				console.log("lista di team recuperata...");
				own_team.empty();

				//dwr.util.addOptions('owner_team_id', data2, 'id', 'name');	------> (vecchia versione dwr)
				jQ.each(data2, function(key, value) {				
					own_team.append('<option value=\"' + value.id + '\">' + value.name + '</option>');
				});
				console.log("select 'owner_team_id' riempita");
			});
		});
		console.info("fine chiamata 'change' di 'owner_nation_id' in player.js");
	});

	
	/***********************************************************************************
					chiamata change di 'owner_division_id' in player.js
	 ***********************************************************************************/	
	jQ('#editPlayer select[id=owner_division_id]').change(function() {
		console.info("chiamata 'change' di 'owner_division_id' in player.js");
		
		var own_nation = jQ('#editPlayer select[id=owner_nation_id]');
		var own_division = jQ('#editPlayer select[id=owner_division_id]');
		var own_team     = jQ('#editPlayer select[id=owner_team_id]');

		businessDelegate.getEntitiesByIDs(own_nation.val(), own_division.val(), 'TEAM', function(data2) {			
			console.log("lista di team recuperata...");
			own_team.empty();
			
			//dwr.util.addOptions('owner_team_id', data2, 'id', 'name');	------> (vecchia versione dwr)
			jQ.each(data2, function(key, value) {				
				own_team.append('<option value=\"' + value.id + '\">' + value.name + '</option>');
			});
			console.log("select 'owner_team_id' riempita");
		});
		console.info("fine chiamata 'change' di 'owner_division_id' in player.js");
	});
	
	
	/***********************************************************************************
					chiamata change di 'ruolo_id' in player.js
	 ***********************************************************************************/
	jQ('#editPlayer select[id=ruolo_id]').change(function() {
		console.info('chiamata ruolo_id--->change() in player.js');
		var codRuolo = jQ('#editPlayer select[id=ruolo_id]');
		var position = jQ('#editPlayer select[id=position_id]');
		var locale = jQ('#locale');
		
		businessDelegate.getEntitiesByParams(codRuolo.val(), locale.val(), 'POSITION', function(data) {
			console.log("lista di position recuperata...");
			position.empty();
			
			//dwr.util.addOptions('position_id', data, 'id', 'descPosizione');  ------> (vecchia versione dwr)
			jQ.each(data, function(key, value) {
				position.append('<option value=\"' + value.id + '\">' + value.descPosizione + '</option>');
			});			
			console.log("select 'position_id' riempita");
			
			position.trigger('change');
		});
		console.info('fine chiamata ruolo_id--->change() in player.js');
	});


	/***********************************************************************************
				chiamata change di 'position_id' in player.js
	***********************************************************************************/	
	jQ('#editPlayer select[id=position_id]').change(function() {
		console.info('chiamata position_id--->change() in player.js');
		
		var codPosizione = jQ('#editPlayer select[id=position_id]');
		var foot = jQ('#editPlayer select[id=foot]');
		
		switch (codPosizione.val()) {
			case '1':
			case '2':
			case '5':
			case '6':
			case '9':
			case '13':
			case '16':
			case '17':			
				foot.val('');
				break;
						
			case '4':
			case '7':
			case '11':
			case '15':
				foot.val('1');
				break;
			
			case '3':
			case '8':
			case '10':
			case '14':			
				foot.val('2');
				break;
			
			default:
				break;
		}
		console.info('fine chiamata position_id--->change() in player.js');
	});		
	
	
	/***********************************************************************************
	chiamata click di 'prestito' in player.js
	 ***********************************************************************************/	
	jQ('#prestito').click(function()
	{
		console.info('chiamata prestito--->click() in player.js');
		controlloPrestito();
		console.info('fine chiamata prestito--->click() in player.js');
	});		
	
	/***********************************************************************************
					chiamata click della classe 'targetLinkPlayers' in player.js
	 ***********************************************************************************/	
	jQ('a.targetLinkPlayers').click(function() { 
		console.info('chiamata a.targetLinkPlayers--->click() in player.js');
		jQ('#divisionId').val(jQ(this).attr('data-division'));
		jQ('#nationId').val(jQ(this).attr('data-nation'));
		
		jQ('#returnTeamsPageForm').submit(); 
		console.info('fine chiamata a.targetLinkPlayers--->click() in player.js');
	});
	
	
	/***********************************************************************************
					chiamata click della classe 'targetLinkPlayer' in player.js
	 ***********************************************************************************/		
	jQ('a.targetLinkPlayer').click(function() {  
		console.info('chiamata a.targetLinkPlayer--->click() in player.js');
		jQ('#viewPlayerPageForm input[id=id]').val(jQ(this).attr('data-id'));

		jQ('#viewPlayerPageForm').submit(); 
		console.info('fine chiamata a.targetLinkPlayer--->click() in player.js');
	});	
	
	
	/***********************************************************************************
					chiamata click della classe 'targetLinkViewPlayer' in player.js
	 ***********************************************************************************/	
	jQ('a.targetLinkViewPlayer').click(function() { 
		console.info('chiamata a.targetLinkViewPlayer--->click() in player.js');
		jQ('#teamId').val(jQ(this).attr('data-id'));
		
		jQ('#returnPlayersPageForm').submit(); 
		console.info('chiamata a.targetLinkViewPlayer--->click() in player.js');
	});		
	
	
	/***********************************************************************************
					creation dialog for 'new/edit' player page
	 ***********************************************************************************/	
	jQ('#dEditPlayer').dialog({
		title : 'Modifica Giocatore',
		width : 500,
		height : 550,
		autoOpen : false,
		modal : true,
		resizable : true,
		autoResize : true,
		dialogClass : 'dlg-no-close',
		buttons : [
			{
				id: 'button-ok',
				text: 'Salva',
				click : function() {
					console.info('esecuzione dialog editPlayer in player.js');
					jQ('.validateTips').show();

					var bValid = true;

					var firstName = jQ('#editPlayer input[id=firstName]');
					var lastName = jQ('#editPlayer input[id=lastName]');
					var dateOfBirth = jQ('#editPlayer input[id=dateOfBirth]');

					var allFields = jQ([]).add(lastName).add(dateOfBirth);

					allFields.removeClass('ui-state-error');

					bValid = bValid && checkLength(lastName, 'Cognome');
					bValid = bValid && checkLength(dateOfBirth, 'Data di Nascita');

					if (bValid) {
						jQ('.validateTips').hide();
						
						if(jQ('#editPlayer input[id=id]').val() == '' ) {
							businessDelegate.findEntityExists(firstName.val(), lastName.val(), dateOfBirth.val(), 'PLAYER', function (data)	{
								if(data == true) {
									alert('Attenzione: il giocatore è già presente nel db!');						
								}
								else {
									jQ('#editPlayer').submit();						
								}
							});					
						}
						else {
							jQ('#editPlayer').submit();
						}
					}
				}
			},
			{
				id: 'button-cancel',
				text: 'Annulla',			
				click : function() {
					console.info('chiusura dialog editPlayer in player.js');
					cleanPlayer();

					jQ(this).dialog('close');
				}
			}
		]
	});
	
	
	/***********************************************************************************
						creation dialog for 'buy' player page
	 ***********************************************************************************/		
	jQ('#dBuyPlayer').dialog({
		title : 'Acquista Giocatore',
		width : 800,
		height : 550,
		autoOpen : false,
		modal : true,
		resizable : true,
		autoResize : true,
		dialogClass : 'dlg-no-close',
		buttons : {
			'Annulla' : function() {
				console.info('chiusura dialog buyPlayer in player.js');
				cleanPlayer();

				jQ(this).dialog('close');
			}
		}
	});	
		

	/***********************************************************************************
						creation dialog for 'move' player page
	 ***********************************************************************************/		
	jQ('#dMovePlayer').dialog({
		title : 'Cambia la squadra',
		width : 600,
		height : 450,
		autoOpen : false,
		modal : true,
		resizable : true,
		autoResize : true,
		dialogClass : 'dlg-no-close',
		buttons : {
			'Conferma' : function() {
				console.info('esecuzione dialog movePlayer in player.js');
				player.confirmTransferPlayer();				
			},
			'Annulla' : function() {
				console.info('chiusura dialog movePlayer in player.js');
				jQ(this).dialog('close');
			}
		}
	});
	
	
	/***********************************************************************************
						creation dialog for 'search' player page
	 ***********************************************************************************/		
	jQ('#dSearchPlayer').dialog({
		title : 'Cerca Giocatore',
		width : 800,
		height : 550,
		autoOpen : false,
		modal : true,
		resizable : true,
		autoResize : true,
		dialogClass : 'dlg-no-close',
		buttons : {
			'Annulla' : function() {
				jQ(this).dialog('close');
			}
		}
	});	
	
    jQ('.accordion-toggle').on('click', function(event){
    	event.preventDefault();
    	// create accordion variables
    	var accordion = jQ(this);
    	var accordionContent = accordion.parent().next('.accordion-content');
    	var accordionToggleIcon = jQ(this).children('.toggle-icon');
    	
    	// toggle accordion link open class
    	accordion.toggleClass("open");
    	// toggle accordion content
    	accordionContent.slideToggle(250);
    	
    	// change plus/minus icon
    	if (accordion.hasClass("open")) {
    		accordionToggleIcon.html("<i class='fa fa-minus-circle fa-2x'></i>");
    	} else {
    		accordionToggleIcon.html("<i class='fa fa-plus-circle fa-2x'></i>");
    	}
    });	


});

function Player() {
}

// METHODS
Player.prototype = {

		
	/***********************************************************************************
		function confirmDeletePlayer() 
	 ***********************************************************************************/		
	confirmDeletePlayer : function(urlDelete) {
		console.info('chiamata confirmDeletePlayer() in player.js');

		// creare automaticamente il div
		var div = jQ('#dConfirmDeletePlayer').attr('title', 'Vuoi eliminare il giocatore?');
		var p = jQ('<p>Il calciatore sar&agrave eliminato. Sei sicuro?</p>');
		var span = jQ('<span></span>').attr('class', 'ui-icon ui-icon-alert').attr('style', 'float:left; margin:0 7px 20px 0;');
	
		span.prependTo(p);
		p.appendTo(div);		

		jQ('#dConfirmDeletePlayer').dialog({
			resizable : false,
			height : 170,
			modal : true,
			autoOpen : false,
			buttons : {
				'Conferma' : function() {
					console.info('esecuzione dialog deletePlayer in player.js');
					jQ(window.location).attr('href', urlDelete);
				},
				'Annulla' : function() {
					console.info('chiusura dialog deletePlayer in player.js');
					jQ(this).dialog('close');

					p.remove();
					span.remove();
				}
			}
		});		
		
		jQ('#dConfirmDeletePlayer').dialog('open');
		console.info('fine chiamata confirmDeletePlayer() in player.js');
	},
	
	
	/***********************************************************************************
		function confirmUnEmployPlayer() 
	 ***********************************************************************************/		
	confirmUnEmployPlayer : function(urlUnemploy) {	
		console.info('chiamata confirmUnEmployPlayer() in player.js');
		
		// creare automaticamente il div
		var div = jQ('#dConfirmUnemployPlayer').attr('title', 'Vuoi svincolare il giocatore?');
		var p = jQ('<p>Il calciatore sar&agrave svincolato. Sei sicuro?</p>');
		var span = jQ('<span></span>').attr('class', 'ui-icon ui-icon-alert').attr('style', 'float:left; margin:0 7px 20px 0;');
	
		span.prependTo(p);
		p.appendTo(div);		
		
		jQ('#dConfirmUnemployPlayer').dialog({
			resizable : false,
			height : 170,
			modal : true,
			autoOpen : false,
			buttons : {
				'Conferma' : function() {
					console.info('esecuzione dialog unemployPlayer in player.js');
					jQ(window.location).attr('href', urlUnemploy);
				},
				'Annulla' : function() {
					console.info('chiusura dialog unemployPlayer in player.js');
					jQ(this).dialog('close');

					p.remove();
					span.remove();
				}
			}
		});

		jQ('#dConfirmUnemployPlayer').dialog('open');
		console.info('fine chiamata confirmUnEmployPlayer() in player.js');
	},	

	
	/***********************************************************************************
		function confirmRetirePlayer() 
	 ***********************************************************************************/		
	confirmRetirePlayer : function(urlRetire) {	
		console.info('chiamata confirmRetirePlayer() in player.js');

		// creare automaticamente il div
		var div = jQ('#dConfirmRetirePlayer').attr('title', 'Vuoi far ritirare il giocatore?');
		var p = jQ('<p>Il calciatore sar&agrave ritirato. Sei sicuro?</p>');
		var span = jQ('<span></span>').attr('class', 'ui-icon ui-icon-alert').attr('style', 'float:left; margin:0 7px 20px 0;');
	
		span.prependTo(p);
		p.appendTo(div);		

		jQ('#dConfirmRetirePlayer').dialog({
			resizable : false,
			height : 170,
			modal : true,
			autoOpen : false,
			buttons : {
				'Conferma' : function() {
					console.info('esecuzione dialog retirePlayer in player.js');
					jQ(window.location).attr('href', urlRetire);
				},
				'Annulla' : function() {
					console.info('chiusura dialog retirePlayer in player.js');
					jQ(this).dialog('close');

					p.remove();
					span.remove();
				}
			}
		});	

		jQ('#dConfirmRetirePlayer').dialog('open');
		console.info('fine chiamata confirmRetirePlayer() in player.js');
	},
	
	
	/***********************************************************************************
		function confirmTransferPlayer() 
	 ***********************************************************************************/	
	confirmTransferPlayer : function() {	
		console.info('chiamata confirmTransferPlayer() in player.js');

		// creare automaticamente il div
		var div = jQ('#dConfirmTransferPlayer').attr('title', 'Vuoi far trasferire il giocatore?');
		var p = jQ('<p>Il calciatore sar&agrave trasferito. Sei sicuro?</p>');
		var span = jQ('<span></span>').attr('class', 'ui-icon ui-icon-alert').attr('style', 'float:left; margin:0 7px 20px 0;');
	
		span.prependTo(p);
		p.appendTo(div);		

		jQ('#dConfirmTransferPlayer').dialog({
			resizable : false,
			height : 170,
			modal : true,
			autoOpen : false,
			buttons : {
				'Conferma' : function() {
					console.info('esecuzione dialog transferPlayer in player.js');
					if (jQ('input[name=onLoan]').is(':checked')) {
						jQ('#teamOwnerId').val(jQ('#teamBeforeChange').val());
					}
					jQ('#movePlayer').submit();
				},
				'Annulla' : function() {
					console.info('chiusura dialog transferPlayer in player.js');
					jQ(this).dialog('close');
					
					p.remove();					
					span.remove();
				}
			}
		});	

		jQ('#dConfirmTransferPlayer').dialog('open');
		console.info('fine chiamata confirmTransferPlayer() in player.js');
	},	
	
	
	/***********************************************************************************
		function confirmBuyPlayer() 
	 ***********************************************************************************/		
	confirmBuyPlayer : function() {	
		console.info('chiamata confirmBuyPlayer() in player.js');

		// creare automaticamente il div
		var div = jQ('#dConfirmBuyPlayer').attr('title', 'Vuoi acquistare il giocatore?');
		var p = jQ('<p>Il calciatore sar&agrave acquistato. Sei sicuro?</p>');
		var span = jQ('<span></span>').attr('class', 'ui-icon ui-icon-alert').attr('style', 'float:left; margin:0 7px 20px 0;');
	
		span.prependTo(p);
		p.appendTo(div);		

		jQ('#dConfirmBuyPlayer').dialog({
			resizable : false,
			height : 170,
			modal : true,
			autoOpen : false,
			buttons : {
				'Conferma' : function() {
					console.info('esecuzione dialog confirmBuyPlayer in player.js');
					var playerId = jQ('#playerIdTransfer').val();
					var teamId = jQ('#teamIdTransfer').val();
					var isLoan = jQ('#isLoanTransfer').val();
					var statusTransfer = jQ('input[id=statusTransfer]:checked', '#formRicerca').val();	
								
					businessDelegate.updateEntityByParams(playerId, teamId, isLoan, statusTransfer, 'PLAYER', function (data){				
						jQ('#listByTeamFORM').submit();
					});
				},
				'Annulla' : function() {
					console.info('chiusura dialog confirmBuyPlayer in player.js');
					jQ(this).dialog('close');

					p.remove();					
					span.remove();
				}
			}
		});			
		
		jQ('#dConfirmBuyPlayer').dialog('open');
		console.info('fine chiamata confirmBuyPlayer() in player.js');
	},	
	
	
	/***********************************************************************************
		function goEndCareer() 
	 ***********************************************************************************/	
	goEndCareer : function() {
		console.info('chiamata goEndCareer() in player.js');
		jQ('#endCareerPlayer').submit(); 
		console.info('fine chiamata goEndCareer() in player.js');
	},

	
	/***********************************************************************************
		function newPlayer() 
	 ***********************************************************************************/		
	newPlayer : function() {
		console.info('chiamata newPlayer() in player.js');
		jQ('#dEditPlayer').dialog('open');

		jQ('#tabs').tabs();
		jQ('#tabs').tabs("option", "active", 0);

		cleanPlayer();				

		var teamNationID = jQ('#editPlayer input[id=team_nation_id]');
		var teamDivisionID = jQ('#editPlayer input[id=team_division_id]');
		var teamID = jQ('#editPlayer input[id=team_id]');

		var own_nation = jQ('#editPlayer select[id=owner_nation_id]');
		var own_division = jQ('#editPlayer select[id=owner_division_id]');
		var own_team = jQ('#editPlayer select[id=owner_team_id]');
		
		
		// nation id default
		jQ('#editPlayer select[id=nation_id]').val(teamNationID.val());
		
		businessDelegate.getEntitiesBySecondID(own_nation.val(), 'DIVISION', function(data4) {
			
			own_division.empty();
			
			//dwr.util.addOptions('owner_division_id', data4, 'id', 'name');  ------> (vecchia versione dwr)
			jQ.each(data4, function(key, value) {				
				own_division.append('<option value=\"' + value.id + '\">' + value.name + '</option>');
			});				

			// OWNER nation ID
			own_division.val(teamDivisionID.val());

		});	

		businessDelegate.getEntitiesByIDs(teamNationID.val(), teamDivisionID.val(), 'TEAM', function(data3) {

			own_team.empty();
				
			//dwr.util.addOptions('owner_team_id', data3, 'id', 'name');  ------> (vecchia versione dwr)
			jQ.each(data3, function(key, value) {				
				own_team.append('<option value=\"' + value.id + '\">' + value.name + '</option>');
			});							

			// OWNER TEAM ID
			own_team.val(teamID.val());

			controlloPrestito();
		});
		console.info('fine chiamata newPlayer() in player.js');
	},

	
	/***********************************************************************************
		function editPlayer() 
	 ***********************************************************************************/	
	editPlayer : function(playerId) {
		console.info('chiamata editPlayer() in player.js');

		jQ('#dEditPlayer').dialog('open');
		
		// inizializzazione dei tabs
		jQ('#tabs').tabs();
		jQ('#tabs').tabs('option', 'active', 0);
		
		jQ('#button-ok').button('disable');

		cleanPlayer();
		
		businessDelegate.getEntityByID(playerId, 'PLAYER', function(data) {
			var urlImage = jQ('#editPlayer input[name=urlImage]').val();
			jQ('#editPlayer img[id=myImage]').attr('src', urlImage + '?id=' + playerId);

			jQ('#editPlayer input[id=id]').val(playerId);
			jQ('#editPlayer input[id=firstName]').val(data.firstName);
			jQ('#editPlayer input[id=lastName]').val(data.lastName);
			jQ('#editPlayer input[id=completeName]').val(data.completeName);
			jQ('#editPlayer input[id=dateOfBirth]').val(dateToYMD(data.dateOfBirth));
			jQ('#editPlayer input[id=placeOfBirth]').val(data.placeOfBirth);
			jQ('#editPlayer input[id=retired]').val(data.retired);
			jQ('#editPlayer input[id=unemployed]').val(data.unemployed);
			
			if (data.teamPrev != null)
				jQ('#editPlayer input[id=team_prev_id]').val(data.teamPrev.id);

			if(data.team != null)
			{
				jQ('#editPlayer input[id=team_id]').val(data.team.id);
				jQ('#editPlayer input[id=team_nation_id]').val(data.team.nation.id);
				jQ('#editPlayer input[id=team_division_id]').val(data.team.division.id);
			}

			// NATION ID
			jQ('#editPlayer select[id=nation_id]').val(data.nationality.id);


			// NATION2 ID
			if(data.nation2 != null)
				jQ('#editPlayer select[id=nation2_id]').val(data.nationality2.id);

			// RUOLO ID							
			jQ('#editPlayer select[id=ruolo_id]').val(data.position.codRuolo);


			// NATIONAL
			jQ('#editPlayer select[id=national]').val(data.national);

			// TEAM NATION ID
			if(data.team != null)
				jQ('#editPlayer select[id=team_nation_id]').val(data.team.nation.id);

			// TEAM CATEGORY
			jQ('#editPlayer select[id=teamBranch]').val(data.teamBranch);

			// FOOT
			jQ('#editPlayer select[id=foot]').val(data.foot);

			// STATUS
			jQ('#editPlayer select[id=status]').val(data.status);
			
			var ruolo = jQ('#editPlayer select[id=ruolo_id]');
			var position = jQ('#editPlayer select[id=position_id]');
			var locale = jQ('#editPlayer input[id=locale]');
								
			businessDelegate.getEntitiesByParams(ruolo.val(), locale.val(), 'POSITION', function(data2) {
				
				position.empty();
				
				//dwr.util.addOptions('position_id', data2, 'id', 'descPosizione');  ------> (vecchia versione dwr)
				jQ.each(data2, function(key, value) {				
					position.append('<option value=\"' + value.id + '\">' + value.descPosizione + '</option>');
				});
				
				//checkOnSelectByText('editPlayer', 'position_id', data.position.descPosizione);
				position.val(data.position.id);
				//jQ('#position_id').trigger('change');
			});

			//jQ('#editPlayer input[id=nationality]').val(data.nationality.id);
			jQ('#editPlayer input[id=position]').val(data.position.id);
			jQ('#editPlayer input[id=height]').val(data.height);
			jQ('#editPlayer input[id=weight]').val(data.weight);
			jQ('#editPlayer input[id=number]').val(data.number);

			// VALUE
			//checkOnText('editPlayer', 'cost', data.cost);

			// INCOME
			checkOnText('editPlayer', 'grossWeeklySalary', data.grossWeeklySalary);
			checkOnText('editPlayer', 'netAnnualSalary', data.netAnnualSalary);
			
			formatCurrency(jQ('#grossWeeklySalary'));
			formatCurrency(jQ('#netAnnualSalary'));
			//formatCurrency(jQ('#cost'));

			// DATE CONTRACT
			checkOnText('editPlayer', 'contractUntil', data.contractUntil);

			// CAPTAIN
			checkOnCheckBox('editPlayer', 'captain', data.captain);

			// ON LOAN
			if(data.team != null)
				checkOnCheckBox('editPlayer', 'prestito', data.team.id != data.teamOwner.id);

			if(data.teamOwner != null)
			{
				var own_nation = jQ('#editPlayer select[id=owner_nation_id]');
				var own_division = jQ('#editPlayer select[id=owner_division_id]');
				var own_team = jQ('#editPlayer select[id=owner_team_id]');				
				
				own_nation.val(data.teamOwner.nation.id);
				
				businessDelegate.getEntitiesBySecondID(data.teamOwner.nation.id, 'DIVISION', function(data4) {
					
					own_division.empty();

					//dwr.util.addOptions('owner_division_id', data2, 'id', 'name');	------> (vecchia versione dwr)
					jQ.each(data4, function(key, value) {				
						own_division.append('<option value=\"' + value.id + '\">' + value.name + '</option>');
					});
					
					// OWNER nation ID
					own_division.val(data.teamOwner.division.id);
				});	

				businessDelegate.getEntitiesByIDs(data.teamOwner.nation.id, data.teamOwner.division.id, 'TEAM', function(data3) {

					own_team.empty();

					//dwr.util.addOptions('owner_team_id', data3, 'id', 'name');	------> (vecchia versione dwr)
					jQ.each(data3, function(key, value) {				
						own_team.append('<option value=\"' + value.id + '\">' + value.name + '</option>');
					});

					// OWNER TEAM ID
					own_team.val(data.teamOwner.id);

					controlloPrestito();
					
					jQ('#button-ok').button('enable');
				});

			}
			else {
				jQ('#button-ok').button('enable');				
			}

		});	
		console.info('fine chiamata editPlayer() in player.js');
	},
	
	
	/***********************************************************************************
		function buyPlayer() 
	 ***********************************************************************************/	
	buyPlayer : function() {
		console.info('chiamata buyPlayer() in player.js');
		jQ('#iniziale').val('');
		jQ('#buyPlayerResults').hide();	
		
		jQ('#dBuyPlayer').dialog('open');
		console.info('fine chiamata buyPlayer() in player.js');
	},

	
	/***********************************************************************************
		function movePlayer() 
	 ***********************************************************************************/	
	movePlayer : function() {
		console.info('chiamata movePlayer() in player.js');
		jQ('#dMovePlayer').dialog('open');

		businessDelegate.getEntitiesByIDs(jQ('#teamNationId').val(), jQ('#teamDivisionId').val(), 'TEAM', function(data) {
			dwr.util.removeAllOptions('team_id');
			dwr.util.addOptions('team_id', data, 'id', 'name');

			// TEAM ID
			jQ('#movePlayer input[id=team_id]').val(jQ('#teamBeforeChange').val());

		});
		console.info('fine chiamata movePlayer() in player.js');
	},
	
	
	/***********************************************************************************
		function changePlayer() 
	 ***********************************************************************************/		
	changePlayer : function() {
		console.info('chiamata changePlayer() in player.js');
		var playerId = jQ('#view_player_id').val();

		jQ('#id').val(playerId);
		
		jQ('#viewPlayerPageForm').submit(); 
		console.info('fine chiamata changePlayer() in player.js');
	},
	
	
	/***********************************************************************************
		function searchPlayer() 
	 ***********************************************************************************/	
	searchPlayer : function() {
		console.info('chiamata searchPlayer() in player.js');
		jQ('#iniziale').val('');
		jQ('#searchPlayerResults').hide();
		jQ('input:radio[id=type]')[0].checked = true;
		
		console.info('apertura dialog searchPlayer');
		jQ('#dSearchPlayer').dialog('open');
		jQ('#iniziale').val('');
		jQ('#iniziale').focus();
		console.info('fine chiamata searchPlayer() in player.js');
	},
	
	
	/***********************************************************************************
		function execSearchPlayer() 
	 ***********************************************************************************/	
	execSearchPlayer : function(letteraRicerca, message1, message2) {
		console.info('chiamata execSearchPlayer() in player.js');
		var type = jQ('input[id=type]:checked', '#formRicerca').val();			

		if (letteraRicerca != '')
		{			
			
			businessDelegate.getEntitiesByParams(letteraRicerca, type, 'PLAYER', function (data)
			{	
				var tableResult = '<table class=\"standard_tabelle\"><tr><th colspan=\"3\">' + message1 + '</th></tr>';
				tableResult += '<tr><th colspan=\"3\">' + message2 + '</th></tr>';
				
				for (var i = 0; i < data.length; i++) {
					
					if (i % 2 == 0)
						tableResult += '<tr class=\"even\">';
					else 
						tableResult += '<tr class=\"odd\">';	
						
					tableResult += '<td class=\"bg2 alle\">' + data[i].lastName + ' ' + data[i].firstName + ' (' + data[i].position.descPosizione;
					
					if (data[i].team != null){
						tableResult += ' - ' + data[i].team.name.capitalize() + ') ';
					}
					else 
					{
						tableResult += ') ';
					}					
					
					tableResult += 'nato il ' + jQ.datepicker.formatDate("dd/mm/yy", data[i].dateOfBirth) + ' a ' + data[i].placeOfBirth + '</td>';
					tableResult += '<td colspan=\"2\" class=\"bg2 alle\" style=\"text-align: center\"><a href=\"#\" onclick=\"player.execViewPlayer(' + data[i].id + ');\">Visualizza</a></td>';
				}
				
				tableResult += '</tr></table>';
				
				jQ('#searchPlayerResults').html(tableResult);
				
				jQ('#searchPlayerResults').show();				
						
			});	
		}
		else 
		{
			alert("Inserire il giocatore da trovare...");
			jQ('#searchPlayerResults').hide();	
		}
		console.info('fine chiamata execSearchPlayer() in player.js');
	},
	
	/***********************************************************************************
		function execViewPlayer() 
	 ***********************************************************************************/	
	execViewPlayer : function(playerId) {
		console.info('chiamata execViewPlayer() in player.js');
		jQ('#searchPlayerPageForm input[id=id]').val(playerId);
		jQ('#searchPlayerPageForm').submit(); 		
		console.info('fine chiamata execViewPlayer() in player.js');
	}	
	
}

var player = new Player();


/***********************************************************************************
							FUNZIONI PUBBLICHE
***********************************************************************************/
function cleanPlayer() {
	console.info('chiamata cleanPlayer() in player.js');
	var lastName = jQ('#editPlayer input[id=lastName]');
	var birthDate = jQ('#editPlayer input[id=birthDate]')
	var allFields = jQ([]).add(lastName).add(birthDate);

	jQ('.validateTips').hide();
	allFields.removeClass('ui-state-error');

	jQ('#editPlayer img[id=myImage]').attr('src', '../images/players/notFound.jpg');

	jQ('#editPlayer :text').val('');
	//jQ('#editPlayer input[id=id]').val('');
	jQ('#editPlayer input[id=height]').val('?');
	jQ('#editPlayer input[id=weight]').val('?');
	jQ('#editPlayer input[id=contractUntil]').val('?');
	jQ('#editPlayer input[id=captain]').attr('checked', false);
	jQ('#editPlayer select[id=ruolo_id]').val(jQ('#editPlayer select[id=ruolo_id] option:first').val());
	
	var codRuolo = jQ('#editPlayer select[id=ruolo_id]');
	var position = jQ('#editPlayer select[id=position_id]');
	var locale = jQ('#editPlayer input[id=locale]');
	
	businessDelegate.getEntitiesByParams(codRuolo.val(), locale.val(), 'POSITION', function(data) {
		
		position.empty();
		
		//dwr.util.addOptions('position_id', data, 'id', 'descPosizione');  ------> (vecchia versione dwr)
		jQ.each(data, function(key, value) {				
			position.append('<option value=\"' + value.id + '\">' + value.descPosizione + '</option>');
		});
		
		position.trigger('change');
	});
	

	jQ('#editPlayer select[id=owner_team_id]').val(jQ('#editPlayer input[id=team_id]').val());
	jQ('#editPlayer select[id=owner_nation_id]').val(jQ('#editPlayer input[id=team_nation_id]').val());
	jQ('#editPlayer select[id=owner_division_id]').val(jQ('#editPlayer input[id=team_division_id]').val());
	
	// CAPTAIN
	checkOnCheckBox('editPlayer', 'prestito', false);
	

	jQ('#rowPrestito').hide();
	jQ('#rowPrestito2').hide();
	console.info('fine chiamata cleanPlayer() in player.js');
}

function controlloPrestito() {
	console.info('chiamata controlloPrestito() in player.js');
	if(jQ('#editPlayer input[id=prestito]').is(':checked')) {
		jQ('#rowPrestito').show();	
		jQ('#rowPrestito2').show();	
	}
	else {
		jQ('#rowPrestito').hide();	
		jQ('#rowPrestito2').hide();	
	}
	console.info('fine chiamata controlloPrestito() in player.js');
}

function searchBuyPlayer()
{
	var letteraRicerca = jQ("#iniziale").val();

	if (letteraRicerca != '')
	{
		businessDelegate.getEntitiesByParams(letteraRicerca, "ALL", "PLAYER", function (data)
		{	
		
			var str = "<table class=\"standard_tabelle\" style=\"width: 730px\">";
			str += "<tr><th colspan=\"3\">Risultati ottenuti:</th></tr>";
			str += "<tr><th>Clicca su un nome per acquistare il giocatore:</th>";
			str += "<th>EST <input type=\"radio\" name=\"statusTransfer\" id=\"statusTransfer\" value=\"1\" checked></th>";
			str += "<th>INV <input type=\"radio\" name=\"statusTransfer\" id=\"statusTransfer\" value=\"2\"></th></tr>";

			
			for (var i = 0; i < data.length; i++) {
				
				if (i % 2 == 0)
					str += "<tr class='even'>";
				else 
					str += "<tr class='odd'>";	
					
				str += "<td class=\"bg2 alle\">" + data[i].lastName + " " + data[i].firstName + " (" + data[i].position.descPosizione;
				
				if (data[i].team != null){
					str += " - " + data[i].team.name.capitalize() + ") ";
				}
				else 
				{
					str += ") ";
				}					
				
				str += "nato il " + jQ.datepicker.formatDate("dd/mm/yy", data[i].dateOfBirth) + " a " + data[i].placeOfBirth;
				str += "</td>";

				if (data[i].team != null && data[i].team.id != jQ("#teamId").val())
				{
					str += "<td class=\"bg2 alle\" style=\"text-align: center\"><a href=\"#\" onclick='acquistaGiocatore(" + data[i].id + ", " + jQ("#teamId").val() + ", false);'>Acquisto</a></td>";				
					str += "<td class=\"bg2 alle\" style=\"text-align: center\"><a href=\"#\" onclick='acquistaGiocatore(" + data[i].id + ", " + jQ("#teamId").val() + ", true);'>Prestito</a></td>";				
				}
				else 
				{
					str += "<td class=\"bg2 alle\" style=\"text-align: center\">&nbsp;</td>";				
					str += "<td class=\"bg2 alle\" style=\"text-align: center\">&nbsp;</td>";									
				}
			}
			
			str += "</tr></table>"
			
			jQ("#buyPlayerResults").html(str);
			
			jQ("#buyPlayerResults").show();				
					
		});	
	}
	else 
	{
		alert("Inserire il giocatore da trovare...");
		jQ("#buyPlayerResults").hide();	
	}		
}

function acquistaGiocatore(playerId, teamId, isLoan)
{	
	jQ("#playerIdTransfer").val(playerId);
	jQ("#teamIdTransfer").val(teamId);
	jQ("#isLoanTransfer").val(isLoan);		

	player.confirmBuyPlayer();
}