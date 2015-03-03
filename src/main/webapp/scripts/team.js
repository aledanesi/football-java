/*******************************************************************
					teams javascript file 
*******************************************************************/

/* var jQ = $.noConflict(); */
var jQ = jQuery.noConflict();

/* jQ(function() {  .... } */
jQ(document).ready(function() {	
	 

	/***********************************************************************************
						chiamata change di 'continent_id' in team.js
	 ***********************************************************************************/
	
	 jQ('#listTeam select[id=continent_id]').selectmenu({
		change: function( event, ui ) {
			console.info('----> continent_id--->change() in team.js');	
			
			var continent = jQ('#listTeam select[id=continent_id]');
			var nation = jQ('#listTeam select[id=nation_id]');

			businessDelegate.getEntitiesBySecondID(continent.val(), 'NATION', function(data) {			
				console.log('lista di nation recuperate...');
				nation.empty();
						
				//dwr.util.addOptions('nation_id', data, 'id', 'name');  ------> (vecchia versione dwr)
				jQ.each(data, function(key, value) {				
					nation.append('<option value=\"' + value.id + '\">' + value.name + '</option>');
				});
				console.log('select nation_id riempita.');
				
				nationChanged();
			});
			console.info('<---- continent_id--->change() in team.js');	
		}
	});		
	
	
	/***********************************************************************************
						chiamata change di 'nation_id' in team.js
	 ***********************************************************************************/	
	 jQ('#listTeam select[id=nation_id]').selectmenu({
		change: function( event, ui ) {
			console.info('----> nation_id--->change() in team.js');
			
			nationChanged();
			
			console.info('<---- nation_id--->change() in team.js');		
		}
	});	
	
	 jQ('#listTeam select[id=nation_id]').selectmenu('menuWidget').addClass('overflow');

	
	/***********************************************************************************
						chiamata change di 'division_id' in team.js
	 ***********************************************************************************/
	 jQ('#listTeam select[id=division_id]').selectmenu({
		change: function( event, ui ) {
			console.info('----> division_id--->change() in team.js');
			
			//event.preventDefault();
			//event.stopPropagation();
			
			jQ('form[name=saison]').submit();  
			
			console.info('<---- division_id--->change() in team.js');		
		}
	});
	
	jQ('#editTeam select[id=division_id]').selectmenu({
		close: function( event, ui ) {
			jQ('.ui-dialog').css('zIndex', '101');
		},
		open: function( event, ui ) {
			jQ('.ui-dialog').css('zIndex', '101');
		}		
	});

	jQ('#editTeam select[id=division_id]').selectmenu('menuWidget').parent().css('z-index', '9999');
	
	/***********************************************************************************
						chiamata click della classe 'targetLinkTeam' in team.js
	 ***********************************************************************************/	
	jQ('a.targetLinkTeam').click(function(event) {	
		console.info('----> a.targetLinkTeam--->click() in team.js');

		jQ('#viewPlayersForm input[id=teamId]').val(jQ(this).attr('data-team'));

		event.preventDefault();
		event.stopPropagation();

		jQ('#viewPlayersForm').submit();  
		console.info('<---- a.targetLinkTeam--->click() in team.js');
	});	
		
	
	/***********************************************************************************
							creation dialog for 'new/edit' team page
	 ***********************************************************************************/	
	jQ('#dEditTeam').dialog({
		title : 'Modifica Squadra',
		width : 500,
		height : 550,
		autoOpen : false,
		modal : true,
		resizable : true,
		autoResize : true,
		dialogClass : 'dlg-no-close',
		buttons : {
			'Salva': function() {
				console.info('esecuzione dialog newTeam/editTeam in team.js');
				jQ('.validateTips').show();

				var bValid = true;

				var name = jQ('#name');
				var allFields = jQ( [] ).add( name );
				
				allFields.removeClass('ui-state-error');
				bValid = bValid && checkLength( name, 'Nome' );
				if ( bValid ) {
					jQ('.validateTips').hide();
					jQ('#editTeam').submit();
				}
			},
			'Annulla' : function() {
				console.info('chiusura dialog newTeam/editTeam in team.js');
				
				jQ(this).dialog('close');
			}
		}
	});

});

function Team() {}

