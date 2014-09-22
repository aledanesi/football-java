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

function buy()
{
	var letteraRicerca = jQ("#iniziale").val();

	if (letteraRicerca != '')
	{
		footballManager.getPlayers(letteraRicerca, "ALL", function (data)
		{	
			var str = "<table class=\"standard_tabelle\" style=\"width: 730px\"><tr><th colspan=\"3\">Risultati ottenuti:</th></tr><tr><th colspan=\"3\">Clicca su un nome per acquistare il giocatore:</th></tr>";


			for (var i = 0; i < data.length; i++) {
				
				str += "<tr class=\"${counter % 2 == 0 ? 'even' : 'odd'}\">";
				str += "<td class=\"bg2 alle\">" + data[i].lastName + " " + data[i].firstName + " (" + data[i].position.descPosizione;
				
				if (data[i].team != null){
					str += " - " + data[i].team.name + ") ";
				}
				
				str += "nato il " + jQ.datepicker.formatDate("dd/mm/yy", data[i].dateOfBirth) + " a " + data[i].placeOfBirth;
				str += "</td>";
				str += "<td class=\"bg2 alle\" style=\"text-align: center\"><a href=\"#\" onclick='acquistaGiocatore(" + data[i].id + ", jQ(\"#teamId\").val(), false);'>Acquisto</a></td>";				
				str += "<td class=\"bg2 alle\" style=\"text-align: center\"><a href=\"#\" onclick='acquistaGiocatore(" + data[i].id + ", jQ(\"#teamId\").val(), true);'>Prestito</a></td>";				
				
			}
			
			str += "</tr></table>"
			
			jQ("#buyPlayerResults").html(str);
			
			jQ("#buyPlayerResults").show();				
					
		});	
	}
	else 
	{
		jQ("#buyPlayerResults").hide();	
	}			
}
</script>
 

	<!-- buy player -->
	<div id="dBuyPlayer">
		
		<p class="validateTips"></p>
		
		<spring:url var="buyURL" value="/players/buy.do" />

		<form:form commandName="buyPlayer" action="${buyURL}" id="formRicerca">
   
			<form:hidden path="hiddenIniziale" id="hiddenIniziale" />
			<form:hidden path="teamId" id="teamId" />

   
			<table class="standard_tabelle" style="width: 730px">
			  <tr>
				  <th>
				  Acquista un Giocatore
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
							<form:input path="iniziale" id="iniziale" size='28' />
												
							<input type='button' value='Cerca' size='28' onclick='buy();' />
							<input type='button' value='Reset' size='28' onclick='jQ("#iniziale").val("")' />
					  </td>
				  </tr>

			</table>
		</form:form>
		<form:form commandName="buyPlayer" action="${buyURL}" id="formRicerca">
   
			<div id="buyPlayerResults">

				<table class="standard_tabelle" style="width: 730px">
					  <tr>
						<th colspan="3">Risultati ottenuti:</th>
					  </tr>
					  <tr>
						<th colspan="3">Clicca su un nome per acquistare il giocatore:</th>
					  </tr>
								
						<c:set var="counter" value="${0}" />
						<c:forEach items="${buyPlayerList}" var="playerStatus">
							<tr class="${counter % 2 == 0 ? 'even' : 'odd'}">
										<c:set var="counter" value="${counter+1}" />
								
							  <td class="bg2 alle">
								   <a href="#" class="targetLinkPlayer" data-id="${playerStatus.id}">${playerStatus.lastName} ${playerStatus.firstName}</a>               
								   (${playerStatus.position.descPosizione}<c:if test="${!empty playerStatus.team.name}"> - ${custom:nationalCapitalize(playerStatus.team.name)}</c:if>) nato il <fmt:formatDate value="${playerStatus.dateOfBirth}" type="both" pattern="dd/MM/yyyy" /> a ${playerStatus.placeOfBirth}              
							  </td>
							  <td class="bg2 alle" style="text-align: center">
								<c:if test="${playerStatus.team.id != buyPlayer.team.id}">
										<a href="#" onclick='acquistaGiocatore("${playerStatus.id}", jQ("#teamId").val(), false);'>Acquisto</a>              
								</c:if>
							  </td>		  
							  <td class="bg2 alle" style="text-align: center">
								<c:if test="${playerStatus.team.id != buyPlayer.teamId}">
										<a href="#" onclick='acquistaGiocatore("${playerStatus.id}", jQ("#teamId").val(), true);'>Prestito</a>              
								</c:if>
							  </td>		  
							</tr>              
						</c:forEach>
			  </table>
			</div> 
		</form:form>		

	</div>
	<!-- fine div -->