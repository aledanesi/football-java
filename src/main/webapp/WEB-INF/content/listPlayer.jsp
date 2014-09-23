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
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

	<script type='text/javascript' src='${pageContext.request.contextPath}/dwr/engine.js'></script>
	<script type='text/javascript' src='${pageContext.request.contextPath}/dwr/util.js'></script>
	<script type='text/javascript' src='${pageContext.request.contextPath}/dwr/interface/footballManager.js'></script>	  

    <%-- FUNCTION SPECIFIC --%>
    <script type="text/javascript" src="${pageContext.request.contextPath}/scripts/player.js"></script>
    
	<title>${custom:nationalCapitalize(team.name)}</title>    

</head>

<spring:url var="returnListURL" value="/teams/list.do" />

<spring:url var="insertPlayerURL" value="/players/insert.do">
	<spring:param name="team.id" value="${team.id}"></spring:param>
</spring:url>

<spring:url var="imageURL" value="/teams/image.do"> 
   <spring:param name="id" value="${team.id}"></spring:param> 
</spring:url>

<spring:url var="viewFirstTeamURL" value="/players/listByTeam.do">
	<spring:param name="team.id">${team.id}</spring:param>
	<spring:param name="teamCategory">1</spring:param>					
</spring:url>

<spring:url var="viewSecondTeamURL" value="/players/listByTeam.do">
	<spring:param name="team.id">${team.id}</spring:param>
	<spring:param name="teamCategory">2</spring:param>					
</spring:url>

<spring:url var="viewYoungTeamURL" value="/players/listByTeam.do">
	<spring:param name="team.id">${team.id}</spring:param>
	<spring:param name="teamCategory">3</spring:param>					
</spring:url>

<spring:url var="viewOtherTeamURL" value="/players/listByTeam.do">
	<spring:param name="team.id">${team.id}</spring:param>
	<spring:param name="teamCategory">4</spring:param>					
</spring:url>

<spring:url var="buyPlayerURL" value="/players/buy.do">
	<spring:param name="teamId">${team.id}</spring:param>
</spring:url>

