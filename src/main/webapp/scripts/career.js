var jQ = jQuery.noConflict();

jQ(document).ready(function() {     
	jQ( "#squadra" ).autocomplete({        
		source: '${pageContext.request.contextPath}/careers/get_team_list'    
	});
});		

jQ(function() {	
	
	jQ("#dEditCareer").dialog({
		title : "Inserisci un anno di career",
		width : 700,
		height : 500,
		autoOpen : false,
		modal : true,
		resizable : true,
		autoResize : true,
		dialogClass : "dlg-no-close",
		buttons : {
			"Conferma" : function() {
				jQ( ".validateTips" ).show();

				var bValid = true;

				var squadra = jQ("#squadra");
				var periodo = jQ("#periodo");
				var serie = jQ("#serie");

				var allFields = jQ([]).add(squadra).add(periodo).add(serie);
				
				allFields.removeClass( "ui-state-error" );
				
				bValid = bValid && checkLength(squadra, "Squadra" );
				bValid = bValid && checkLength(periodo, "Periodo");
				bValid = bValid && checkLength(serie, "Serie");				
				
				if ( bValid ) {
					jQ( ".validateTips" ).hide();
					jQ("#editCareer").submit();
				}				
			},
			"Annulla" : function() {
				jQ(this).dialog("close");
			}
		}
	});
	
	jQ("#dDeleteCareer").dialog({
		resizable : false,
		height : 170,
		modal : true,
		autoOpen : false,
		buttons : {
			"Conferma" : function() {
				jQ(window.location).attr('href', jQ("#urlDeleteCareer").val());
			},
			"Annulla" : function() {
				jQ(this).dialog("close");
			}
		}
	});		
	
});

function Career() {}

//METHODS
Career.prototype = {
		
		validateCareer : function()
		{
			var seasonName = jQ("#stagione").val();
			businessDelegate.getEntityByDesc(seasonName, "SEASON", function(data){
				jQ("#stagioneID").val(data.id);
			});
		},	
		
		insertCareer : function(playerId) {
			
			cleanCareer();
			
			jQ('#dEditCareer').dialog('open');
		},	
		
		updateCareer : function(playerId, careerId) {

			cleanCareer();

			jQ('#dEditCareer').dialog('open');

			businessDelegate.getEntityByID(careerId, "CAREER", function(data)
			{
				jQ('#editCareer input[id=id]').val(data.id);
				
				jQ('#editCareer input[id=squadra]').val(data.squadra);
				jQ('#editCareer input[id=stagioni]').val(data.stagioni);
				jQ('#editCareer input[id=periodo]').val(data.periodo);
				jQ('#editCareer input[id=serie]').val(data.serie);			
				jQ('#editCareer input[id=presenze]').val(data.presenze);
				jQ('#editCareer input[id=reti]').val(data.reti);
				if (data.onLoan == true)
				{
					//jQ('#editPlayer input[name=captain]').attr('checked', 'true');		
					document.getElementById("prestito").checked = true;
				}
				else
				{
					//jQ('#editPlayer input[name=prestito]').attr('checked', 'false');					
					document.getElementById("prestito").checked = false;
				}			
				
			});
		},	

		confirmDeleteCareer : function(urlDelete) {
			jQ("#urlDeleteCareer").val(urlDelete);

			jQ('#dDeleteCareer').dialog('open');
		}			
		
}

var career = new Career();

function cleanCareer()
{
		jQ('#editCareer input[name=id]').val('');
		jQ('#editCareer input[id=squadra]').val('');
		jQ('#editCareer input[id=stagioni]').val('');
		jQ('#editCareer input[id=periodo]').val('');
		jQ('#editCareer input[id=serie]').val('');
		jQ('#editCareer input[id=presenze]').val('');
		jQ('#editCareer input[id=reti]').val('');
		document.getElementById("prestito").checked = false;
}