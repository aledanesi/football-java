/*******************************************************************
					career javascript file 
*******************************************************************/

/* var jQ = $.noConflict(); */
var jQ = jQuery.noConflict();

/* jQ(function() {  .... } */
jQ(document).ready(function() {	
	
	
	/***********************************************************************************
					autocomplete for team
	 ***********************************************************************************/	
	jQ('#squadra').autocomplete({        
		source: '${pageContext.request.contextPath}/careers/get_team_list'    
	});
	
	
	/***********************************************************************************
		creation dialog for 'new/edit' career page
	 ***********************************************************************************/	
	jQ('#dEditCareer').dialog({
		title : 'Inserisci un anno di career',
		width : 700,
		height : 500,
		autoOpen : false,
		modal : true,
		resizable : true,
		autoResize : true,
		dialogClass : 'dlg-no-close',
		buttons : {
			'Conferma' : function() {
				console.info('esecuzione dialog editCareer in career.js');
				jQ('.validateTips').show();

				var bValid = true;

				var squadra = jQ('#squadra');
				var periodo = jQ('#periodo');
				var serie = jQ('#serie');

				var allFields = jQ([]).add(squadra).add(periodo).add(serie);
				
				allFields.removeClass('ui-state-error');
				
				bValid = bValid && checkLength(squadra, 'Squadra');
				bValid = bValid && checkLength(periodo, 'Periodo');
				bValid = bValid && checkLength(serie, 'Serie');				
				
				if ( bValid ) {
					jQ('.validateTips').hide();
					jQ('#editCareer').submit();
				}				
			},
			'Annulla' : function() {
				console.info('chiusura dialog editCareer in career.js');
				jQ(this).dialog('close');
			}
		}
	});
		
});

function Career() {}

//METHODS
Career.prototype = {
		

		/***********************************************************************************
			function validateCareer() 
		 ***********************************************************************************/		
		validateCareer : function() {
			console.info('chiamata validateCareer() in career.js');
			var seasonName = jQ('#stagione').val();
			businessDelegate.getEntityByDesc(seasonName, 'SEASON', function(data){
				jQ('#stagioneID').val(data.id);
			});
			console.info('fine chiamata validateCareer() in career.js');
		},	
		
		
		/***********************************************************************************
			function insertCareer() 
		 ***********************************************************************************/		
		insertCareer : function(playerId) {
			console.info('chiamata insertCareer() in career.js');
			cleanCareer();
			
			jQ('#dEditCareer').dialog('open');
			console.info('fine chiamata insertCareer() in career.js');
		},	
		
		
		/***********************************************************************************
			function updateCareer() 
		 ***********************************************************************************/		
		updateCareer : function(playerId, careerId) {
			console.info('chiamata updateCareer() in career.js');
			cleanCareer();

			jQ('#dEditCareer').dialog('open');

			businessDelegate.getEntityByID(careerId, 'CAREER', function(data)
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
					document.getElementById('prestito').checked = true;
				}
				else
				{
					//jQ('#editPlayer input[name=prestito]').attr('checked', 'false');					
					document.getElementById('prestito').checked = false;
				}			
				
			});
			console.info('fine chiamata updateCareer() in career.js');
		},	

		
		/***********************************************************************************
			function confirmDeleteCareer() 
		 ***********************************************************************************/		
		confirmDeleteCareer : function(urlDelete) {
			console.info('chiamata confirmDeleteCareer() in career.js');

			// creare automaticamente il div
			var div = jQ('#dConfirmDeleteCareer').attr('title', 'Vuoi eliminare l\'anno di carriera?');
			var p = jQ('<p>L\'anno di carriera del calciatore sar&agrave eliminato. Sei sicuro?</p>');
			var span = jQ('<span></span>').attr('class', 'ui-icon ui-icon-alert').attr('style', 'float:left; margin:0 7px 20px 0;');
		
			span.prependTo(p);
			p.appendTo(div);		

			jQ('#dConfirmDeleteCareer').dialog({
				resizable : false,
				height : 170,
				modal : true,
				autoOpen : false,
				buttons : {
					'Conferma' : function() {
						console.info('esecuzione dialog deleteCareer in career.js');
						jQ(window.location).attr('href', urlDelete);
					},
					'Annulla' : function() {
						console.info('chiusura dialog deleteCareer in career.js');
						jQ(this).dialog('close');
						
						p.remove();					
						span.remove();						
					}
				}
			});		

			jQ('#dConfirmDeleteCareer').dialog('open');
			console.info('fine chiamata confirmDeleteCareer() in career.js');
		}			
		
}

var career = new Career();


/***********************************************************************************
						FUNZIONI PUBBLICHE
***********************************************************************************/
function cleanCareer() {
	console.info('chiamata cleanTeam() in team.js');
	jQ('#editCareer input[name=id]').val('');
	jQ('#editCareer input[id=squadra]').val('');
	jQ('#editCareer input[id=stagioni]').val('');
	jQ('#editCareer input[id=periodo]').val('');
	jQ('#editCareer input[id=serie]').val('');
	jQ('#editCareer input[id=presenze]').val('');
	jQ('#editCareer input[id=reti]').val('');
	document.getElementById('prestito').checked = false;
	console.info('fine chiamata cleanTeam() in team.js');
}