<body>
	
	<div id="header2">
		<div id="menutop">
			<ul>
				<li><a href="#" class="targetLinkPlayers" data-division="${team.division.id}" data-nation="${team.nation.id}"><spring:message code="returnListTeam" /> </a></li>
				<sec:authorize access="hasAnyRole('ROLE_ADMIN', 'DEVELOPER')">	
					<c:if test="${user_in_session.user.roles[0].name == 'ROLE_ADMIN'}">
						<li> <a href="#" onclick="player.newPlayer()"><spring:message code="insertPlayer" /> </a></li>
					</c:if>				
					<c:if test="${user_in_session.user.roles[0].name == 'ROLE_ADMIN' || 
					             !empty user_in_session.user.profile && user_in_session.user.profile.teamId == team.id}">
						 <li> 
						 	<%-- <a href="${buyPlayerURL}">Acquista un giocatore</a> --%>
						 	<a href="#" onclick="player.buyPlayer()">Acquista un giocatore</a>
						 
						 </li>
					</c:if>
				</sec:authorize>
			</ul>
		</div>
	</div>
	
	<form:form commandName="player" action="${returnListURL}" id="returnTeamsPageForm" method="POST">
		<input type="hidden" name="division.id" id="divisionId" />
		<input type="hidden" name="nation.id" id="nationId" />					 
	</form:form>
	
	<spring:url var="viewURL" value="/players/view.do" />
	
	<form:form commandName="player" action="${viewURL}" id="viewPlayerPageForm" method="POST">
		<input type="hidden" name="id" id="id" />
	</form:form>

	<table bordercolor="#111111">
		<tbody>
			<tr>
				<td valign="middle" style="margin: 15pt;">
						<div class="content">

							<table class="tabelle_spieler" cellSpacing="1" cellPadding="0">
								<tbody>
									<tr>
										<td class="ac verein_header_zelle_wappen" rowSpan="3" style="width: 100px; padding: 0px; padding-right: 3px; padding-left: 3px; margin: 0px; border-top-color: #bbb; border-right-color: #bbb; border-bottom-color: #bbb; border-left-color: #bbb; border-top-width: 1px; border-right-width: 1px; border-bottom-width: 1px; border-left-width: 1px; border-top-style: solid; border-right-style: solid; border-bottom-style: solid; border-left-style: solid; background-color: rgb(255, 255, 255);">
												<img alt="-" src="${imageURL}" style="width: 75%">
										</td>
										<td class="blau vm verein_header_table_gross_bg" style="background-color: rgb(14, 46, 128);">
											<h1 class="fff" style="vertical-alignment: middle">
												<span id="vereinsinfo" class="s18 tdn fff">${custom:nationalCapitalize(team.name)}</span>
												<span class="s10 fn fff">${custom:nationalCapitalize(team.nation.name)}</span>
												<c:if test="${teamCategory == '2'}">
													<span class="s10 fn fff">(Primavera)</span>
												</c:if>	
												<c:if test="${teamCategory == '3'}">
													<span class="s10 fn fff">(Giovanili)</span>
												</c:if>	
												<c:if test="${teamCategory == '4'}">
													<span class="s10 fn fff">(Altri giocatori)</span>
												</c:if>	
												<sec:authorize access="hasAnyRole('ROLE_ADMIN')">
													<c:if test="${! empty team.lastTimeModify}">
														<img style="position: absolute" src="${pageContext.request.contextPath}/images/help.png" title="Aggiornato il: <fmt:formatDate pattern="dd/MM/yyyy - [HH:mm:SS]" value="${team.lastTimeModify}" /> da ${team.lastUserModify}">
													</c:if>
												</sec:authorize>												
											</h1>
										</td>
									</tr>
									<tr>
										<td>
											<img class="pfeil" alt="-" src="${pageContext.request.contextPath}/images/linkpfeil_b.gif">&nbsp;<a class="s10 fn targetLinkPlayers" data-division="${team.division.id}" data-nation="${team.nation.id}"  href="#">${custom:nationalCapitalize(team.division.name)}</a>					
										</td>
									</tr>
									<tr>
										<td>
											<img class="pfeil" alt="-" src="${pageContext.request.contextPath}/images/linkpfeil_b.gif">&nbsp;

											<c:if test="${teamCategory != '1'}">
												<a class="s10 fn" href="${viewFirstTeamURL}">Prima Squadra</a>&nbsp;&nbsp;&nbsp;
											</c:if>
											<c:if test="${teamCategory != '2'}">
												<a class="s10 fn" href="${viewSecondTeamURL}">Primavera</a>&nbsp;&nbsp;&nbsp;
											</c:if>
											<c:if test="${teamCategory != '3'}">
												<a class="s10 fn" href="${viewYoungTeamURL}">Giovanili</a>&nbsp;&nbsp;&nbsp;
											</c:if>
											<c:if test="${teamCategory != '4'}">
												<a class="s10 fn" href="${viewOtherTeamURL}">Altri giocatori</a>&nbsp;&nbsp;&nbsp;
											</c:if>

										</td>
									</tr>
								</tbody>
							</table>
							
							<c:if test="${! empty playerList}">

							<table class="standard_tabelle" cellpadding="5px" style="width: 650px">
								<tr class="title">
									<th>#</th>
									<th style="text-align: left"><spring:message code="footballer" /></th>
									<th style="width: 50px;">Nazione</th>
									<th style="width: 50px;">Squadra precedente</th>									
									<th style="width: 120px; text-align: center">Scadenza contratto</th>
									<%--<th style="width: 70px; text-align: center">Valore giocatore</th>--%>
									<th style="width: 70px; text-align: center">Ingaggio netto</th>									
									<sec:authorize access="hasAnyRole('ROLE_ADMIN', 'DEVELOPER')">									
										<c:if test="${user_in_session.user.roles[0].name == 'ROLE_ADMIN' || 
													 !empty user_in_session.user.profile && user_in_session.user.profile.teamId == team.id}">
											 <th></th>
										</c:if>
									</sec:authorize>
									<sec:authorize access="hasRole('ROLE_ADMIN')">
										<th></th>
									</sec:authorize>
								</tr>
								<c:set var="counter" value="${0}" />
								
								<c:set var="styleGoalkeaper" value="#4384A8" />
								<c:set var="styleDefender" value="#6DA0BC" />
								<c:set var="styleMidfielder" value="#ACC9D9" />
								<c:set var="styleStriker" value="#D5E4EC" />
								
								<c:forEach items="${playerList}" var="row">

									<tr style="border: 1px solid #E4E4E4">
									<c:set var="counter" value="${counter+1}" />
									
									<c:if test="${row.position.codRuolo == '01'}">
										<c:set var="stylePlayer" value="${styleGoalkeaper}" />
									</c:if>
									<c:if test="${row.position.codRuolo == '02'}">
										<c:set var="stylePlayer" value="${styleDefender}" />
									</c:if>
									<c:if test="${row.position.codRuolo == '03'}">
										<c:set var="stylePlayer" value="${styleMidfielder}" />
									</c:if>
									<c:if test="${row.position.codRuolo == '04'}">
										<c:set var="stylePlayer" value="${styleStriker}" />
									</c:if>									

									<td style="font-size: 11px; font-weight: bold; text-align: center; width: 40px; border:1px solid #999;  background-color: ${stylePlayer}">
										<c:if test="${! empty row.number}">
											<div class="rn_nummer">
												${row.number}
											</div>	
										</c:if>
										<c:if test="${empty row.number}">
											-
										</c:if>
									</td>
									<td style="white-space: nowrap; text-align: left; padding: 0px; border:1px solid #999;">
										<table>
											<tr>
												<td style="width: 15px !important;" rowSpan="2">
													<spring:url var="imageURL" value="/players/image.do">
													   <spring:param name="id" value="${row.id}" />
													</spring:url>													
													<img width="31" height="38" class="minifoto" src="${imageURL}"/>
												</td>												
												<td style="padding: 0px; font-weight: bold">
													<spring:url var="viewURL" value="/players/view.do" />
													<span style="width: 30px; text-align: right;">
														<a href="#" class="targetLinkPlayer" data-id="${row.id}">
															<c:if test="${! empty row.firstName}"> ${row.firstName}</c:if> ${row.lastName}<c:if test="${row.team.id != row.teamOwner.id}">*</c:if>
															<c:if test="${row.captain != null && row.captain}">
															  &nbsp;<img src="${pageContext.request.contextPath}/images/captain.gif" title="Capitano" />
															</c:if>														
														</a>
													</span>														
												</td>
											</tr>
											<tr>
												<td style="padding: 0px">
													${row.position.descPosizione}, ${row.age} anni
												</td>
											</tr>												
										</table>	
									</td>
									<td style="text-align: center; border:1px solid #999;">
										<img src="${pageContext.request.contextPath}/images/flags/${row.nationality.id}.png" title="${custom:nationalCapitalize(row.nationality.name)}" id="flag" />
										<c:if test="${! empty row.nationality2.id}">
											<br><img src="${pageContext.request.contextPath}/images/flags/${row.nationality2.id}.png" title="${custom:nationalCapitalize(row.nationality2.name)}" id="flag" />
										</c:if>
									</td>

									<c:choose>
											<c:when test="${row.team.id != row.teamOwner.id}">	
												<td style="text-align: center; border:1px solid #999;" class="s10 ac wid10 ch cleague">
													<spring:url var="imageOwnerURL" value="/teams/image.do"> 
													   <spring:param name="id" value="${row.teamOwner.id}"></spring:param> 
													</spring:url>														
													<span style="width: 30px; text-align: right;">
														<img width="20" height="24" src="${imageOwnerURL}" title="In prestito dal ${custom:nationalCapitalize(row.teamOwner.name)}">
													</span>
												</td>									
											</c:when>
											<c:when test="${row.teamPrev.id != row.team.id && !empty row.teamPrev.id }">
												<td style="text-align: center; border:1px solid #999;">
													<spring:url var="imageURL" value="/teams/image.do">
														<spring:param name="id">${row.teamPrev.id}</spring:param>
													</spring:url>
													<span style="width: 30px; text-align: right;">
														<img width="20" height="23" class="minifoto" src="${imageURL}" title="In precedenza al ${custom:nationalCapitalize(row.teamPrev.name)}"/>										
													</span>
												</td>									
											</c:when>
											<c:otherwise>
												<td style="text-align: center; border:1px solid #999;"></td>
											</c:otherwise>	
									</c:choose>
									<c:choose>
										<c:when test="${row.team.id != row.teamOwner.id}">
											<td style="text-align: center; white-space:nowrap; padding-left: 10px; padding-right: 10px; border:1px solid #999;" class="s10 ac wid10 ch cleague">fine anno</td>
										</c:when>
										<c:otherwise>
											<td style="text-align: center; white-space:nowrap; padding-left: 10px; padding-right: 10px; border:1px solid #999;">
												<c:if test="${! empty row.contractUntil}">${row.contractUntil}</c:if>
												<c:if test="${empty row.contractUntil}">?</c:if>
											</td>
										</c:otherwise>
									</c:choose>
									<%--<td style="text-align: center; white-space:nowrap; border:1px solid #999;"><c:if test="${! empty row.cost}">${custom:shortCurrencyValue(row.cost)}</c:if></td>--%>
									<td style="text-align: center; white-space:nowrap; border:1px solid #999;">
										<c:choose>
											<c:when test="${! empty row.netAnnualSalary && row.netAnnualSalary != 0}">${custom:shortCurrencyValue(row.netAnnualSalary)}</c:when>
											<c:otherwise>?</c:otherwise>
										</c:choose>
									</td>
									<sec:authorize access="hasAnyRole('ROLE_ADMIN', 'DEVELOPER')">									
										<c:if test="${user_in_session.user.roles[0].name == 'ROLE_ADMIN' || 
													 !empty user_in_session.user.profile && user_in_session.user.profile.teamId == team.id}">
									
											<td style="text-align: center; width: 25px; border:1px solid #999;">
														<a href="#" onclick="player.editPlayer('${row.id}'); return false;">
															<img src="${pageContext.request.contextPath}/images/edit.png" alt="<spring:message code="edit"/>" /> 
														</a>											
											</td>
										</c:if>											
									</sec:authorize>									
									<sec:authorize access="hasAnyRole('ROLE_ADMIN')">
										<td style="text-align: center; width: 25px; border:1px solid #999;">
											<spring:url var="deleteURL" value="/players/delete.do">
												<spring:param name="id" value="${row.id}"></spring:param>
												<spring:param name="team.id" value="${team.id}"></spring:param>
											</spring:url> 
											<a href="#" onclick="player.confirmDeletePlayer('${deleteURL}'); return false;"> 
												<img src="${pageContext.request.contextPath}/images/delete.png" alt="<spring:message code="delete"/>" /> 
											</a>
										</td>
									</sec:authorize>
									</tr>
								</c:forEach>
							</table>
							</c:if>
						</div>
					</td>
			</tr>
		</tbody>
	</table>
	
	<jsp:include page="secure/editPlayer.jsp" />	
	
	<jsp:include page="secure/buyPlayer.jsp" />
	
	<jsp:include page="includes/deletePlayer.jsp" />
	

</body>
</html>