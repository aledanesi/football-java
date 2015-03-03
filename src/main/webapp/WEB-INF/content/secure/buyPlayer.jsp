<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<%@ taglib prefix="custom" uri="http://jfooball.it/functions"  %>

<script>
	jQ(document).ready(function() {     
		jQ("#buyPlayerResults").hide();
	});
</script>
 

	<!-- buy player -->
	<div id="dBuyPlayer" style="display: none">
		
		<p class="validateTips"></p>
		
		<spring:url var="listByTeamURL" value="/players/listByTeam.do" />

		<spring:url var="buyURL" value="/players/buy.do" />
		
		<form id="listByTeamFORM" action="${listByTeamURL}"  method="POST">
			<input type="hidden" name="team.id" value="${team.id}" />
			<input type="hidden" name="teamCategory" value="1" />
		</form>

		<form action="#" id="formRicerca" onsubmit="searchBuyPlayer();return false;">
   
			<input type="hidden" name="team.id" id="teamId" value="${team.id}" />
			<input type="hidden" id="listByTeamURL" value="${listByTeamURL}" />
			<input type="hidden" id="teamIdTransfer" />			
			<input type="hidden" id="playerIdTransfer" />			
			<input type="hidden" id="isLoanTransfer" />		
			<input type="hidden" id="statusTransfer" />		
			
   
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
												
							<input type='button' value='Cerca' size='28' onclick='searchBuyPlayer();' />
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