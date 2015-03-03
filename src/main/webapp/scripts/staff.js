var jQ = jQuery.noConflict();

/* jQ(function() {  .... } */
jQ(document).ready(function() {	

	jQ("#dEditStaff").dialog({
		title : "Modifica Staff",
		width : 500,
		height : 550,
		autoOpen : false,
		modal : true,
		resizable : true,
		autoResize : true,
		dialogClass : "dlg-no-close",
		buttons : [
			{
				id: "button-ok",
				text: "Salva",
				click : function() {
					jQ(".validateTips").show();

					var bValid = true;

					var firstName = jQ('#editStaff input[id=firstName]');
					var lastName = jQ('#editStaff input[id=lastName]');
					var dateOfBirth = jQ('#editStaff input[id=dateOfBirth]');

					var allFields = jQ([]).add(lastName).add(dateOfBirth);

					allFields.removeClass("ui-state-error");

					bValid = bValid && checkLength(lastName, "Cognome");
					bValid = bValid && checkLength(dateOfBirth, "Data di Nascita");

					if (bValid) {
						jQ(".validateTips").hide();
						
						if(jQ('#editStaff input[id=id]').val() == '' )
						{
							businessDelegate.findEntityExists(firstName.val(), lastName.val(), dateOfBirth.val(), "STAFF", function (data)
							{
								if(data == true)
								{
									alert("Attenzione: il manager è già presente nel db!");						
								}
								else 
								{
									jQ("#editStaff").submit();						
								}
							});					
						}
						else 
						{
							jQ("#editStaff").submit();
						}
					}
				}
			},
			{
				id: "button-cancel",
				text: "Annulla",			
				click : function() {
					cleanStaff();

					jQ(this).dialog("close");
				}
			}
		]
	});
	
	jQ("#dBuyStaff").dialog({
		title : "Acquista Staff",
		width : 800,
		height : 550,
		autoOpen : false,
		modal : true,
		resizable : true,
		autoResize : true,
		dialogClass : "dlg-no-close",
		buttons : {
			"Annulla" : function() {
				cleanStaff();

				jQ(this).dialog("close");
			}
		}
	});		

	
});

function Staff() {
}

// METHODS
Staff.prototype = {

	newStaff : function() {
		
		jQ('#dEditStaff').dialog('open');
		
		jQ('#tabs_staff').tabs();
		jQ('#tabs_staff').tabs("option", "active", 0);			

		cleanStaff();

		var nationID = jQ('#editStaff input[id=team_nation_id]').val();
		var divisionID = jQ('#editStaff input[id=team_division_id]').val();
		var teamID = jQ('#editStaff input[id=team_id]').val();

		var nation = jQ("#owner_nation_id").val();
		
		// nation id default
		jQ("#editStaff select[id=nation_id] option").each(function() {
			if (jQ(this).val() == jQ("#team_nation_id").val())
				jQ(this).attr("selected", "selected");
		});
				
	},	

	editStaff : function(staffId) {

		jQ('#dEditStaff').dialog('open');
		
		jQ('#tabs_staff').tabs();
		jQ('#tabs_staff').tabs("option", "active", 0);		
		
		jQ("#button-ok").button("disable");

		cleanStaff();
		
		businessDelegate.getEntityByID(staffId, "STAFF",
						function(data) {
							var urlImage = jQ('#editStaff input[name=urlImage]').val();
							jQ('#editStaff img[id=myImage]').attr('src', urlImage + "?id=" + staffId);

							jQ('#editStaff input[id=id]').val(staffId);
							jQ('#editStaff input[id=firstName]').val(data.firstName);
							jQ('#editStaff input[id=lastName]').val(data.lastName);
							jQ('#editStaff input[id=dateOfBirth]').val(dateToYMD(data.dateOfBirth));
							jQ('#editStaff input[id=placeOfBirth]').val(data.placeOfBirth);

							//jQ('#editStaff input[id=retired]').val(data.retired);
							//jQ('#editStaff input[id=unemployed]').val(data.unemployed);

							//if (data.teamPrev != null)
							//  jQ('#editStaff input[id=team_prev_id]').val(data.teamPrev.id);

							if(data.team != null)
							{
								jQ('#editStaff input[id=staff_team_id]').val(data.team.id);
								jQ('#editStaff input[id=staff_team_nation_id]').val(data.team.nation.id);
								jQ('#editStaff input[id=staff_team_division_id]').val(data.team.division.id);
							}


							// NATION ID
							jQ("#staff_nation_id").val(data.nationality.id);

							// NATION2 ID
							if(data.nation2 != null)
								jQ("#staff_nation2_id").val(data.nationality2.id);

							// TEAM NATION ID
							if(data.team != null)
								//checkOnSelectByVal('editPlayer', 'team_nation_id', data.team.nation.name);
								jQ("#staff_team_nation_id").val(data.team.nation.id);
								
							// TEAM CATEGORY
							jQ('#editStaff select[id=staff_teamBranch]').val(data.teamBranch);								

							// INCOME
							checkOnText('editStaff', 'staff_grossWeeklySalary', data.grossWeeklySalary);
							checkOnText('editStaff', 'staff_netAnnualSalary', data.netAnnualSalary);
							
							formatCurrency(jQ("#staff_grossWeeklySalary"));
							formatCurrency(jQ("#staff_netAnnualSalary"));

							// DATE CONTRACT
							checkOnText('editStaff', 'contractUntil', data.contractUntil);

						});	
				
	},	

	buyStaff : function() {
		
		//jQ("#iniziale").val("");
		
		jQ('#dBuyStaff').dialog('open');
	
	}

}

var staff = new Staff();

function cleanStaff() {
	var lastName = jQ('#editStaff input[id=lastName]')
	var birthDate = jQ('#editStaff input[id=birthDate]')
	var allFields = jQ([]).add(lastName).add(birthDate);

	jQ(".validateTips").hide();
	allFields.removeClass("ui-state-error");

	jQ('#editStaff img[id=myImage]').attr('src', '../images/players/notFound.jpg');
	jQ('#editStaff input[id=id]').val('');
	jQ('#editStaff input[id=firstName]').val('');
	jQ('#editStaff input[id=lastName]').val('');
	//jQ('#editStaff input[id=completeName]').val('');
	jQ('#editStaff input[id=dateOfBirth]').val('');
	jQ('#editStaff input[id=placeOfBirth]').val('');
	jQ('#editStaff input[id=nationality]').val('');
	//jQ('#editStaff input[id=cost]').val('');
	jQ('#editStaff input[id=netAnnualSalary]').val('');
	jQ('#editStaff input[id=contractUntil]').val('?');

	/*jQ("#editStaff select[id=ruolo_id] option").each(function() {
		if (jQ(this).val() == '01')
			jQ(this).attr("selected", "selected");
	});*/
	
}