// METHODS
Team.prototype = {

		
	/***********************************************************************************
								function confirmDeleteTeam() 
	***********************************************************************************/		
	confirmDeleteTeam : function(urlDelete) {
		console.info('----> confirmDeleteTeam() in team.js');

		// creare automaticamente il div
		var div = jQ('#dConfirmDeleteTeam').attr('title', 'Vuoi eliminare la squadra?');
		var p = jQ('<p>La squadra sar&agrave eliminata. Sei sicuro?</p>');
		var span = jQ('<span></span>').attr('class', 'ui-icon ui-icon-alert').attr('style', 'float:left; margin:0 7px 20px 0;');
	
		span.prependTo(p);
		p.appendTo(div);		
		
		jQ('#dConfirmDeleteTeam').dialog({
			resizable : false,
			height : 170,
			autoOpen : false,
			modal : true,
			buttons : {
				'Elimina' : function() {
					console.info('esecuzione dialog deleteTeam in team.js');
					jQ(window.location).attr('href', urlDelete);
				},
				'Annulla' : function() {
					console.info('chiusura dialog deleteTeam in team.js');
					jQ(this).dialog('close');
					
					p.remove();
					span.remove();
				}
			}
		});
		
		
		console.info('aperura dialog delete team');
		jQ('#dConfirmDeleteTeam').dialog('open');
		console.info('<---- confirmDeleteTeam() in team.js');
	},

	
	/***********************************************************************************
								function newTeam()
	 ***********************************************************************************/	
	newTeam : function() {
		console.info('----> newTeam() in team.js');
		
		console.info('apertura dialog newTeam/editTeam in team.js');
		jQ('#dEditTeam').dialog('open');

		cleanTeam();
		console.info('<---- newTeam() in team.js');
	},
	
	
	/***********************************************************************************
								function editTeam()
	 ***********************************************************************************/	
	editTeam : function(teamId) {
		console.info('----> editTeam() in team.js');
		
		console.info('apertura dialog newTeam/editTeam in team.js');
		jQ('#dEditTeam').dialog('open');

		cleanTeam();
			
		businessDelegate.getEntityByID(teamId, 'TEAM', function(data) {
			var urlImage = jQ('#editTeam input[name=urlImage]').val();
			jQ('#editTeam img[id=myImage]').attr('src', urlImage + '?id=' + teamId);

			jQ('#editTeam input[id=id]').val(teamId);
			jQ('#editTeam input[id=name]').val(data.name);
			jQ('#editTeam input[id=founded]').val(data.founded);
			jQ('#editTeam input[id=city]').val(data.city);
			jQ('#editTeam input[id=address]').val(data.address);
			jQ('#editTeam input[id=email]').val(data.email);
			jQ('#editTeam input[id=webSite]').val(data.webSite);
			jQ('#editTeam input[id=stadium]').val(data.stadium);
			jQ('#editTeam input[id=posti]').val(data.posti);
			jQ('#editTeam input[id=colorTeam]').val(data.colorTeam);

			jQ('#editTeam select[id=division_id]').val(data.division.id);
			jQ('#editTeam select[id=division_id]').selectmenu("refresh", true);

		});
		console.info('<---- editTeam() in team.js');
	}
	
	
};


var team = new Team();


/***********************************************************************************
					FUNZIONI PUBBLICHE
***********************************************************************************/	

function cleanTeam() {
	console.info('----> cleanTeam() in team.js');
	var name = jQ('#editTeam hidden[id=name]');
	var allFields = jQ([]).add(name);

	jQ('.validateTips').hide();
	allFields.removeClass('ui-state-error');
	
	jQ('#editTeam img[id=myImage]').attr('src','../images/players/notFound.jpg');
	
	jQ('#editTeam select[id=division_id]').val(jQ('#listTeam select[id=division_id] option:selected').val());
	jQ('#editTeam select[id=division_id]').selectmenu("refresh", true);	

	jQ('#editTeam :text').val('');
	jQ('#editTeam input[id=id]').val('');
	
	console.info('<---- cleanTeam() in team.js');
}

function nationChanged() {
	console.info('----> nationChanged() in team.js');	

	var nation = jQ('#listTeam select[id=nation_id]');
	var division = jQ('#listTeam select[id=division_id]');
	
	businessDelegate.getEntitiesBySecondID(nation.val(), 'DIVISION', function(data) 
	{
		console.log('lista di division recuperate...');
		
		var division = jQ('#listTeam select[id=division_id]');
		
		division.empty();

		//sostituita la chiamata di dwr util con una classica funzione jquery			
		//dwr.util.addOptions('division_id', data, 'id', 'name');
		jQ.each(data, function(key, value) 
		{				
			division.append('<option value=\"' + value.id + '\">' + value.name + '</option>');
		});

		division.selectmenu("refresh", true);
		console.log('select division_id riempita.');

		
		jQ('form[name=saison]').submit();
	});	
	console.info('<---- nationChanged() in team.js');	
}

/*********************************************
				NOT LONGER USED
*********************************************/

/*function changeTeam() {
var team = dwr.util.getValue('team_id');
jQ('#teamOwnerId').val(team);
}

function changeTeamOwner() {
var team = dwr.util.getValue('owner_team_id');
jQ('#teamOwnerId').val(team);
}*/