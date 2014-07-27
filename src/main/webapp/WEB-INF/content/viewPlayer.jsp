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

	<script type='text/javascript' src='${pageContext.request.contextPath}/dwr/engine.js'></script>
	<script type='text/javascript' src='${pageContext.request.contextPath}/dwr/util.js'></script>
	<script type='text/javascript' src='${pageContext.request.contextPath}/dwr/interface/teamManager.js'></script>
	<script type='text/javascript' src='${pageContext.request.contextPath}/dwr/interface/divisionManager.js'></script>
	<script type='text/javascript' src='${pageContext.request.contextPath}/dwr/interface/careerManager.js'></script>  
	<script type='text/javascript' src='${pageContext.request.contextPath}/dwr/interface/seasonManager.js'></script>
	<script type='text/javascript' src='${pageContext.request.contextPath}/dwr/interface/playerManager.js'></script>	  
	<script type='text/javascript' src='${pageContext.request.contextPath}/dwr/interface/positionManager.js'></script>
	<script type='text/javascript' src='${pageContext.request.contextPath}/dwr/interface/divisionManager.js'></script>
   

	<title><spring:message code="title"/> ${player.firstName} ${player.lastName}</title>

  <%-- FUNCTION SPECIFIC --%>
  <script type="text/javascript" src="${pageContext.request.contextPath}/scripts/player.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/scripts/career.js"></script>

	<script type="text/javascript">
		jQ(document).ready(function() 
		{     
			jQ( "#squadra" ).autocomplete({        
				source: '${pageContext.request.contextPath}/careers/get_team_list.do',
				appendTo: "#dEditCareer"
			});
		});		
	</script>

</head>

<spring:url var="listPlayerURL" value="/players/listByTeam.do" />

<spring:url var="insertCareerURL" value="/careers/insert.do">
   <spring:param name="player.id" value="${player.id}"></spring:param>
</spring:url>

<spring:url var="moveTeamURL" value="/teams/move.do">
   <spring:param name="player.id" value="${player.id}"></spring:param>
</spring:url>

<spring:url var="imageURL" value="/players/image.do">
   <spring:param name="id" value="${player.id}" />
</spring:url>

<spring:url var="imageTURL" value="/teams/image.do"> 
   <spring:param name="id" value="${player.team.id}"></spring:param> 
</spring:url>

<spring:url var="searchPlayerURL" value="/players/search.do"/>

<body>
	
<form:form commandName="player" action="${listPlayerURL}" id="returnPlayersPageForm" method="POST">
	<input type="hidden" name="team.id" id="teamId" />
	<input type="hidden" name="teamCategory" id="teamCategory" value="1" />					 
</form:form>

<spring:url var="viewURL" value="/players/view.do" />

<form:form commandName="player" action="${viewURL}" id="viewPlayerPageForm" method="POST">
	<input type="hidden" name="id" id="id" value="${player.id}" />
</form:form>

<spring:url var="withoutTeamURL" value="/players/withoutTeam.do" /> 

<form:form action="${withoutTeamURL}" id="withoutTeamPlayer" method="POST" commandName="player">
		<form:hidden path="id" />
</form:form>

<spring:url var="endCareerURL" value="/players/endCareer.do" /> 

<form:form action="${endCareerURL}" id="endCareerPlayer" method="POST" commandName="player">
		<form:hidden path="id" />
</form:form>


<div id="header2">
	<div id="menutop">
		<ul>
			<c:choose>		                           
						<c:when test="${! (player.endCareer || player.withoutTeam )}"> 
								<li>
					    		<a href="#" class="targetLinkViewPlayer" data-id="${player.team.id}"><spring:message code="returnListPlayer"/></a>
		            </li>
		        </c:when>
	          	<c:otherwise>
		            <li>
		            	<a href="${searchPlayerURL}"><spring:message code="searchPlayer" /></a>
		            </li>
	            </c:otherwise>
	          </c:choose>		                           
            <sec:authorize access="hasRole('ROLE_ADMIN')">   
		            <li>
									<a href="#" onclick="player.editPlayer('${player.id}'); return false;">Modifica Giocatore</a>
		            </li> 
		            <li>
									<a href="#" onclick="career.insertCareer('${player.id}'); return false;"><spring:message code="insertCareer"/></a>
		            </li> 
				        <c:if test="${! player.endCareer }"> 
				            <li>
											<a href="#" onclick="player.movePlayer('${player.id}'); return false;"><spring:message code="changeTeam"/></a>
				            </li>

    				        <c:if test="${! player.withoutTeam }"> 
					            <li>
												<a href="#" onclick="player.goWithoutTeam();">Svincolato</a>
					            </li> 
										</c:if>
				            <li>
											<a href="#" onclick="player.goEndCareer();">Fine carriera</a>
				            </li> 				            
								</c:if>
        	</sec:authorize>
    	</ul>
	</div>
