<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<%@ taglib prefix="custom" uri="http://jfooball.it/functions"  %>

<html>
<head>
<title>Cerca un giocatore</title>

<%-- FUNCTION SPECIFIC --%>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/player.js"></script>

<script type='text/javascript' src='${pageContext.request.contextPath}/dwr/engine.js'></script>
<script type='text/javascript' src='${pageContext.request.contextPath}/dwr/util.js'></script>
<script type='text/javascript' src='${pageContext.request.contextPath}/dwr/interface/footballManager.js'></script>

	
<script>
	jQ(document).ready(function() {     
		jQ("#hiddenIniziale").val('');
	});	

	jQ(function() {
		
		jQ( "a.targetSearchPlayers" ).click(function() 
		{  		
			var input = jQ("#hiddenIniziale").val(jQ(this).attr("data-iniziale"));
			
			jQ("#formRicerca").append(jQ(input));			
			
			jQ("#formRicerca").submit(); 

		});
	});
	
	function acquistaGiocatore(playerId, teamId, isLoan)
	{
		playerManager.updateTeam(playerId, teamId, isLoan, function (data){

			jQ("#returnPlayersPageForm").submit();
		});
	}

</script>
	

</head>

<spring:url var="buyURL" value="/players/buy.do" />

<body>

<spring:url var="returnURL" value="/players/listByTeam.do">
   <spring:param name="team.id">${player.teamId}</spring:param>
   <spring:param name="teamCategory">1</spring:param>
</spring:url>

<spring:url var="viewURL" value="/players/view.do" />

<form:form commandName="player" action="${returnURL}" id="returnPlayersPageForm" method="POST">
	<input type="hidden" name="id" id="id" value="${player.teamId}" />
</form:form>

<form:form commandName="player" action="${viewURL}" id="viewPlayerPageForm" method="POST">
	<input type="hidden" name="id" id="id" />
</form:form> 


<div id="header2">
    <div id="menutop">
        <ul>
            <li> 
			<a href="#" class="targetLinkViewPlayer" data-id="${player.teamId}"><spring:message code="returnListPlayer"/></a>
            </li> 
			
        </ul>
        
    </div>
</div>
<div id="menubline">
</div>

<div id="page">
    <!--
    <div id="sidebar">
        <ul></ul>
    </div>
    -->
    <div id="content">
        
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
												
							<input type='button' value='Cerca' size='28' onclick='jQ("#formRicerca").submit();' />
							<input type='button' value='Reset' size='28' onclick='jQ("#iniziale").val("")' />
					  </td>
				  </tr>

			</table>
		</form:form>
		<form:form commandName="buyPlayer" action="${buyURL}" id="formRicerca">
   
			<c:if test="${! empty playerList }">

				<table class="standard_tabelle" style="width: 730px">
					  <tr>
						<th colspan="3">Risultati ottenuti:</th>
					  </tr>
					  <tr>
						<th colspan="3">Clicca su un nome per acquistare il giocatore:</th>
					  </tr>
								
						<c:set var="counter" value="${0}" />
						<c:forEach items="${playerList}" var="playerStatus">
							<tr class="${counter % 2 == 0 ? 'even' : 'odd'}">
										<c:set var="counter" value="${counter+1}" />
								
							  <td class="bg2 alle">
								   <a href="#" class="targetLinkPlayer" data-id="${playerStatus.id}">${playerStatus.lastName} ${playerStatus.firstName}</a>               
								   (${playerStatus.position.descPosizione}<c:if test="${!empty playerStatus.team.name}"> - ${custom:nationalCapitalize(playerStatus.team.name)}</c:if>) nato il <fmt:formatDate value="${playerStatus.birthDate}" type="both" pattern="dd/MM/yyyy" /> a ${playerStatus.birthPlace}              
							  </td>
							  <td class="bg2 alle" style="text-align: center">
								<c:if test="${playerStatus.team.id != player.teamId}">
										<a href="#" onclick='acquistaGiocatore("${playerStatus.id}", jQ("#teamId").val(), false);'>Acquisto</a>              
								</c:if>
							  </td>		  
							  <td class="bg2 alle" style="text-align: center">
								<c:if test="${playerStatus.team.id != player.teamId}">
										<a href="#" onclick='acquistaGiocatore("${playerStatus.id}", jQ("#teamId").val(), true);'>Prestito</a>              
								</c:if>
							  </td>		  
							</tr>              
						</c:forEach>
			  </table>
			</c:if> 
		</form:form>
    </div>
</div>

</body>
</html>