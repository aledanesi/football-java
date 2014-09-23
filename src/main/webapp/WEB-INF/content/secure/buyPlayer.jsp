<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<%@ taglib prefix="custom" uri="http://jfooball.it/functions"  %>

<script>
	String.prototype.capitalize = function() {
		var str = this.toLowerCase();

		return str.charAt(0).toUpperCase() + str.slice(1);
	}

	jQ(document).ready(function() {     
		jQ("#buyPlayerResults").hide();
	});	

	function searchBuy()
	{
		var letteraRicerca = jQ("#iniziale").val();

		if (letteraRicerca != '')
		{

			footballManager.getPlayers(letteraRicerca, "ALL", function (data)
			{	
			
				var str = "<table class=\"standard_tabelle\" style=\"width: 730px\"><tr><th colspan=\"3\">Risultati ottenuti:</th></tr><tr><th colspan=\"3\">Clicca su un nome per acquistare il giocatore:</th></tr>";

				
				for (var i = 0; i < data.length; i++) {
					
					if (i % 2 == 0)
						str += "<tr class='even'>";
					else 
						str += "<tr class='odd'>";	
						
					str += "<td class=\"bg2 alle\">" + data[i].lastName + " " + data[i].firstName + " (" + data[i].position.descPosizione;
					
					if (data[i].team != null){
						str += " - " + data[i].team.name.capitalize() + ") ";
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
		footballManager.updateTeam(playerId, teamId, isLoan, function (data){

			//jQ("#teamId2").val(teamId);
		
			jQ("#listByTeamFORM").submit();
		});
	}
</script>
 

	<!-- buy player -->
	<div id="dBuyPlayer">
		
		<p class="validateTips"></p>
		
		<spring:url var="listByTeamURL" value="/players/listByTeam.do" />

		<spring:url var="buyURL" value="/players/buy.do" />
		
		<form id="listByTeamFORM" action="${listByTeamURL}"  method="POST">
			<input type="hidden" name="team.id" value="${team.id}" />
			<input type="hidden" name="teamCategory" value="1" />
		</form>

		<form action="#" id="formRicerca" onsubmit="searchBuy();return false;">
   
			<input type="hidden" name="team.id" id="teamId" value="${team.id}" />
			<input type="hidden" id="listByTeamURL" value="${listByTeamURL}" />
   
			<table class="standard_tabelle" style="width: 730px">
			  <tr>
				  <th>
				  Scegli il giocatore da acquistare
				  </th>
			  </tr>
			  <tr>
				  <td>
				  Seleziona l'iniziale del cognome:
				  </td>
			  </tr>
			</table>
			<table class="standard_tabelle" style="width: 730px">
				  <tr>
					  <th>
					  Ricerca libera: inserisci le lettere iniziali o il cognome intero
					  </th>
				  </tr>
				  <tr>
					  <td>
							<input type="text" name="iniziale" id="iniziale" size='28' />
												
							<input type='button' value='Cerca' size='28' onclick='searchBuy();' />
							<input type='button' value='Reset' size='28' onclick='jQ("#iniziale").val("")' />
					  </td>
				  </tr>

			</table>
   
			<div id="buyPlayerResults">

				<table class="standard_tabelle" style="width: 730px">
					  <tr>
						<th colspan="3">Risultati ottenuti:</th>
					  </tr>
					  <tr>
						<th colspan="3">Clicca su un nome per acquistare il giocatore:</th>
					  </tr>
								
			  </table>
			</div> 
		</form>		

	</div>
	<!-- fine div -->