</div>	

	
<table>
	<tr>

		<td style="padding: 2px">



	
    			
<!-- dati giocatore -->
<div style="width:500px;">
	<div style="background-color:#f1f1f1;border:1px solid #999;padding:0;margin-top:2px;">
		<table cellspacing="1" cellpadding="0" class="tabelle_spieler">
			<tbody>
				<tr>
					<td style="width: 100px;background-color: rgb(241, 241, 241);margin:0px;padding:0 3px;border:1px solid #bbb;" class="ac" rowspan="3">
						<c:choose>
							<c:when test="${player.endCareer}">
								<spring:url var="endCareerURL" value="/images/ritiro.jpg" />
								<img src="${endCareerURL}">
							</c:when>
							<c:when test="${player.withoutTeam}">
								<spring:url var="withoutTeamURL" value="/images/svincolato.jpg" />
								<img src="${withoutTeamURL}">
							</c:when>
							<c:otherwise>
								<img src="${imageURL}">
							</c:otherwise>
						</c:choose>
					</td>			
					<td style="background-color: rgb(14, 46, 128)" class="blau"><h1 style="color:#fff;">${player.number} ${player.firstName} ${player.lastName}</h1></td>
				</tr>
				<tr >
					<td class="odd">
						<c:choose>
							<c:when test="${player.endCareer}">
								Calciatore ritirato
							</c:when>
							<c:when test="${player.withoutTeam}">
								Calciatore svincolato
							</c:when>
							<c:otherwise>
								${player.team.name}, ${custom:nationalCapitalize(player.team.division.name)} (${custom:nationalCapitalize(player.team.nation.name)})
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
				<tr>
					<td style="background-color: rgb(230, 230, 230); font-weight: bold">
							&nbsp;
							<c:if test="${! (player.endCareer || player.withoutTeam)}">
								${custom:nationalDesc(player.national, player.nation.name)}
							</c:if>
					</td>
				</tr>
			</tbody>
		</table>

		<img width="16" height="16" class="vt kl_icon" src="${pageContext.request.contextPath}/images/prev.png"> 
		<c:if test="${nextCounter-1 > 0}">
			<a href="#" class="targetLinkPlayer s10 tdn" id="mycarousel-prev" data-id="${prevId}">
				${prevPlayer}
			</a>
		</c:if>	

		<c:if test="${nextId > 0}">
			<a href="#" class="targetLinkPlayer s10 fr tdn ietopminus" id="mycarousel-next" data-id="${nextId}">
				${nextPlayer}
				<img width="16" height="16" class="vt kl_icon" src="${pageContext.request.contextPath}/images/next.png">
			</a>
		</c:if>

	</div>
	<c:if test="${player.team.id != player.teamOwner.id}">
		<p class="drunter ar">in prestito dal ${custom:nationalCapitalize(player.teamOwner.name)}. Contratto fino al ${player.dateContract}</p>
	</c:if>
	
	<p class="hl_startseite mt10">Il profilo di ${player.firstName} ${player.lastName}
	<sec:authorize access="hasRole('ROLE_ADMIN')">
		<c:if test="${! empty player.lastTimeModify}">
			<img style="position: absolute" src="${pageContext.request.contextPath}/images/help.png" title="Aggiornato il: <fmt:formatDate pattern="dd/MM/yyyy - [HH:mm:SS]" value="${player.lastTimeModify}" /> da ${player.lastUserModify}">
		</c:if>
	</sec:authorize>
	</p>
	<table cellspacing="1" cellpadding="2" style="margin-top:0;border:1px solid #999; background-color: rgb(241, 241, 241);" class="standard_tabelle">
	<tbody>
		<tr>
		<td class="al vt">
		<table cellspacing="1" cellpadding="0" class="standard_tabelle s10">
			<tbody>
			<tr class="odd">
				<td style="width:150px;">Nome completo:</td>
				<td class="fn n">
					<c:choose>
						<c:when test="${!empty player.completeName}">
							${player.completeName}
						</c:when>
						<c:otherwise>
							${player.firstName} ${player.lastName}
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
			<tr class="even">
				<td>Data di nascita:</td>
				<td><fmt:formatDate value="${player.birthDate}" type="both" pattern="dd/MM/yyyy" /></td>
			</tr>
			<tr class="odd">
				<td>Luogo di nascita:</td>
				<td>${player.birthPlace}</td>
			</tr>
			<tr class="even">
				<td>Et&agrave;:</td>
				<td>${player.eta} anni</td>
			</tr>
			<tr class="odd">
				<td>Altezza:</td>
				<td>${player.height}</td>
			</tr>
			<tr class="even">
				<td>Nazionalit&agrave;:</td>
				<td class="adr">
					<span class="flaggen_sprite sprite_land_75 vt mt4">
						<img src="${pageContext.request.contextPath}/images/flags/${player.nation.id}.png" id="flag" style="margin-top: 4px" />
						<span class="country-name s10">${custom:nationalCapitalize(player.nation.name)}</span>
							<c:if test="${! empty player.nation2.id}">
								<br><img src="${pageContext.request.contextPath}/images/flags/${player.nation2.id}.png" alt="${player.nation2.name}" id="flag" style="margin-top: 4px" />
							</c:if>
						</span>
						<span class="country-name s10">${custom:nationalCapitalize(player.nation2.name)}</span>
					</span>
				</td>
			</tr>
			<tr class="odd">
				<td style="width:115px;" class="category">Ruolo:</td>
				<td>${player.position.descPosizione}</td>
			</tr>
			<tr class="even">
				<td>Piede:</td>
				<td>${player.foot}</td>
			</tr>
      <c:if test="${! (player.endCareer || player.withoutTeam) }"> 
				<tr class="odd">
					<td>Valore di mercato:</td>
					<td class="note">${custom:currencyValue(player.value)} &euro;</td>
				</tr>
			</c:if>
			</tbody>
			</table>
		</td>
		</tr>
		</tbody>
	</table>

	<!-- ulteriori dati -->
  <c:if test="${! (player.endCareer || player.withoutTeam) }"> 

	<p class="hl_startseite mt10">Ulteriori dati di ${player.firstName} ${player.lastName}</p>
	<table cellspacing="1" cellpadding="2" style="margin-top:0;border:1px solid #999; background-color: rgb(241, 241, 241);" class="standard_tabelle">
	<tbody>
		<tr>
		<td class="al vt">
		<table cellspacing="1" cellpadding="0" class="standard_tabelle s10">
			<tbody>
			<tr class="odd">
				<td style="width: 150px;">Ingaggio:</td>
				<td>${custom:currencyValue(player.income)} &euro;</td>
			</tr>							
			<tr class="even">
				<td>Scadenza contratto:</td>
				<td>${player.dateContract}</td>
			</tr>
			</tbody>
			</table>
		</td>
		</tr>
		</tbody>
	</table>

