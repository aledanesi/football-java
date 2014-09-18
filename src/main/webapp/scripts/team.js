var jQ = jQuery.noConflict();

jQ(function() {	
		
	jQ("#continent_id").change(function() {

		var continent = jQ("#continent_id").val();

		footballManager.getNationsFromContinent(continent, function(data) {
			jQ( "#nation_id" ).empty();
			dwr.util.addOptions("nation_id", data, "id", "name");

			var nation = jQ("#nation_id").val();
			
			footballManager.getDivisionsByNation(nation, function(data) {

				jQ( "#division_id" ).empty();
	
				dwr.util.addOptions("division_id", data, "id", "name");
	
				if (data.length == 0)
				{
					jQ("#butSearch").attr("disabled", "disabled");
				}
				else 
				{
					jQ("#butSearch").removeAttr("disabled");				

					jQ( "#division_id" ).trigger("change");				
	
				}
			});			
			
			
		});

	});		
	
	jQ("#nation_id").change(function() {
		var nation = jQ("#nation_id").val();

		footballManager.getDivisionsByNation(nation, function(data) {

			jQ( "#division_id" ).empty();

			dwr.util.addOptions("division_id", data, "id", "name");

			if (data.length == 0)
			{
				jQ("#butSearch").attr("disabled", "disabled");
			}
			else 
			{
				jQ("#butSearch").removeAttr("disabled");				

				jQ( "#division_id" ).trigger("change");				

			}
		});

		jQ("#division_id").bind("change", function() {

			jQ('form[name="saison"]').submit();  
		});	

	});

	jQ("#division_id").bind("change", function() {
		
		jQ("#continent_id").val(jQ( "#continent_id option:selected" ).val());
		
		jQ('form[name="saison"]').submit();  
	});	
	
	
	jQ( "a.targetLinkTeam" ).click(function() 
	{  		
		jQ("#teamId").val(jQ(this).attr("data-team"));
		jQ("#viewPlayersForm").submit();  
	});
	
			
	jQ("#dEditTeam").dialog({
		title : "Modifica Squadra",
		width : 550,
		height : 600,
		autoOpen : false,
		modal : true,
		resizable : true,
		autoResize : true,
		dialogClass : "dlg-no-close",
		buttons : {
			"Salva": function() {
				jQ( ".validateTips" ).show();

				var bValid = true;

				var name = jQ( "#name" );
				var allFields = jQ( [] ).add( name );
				
				allFields.removeClass( "ui-state-error" );
				bValid = bValid && checkLength( name, "Nome" );
				if ( bValid ) {
					jQ( ".validateTips" ).hide();
					jQ("#editTeam").submit();
				}
			},
			"Annulla" : function() {
				cleanTeam();
				
				jQ(this).dialog("close");
			}
		}
	});

	jQ("#dDeleteTeam").dialog({
		resizable : false,
		height : 170,
		autoOpen : false,
		modal : true,
		buttons : {
			"Elimina" : function() {
				jQ(window.location).attr('href', jQ("#urlDeleteTeam").val());
			},
			"Annulla" : function() {
				jQ(this).dialog("close");
			}
		}
	});

});

function Team() {}

// METHODS
Team.prototype = {

	confirmDeleteTeam : function(urlDelete) {
		jQ("#urlDeleteTeam").val(urlDelete);

		jQ('#dDeleteTeam').dialog('open');
	},

	newTeam : function() {

		jQ('#dEditTeam').dialog('open');

		cleanTeam();

	},
	editTeam : function(teamId) {

		jQ('#dEditTeam').dialog('open');

		cleanTeam();
			
		footballManager.getTeamByID(teamId, function(data) {
			var urlImage = jQ('#editTeam input[name=urlImage]').val();
			jQ('#editTeam img[id=myImage]').attr('src', urlImage + "?id=" + teamId);

			setTextByName('editTeam', 'id', teamId);
			setTextByName('editTeam', 'name', data.name);
			setTextByName('editTeam', 'founded', data.founded);
			setTextByName('editTeam', 'city', data.city);
			setTextByName('editTeam', 'address', data.address);
			setTextByName('editTeam', 'email', data.email);
			setTextByName('editTeam', 'webSite', data.webSite);
			setTextByName('editTeam', 'stadium', data.stadium);
			setTextByName('editTeam', 'posti', data.posti);
			setTextByName('editTeam', 'colorTeam', data.colorTeam);

			// DIVISION ID
			checkOnSelectByText('editTeam', 'division_id', data.division.name);

			// NATION ID
			checkOnSelectByText('editTeam', 'nation_id', data.nation.name);

		});
	}
};

var team = new Team();

function cleanTeam()
{
		var name = jQ( "#name" ),
		allFields = jQ( [] ).add( name );
	
		jQ( ".validateTips" ).hide();
		allFields.removeClass( "ui-state-error" );
		
		jQ('#editTeam img[id=myImage]').attr('src','../images/players/notFound.jpg');

		cleanTextByName('editTeam', 'id');
		cleanTextByName('editTeam', 'name');
		cleanTextByName('editTeam', 'founded');
		cleanTextByName('editTeam', 'address');
		cleanTextByName('editTeam', 'email');
		cleanTextByName('editTeam', 'webSite');
		cleanTextByName('editTeam', 'stadium');
		cleanTextByName('editTeam', 'posti');
		cleanTextByName('editTeam', 'colorTeam');	
}


function changeTeam()
{
	var team = dwr.util.getValue("team_id");
	jQ("#teamOwnerId").val(team);
}

function changeTeamOwner()
{
	var team = dwr.util.getValue("owner_team_id");
	jQ("#teamOwnerId").val(team);
}