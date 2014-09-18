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
</script>
	

</head>

<spring:url var="searchURL" value="/players/search.do" />

<spring:url var="returnURL" value="/teams/list.do">
   <spring:param name="division.id">1</spring:param>
</spring:url> 

<body>

<spring:url var="viewURL" value="/players/view.do" />

<form:form commandName="player" action="${viewURL}" id="viewPlayerPageForm" method="POST">
	<input type="hidden" name="id" id="id" />
</form:form>


<div id="header2">
    <div id="menutop">
        <ul>
            <li>
		        <a href="${returnURL}">
		          Torna all'elenco delle squadre
		        </a>  
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
        
   <form:form commandName="searchPlayer" action="${searchURL}" id="formRicerca">
   
		<form:hidden path="hiddenIniziale" id="hiddenIniziale" />

   
    <table class="standard_tabelle" style="width: 700px">
  <tr>
      <th>
      Cerca un Giocatore
      </th>
  </tr>
  <tr>
      <td>
      Seleziona l'iniziale del cognome:
      </td>
  </tr>
    </table>
    <table class="standard_tabelle" style="width: 700px">
  <tr>

    <c:forEach items="${lettereRicerca}" var="obj">   
      <td><a a href="#" class="targetSearchPlayers" data-iniziale="${obj}">${obj}</a></td>
    </c:forEach>
  
  </tr>
    </table>
    <table class="standard_tabelle" style="width: 700px">
		  <tr>
		      <th>
		      Ricerca libera: inserisci le lettere iniziali o il cognome intero
		      </th>
		  </tr>
		  <tr>
		      <td>
			      	<form:radiobutton path="type" value="OTHERS" /><span style="padding: 5px">In attività</span>
			      	<form:radiobutton path="type" value="WITHOUT_TEAM" /><span style="padding: 5px">Svincolati</span>
			      	<form:radiobutton path="type" value="END_CAREER" /><span style="padding: 5px">Ritirati</span>
			      	<form:radiobutton path="type" value="ALL" /><span style="padding: 5px">Tutti</span>
		      </td>
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

    <br/>
    

    <c:if test="${! empty playerList }">
      <div class="content">

        <table class="standard_tabelle">
      <tr>
        <th>Risultati ottenuti:</th>
      </tr>
      <tr>
        <th>Clicca su un nome per accedere alla relativa scheda:</th>
      </tr>
                
				<c:set var="counter" value="${0}" />
        <c:forEach items="${playerList}" var="playerStatus">
					<tr class="${counter % 2 == 0 ? 'even' : 'odd'}">
					<c:set var="counter" value="${counter+1}" />
        	
          <td class="bg2 alle">
	       	   <a href="#" class="targetLinkPlayer" data-id="${playerStatus.id}">${playerStatus.lastName} ${playerStatus.firstName}</a>               
		       (${playerStatus.position.descPosizione}<c:if test="${!empty playerStatus.team.name}"> - ${custom:nationalCapitalize(playerStatus.team.name)}</c:if>) nato il <fmt:formatDate value="${playerStatus.dateOfBirth}" type="both" pattern="dd/MM/yyyy" /> a ${playerStatus.placeOfBirth}              
          </td>
          </tr>              
          
        </c:forEach>
      </table>
      </div>
    </c:if> 

    </div>
</div>

</body>
</html>