</c:if> 

</div>


<div style="width:500px;">	
	<h2 class="hl_startseite mt10" style="text-align: center">Carriera del giocatore</h2>
					

<table style="width: 500px;" class="standard_tabelle">
      <tr class="thead1 tcharw">
      <th style="width: 100px;">Stagione</th>
      <th style="width: 200px;">Squadra</th>   
      <th style="width: 100px;">Serie </th>
      <th style="width: 50px;">Pres.</th>
      <th style="width: 50px;">Reti</th>
	  <sec:authorize access="hasRole('ROLE_ADMIN')"> 
	  	<th>&nbsp;</th>
	  </sec:authorize>
	  <sec:authorize access="hasRole('ROLE_ADMIN')"> 
      	<th>&nbsp;</th>
	  </sec:authorize>
    </tr>
  
	<c:set var="counter" value="${0}" />
    <c:forEach items="${careerList}" var="careerStatus">
    
      <tr class="${counter % 2 == 0 ? 'odd' : 'even'}">
	  <c:set var="counter" value="${counter+1}" />

        <td style="text-align: center">${careerStatus.periodo}</td>
        <td style="text-align: center">${careerStatus.squadra} <c:if test="${careerStatus.onLoan}">(*)</c:if></td>
        <td style="text-align: center">${careerStatus.serie}</td>
        <td style="text-align: center">${careerStatus.presenze}</td>
        <td style="text-align: center">${careerStatus.reti}</td>
        <sec:authorize access="hasRole('ROLE_ADMIN')"> 
          <td style="text-align: center">
          <a href="#" onclick="career.updateCareer('${player.id}', '${careerStatus.id}'); return false;"><img src="${pageContext.request.contextPath}/images/edit.png" alt="<spring:message code="edit"/>" /></a>          
          </td>
        </sec:authorize> 
        <sec:authorize access="hasRole('ROLE_ADMIN')"> 
        <td style="text-align: center">
			<spring:url var="deleteURL" value="/careers/delete.do">
				<spring:param name="id" value="${careerStatus.id}"></spring:param>
				<spring:param name="player.id" value="${player.id}"></spring:param>
			</spring:url>         
        	<a href="#" onclick="career.confirmDeleteCareer('${deleteURL}'); return false;">
        		<img src="${pageContext.request.contextPath}/images/delete.png" alt="<spring:message code="edit"/>" />
        	</a>          
        </td>
      </sec:authorize> 
    </tr>
    </c:forEach>
			
    </table>					

	</div>

		
		</td>
			
		<td style="vertical-align: top; padding: 2px">

		<div style="width: 280px; ">

				<p style="margin-top: 3px;" class="geruechtebox_head al">Cambia profilo</p>
				<div class="hell lhg s10 p5">
				<form method="post" name="neuer_spieler" action="">
				<select onchange="player.changePlayer()" class="select" name="view_player_id" id="view_player_id">
					<option class="option" value="">Seleziona un giocatore:</option>
					<optgroup style="padding: 2px 15px;" label="${player.team.name}">
				    <c:forEach items="${playerList}" var="row">
				     	<option class="option" value="${row.id}">${row.firstName} ${row.lastName}</option>
						</c:forEach>							
					</optgroup>			
			 	</select>
				</form>
				</div>
			
				<br>
				<p style="margin-top: 3px;" class="geruechtebox_head al">Ruolo</p>
		            
		    <table class="tabelle_spieler" cellSpacing="0" cellPadding="0">
					<tbody>
						<tr>
		        		<td style="padding: 2px 5px; border-bottom-color: rgb(255, 255, 255); border-bottom-width: 1px; border-bottom-style: solid; background-color: rgb(241, 241, 241);" class="vt" colSpan="2">
									<c:if test="${player.position.codRuolo == '01'}">
										<div style='height: 170px; position: relative; background-image: url(${pageContext.request.contextPath}/images/Torwart.png); background-repeat: no-repeat;'>
											<img class="${player.position.codCss}" title="${player.position.descPosizione}" alt="${player.position.descPosizione}" src="${pageContext.request.contextPath}/images/hauptposition.png">
										</div>
									</c:if>	
									<c:if test="${player.position.codRuolo == '02'}">
										<div style='height: 170px; position: relative; background-image: url(${pageContext.request.contextPath}/images/Abwehr.png); background-repeat: no-repeat;'>
										<c:choose>
											<c:when test="${player.position.codPosizione == 'DC'}">
													<img class="${player.position.codCss}l" title="${player.position.descPosizione}" alt="${player.position.descPosizione}" src="${pageContext.request.contextPath}/images/hauptposition.png">
													<img class="${player.position.codCss}r" title="${player.position.descPosizione}" alt="${player.position.descPosizione}" src="${pageContext.request.contextPath}/images/hauptposition.png">
											</c:when>	
											<c:otherwise>
													<img class="${player.position.codCss}" title="${player.position.descPosizione}" alt="${player.position.descPosizione}" src="${pageContext.request.contextPath}/images/hauptposition.png">
											</c:otherwise>	
										</c:choose>	
										</div>
									</c:if>	
									<c:if test="${player.position.codRuolo == '03'}">
										<div style='height: 170px; position: relative; background-image: url(${pageContext.request.contextPath}/images/Mittelfeld.png); background-repeat: no-repeat;'>
												<img class="${player.position.codCss}" title="${player.position.descPosizione}" alt="${player.position.descPosizione}" src="${pageContext.request.contextPath}/images/hauptposition.png">
										</div>
									</c:if>	
									<c:if test="${player.position.codRuolo == '04'}">
										<div style='height: 170px; position: relative; background-image: url(${pageContext.request.contextPath}/images/Sturm.png); background-repeat: no-repeat;'>
											<img class="${player.position.codCss}" title="${player.position.descPosizione}" alt="${player.position.descPosizione}" src="${pageContext.request.contextPath}/images/hauptposition.png">
										</div>
									</c:if>	
								</td>
						</tr>
					</tbody>
				</table>

		</div>
			
		</td>
			
	</tr>
</table>	
	
	
	<sec:authorize access="hasRole('ROLE_ADMIN')">   

			<jsp:include page="secure/editPlayer.jsp" />	

			<jsp:include page="secure/movePlayer.jsp" />
				
			<jsp:include page="secure/editCareer.jsp" />
			
			<jsp:include page="secure/deleteCareer.jsp" />			

	</sec:authorize>	

</body>
</html>