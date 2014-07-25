<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<html>
<head>

	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    
	<script type='text/javascript' src='${pageContext.request.contextPath}/dwr/engine.js'></script>
	<script type='text/javascript' src='${pageContext.request.contextPath}/dwr/util.js'></script>
	<script type='text/javascript' src='${pageContext.request.contextPath}/dwr/interface/teamManager.js'></script>
	<script type='text/javascript' src='${pageContext.request.contextPath}/dwr/interface/divisionManager.js'></script>
	<script type='text/javascript' src='${pageContext.request.contextPath}/dwr/interface/nationManager.js'></script>
	

    <%-- FUNCTION SPECIFIC --%>
    <script type="text/javascript" src="${pageContext.request.contextPath}/scripts/team.js"></script>   
    
</head>

<spring:url var="searchTeamURL" value="/teams/list.do" />

<spring:url var="searchPlayerURL" value="/players/search.do"/>

<body>
	  	
	<!-- mette un spazio tra i pulsanti e il filtro -->
	<div id="header2">
		<!-- stile dei pulsanti -->
		<div id="menutop">
			<ul>
				<li>
					<a href="${searchPlayerURL}"><spring:message code="searchPlayer" /></a>
				</li>
				<sec:authorize access="hasRole('ROLE_ADMIN')">
					<li><a href="#" onclick="team.newTeam()"><spring:message code="insertTeam" /> </a></li>
				</sec:authorize>
			</ul>
		</div>
	</div>
	
	<spring:url var="viewURL" value="/players/listByTeam.do" />

	<form:form commandName="team" action="${viewURL}" id="viewPlayersForm" method="POST">
			<input type="hidden" name="team.id" id="teamId" />
			<input type="hidden" name="teamCategory" id="teamCategory" value="1" />		 
	</form:form>
	
	
<form:form commandName="team" action="${searchTeamURL}" id="wb_seite" name="saison" style="text-align: center"  method="POST">
	<table class="tabelle_spieler" cellSpacing="1" cellPadding="0" style="width: 600px; text-align: center; margin-left: 65px;" >
		<tbody><tr>
			<td style="margin: 0px; padding: 0px 3px; border: 1px solid rgb(187, 187, 187); width: 60px; background-color: rgb(255, 255, 255);" class="ac vm" rowSpan="3">
				<img class="fl vm" title="Serie A" alt="-" src="${pageContext.request.contextPath}/images/divisions/${team.division.id}.jpg">
			</td>
			<td style='background: url("http://static.transfermarkt.net/img/bg_table_th_gross.jpg") repeat-x rgb(230, 230, 230);' class="blau vm">

			    <h1 style="color: rgb(255, 255, 255);"> <a style="color: rgb(255, 255, 255);" class="s18 tdn" href="#">&nbsp;</a>					
			        <div style="height: 0px; position: relative;">
						<div style="margin: -33px 5px 0px 50px; float: left; position: absolute;" class="mcdropdown">

							<sec:authorize access="hasRole('ROLE_ADMIN')">
								<form:select id="continent_id" path="nation.continent.id" items="${continentList}" itemLabel="name" itemValue="id" />
							</sec:authorize>
						
							<form:select id="nation_id" path="nation.id" items="${nationList}" itemLabel="name" itemValue="id" />

							<form:select id="division_id" path="division.id" items="${divisionList}" itemLabel="name" itemValue="id" />
						</div>
			        </div>
				</h1>
			</td>			
		</tr>
		<tr>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
		</tr>
			</tbody></table>
</form:form>
	
	<br />
	<br />

	<div align="center">

		<display:table name="${teamList}" id="row" pagesize="22" requestURI="" style="width: 600px" class="standard_tabelle">
			<display:column style="text-align: center;">
				<spring:url var="imageURL" value="/teams/image.do">
					<spring:param name="id">${row.id}</spring:param>
				</spring:url>
				<img class="minifoto" src="${imageURL}" width="30" height="40"/>
			</display:column> 
			<display:column title="Squadra">
				<a href="#" class="targetLinkTeam" data-team="${row.id}">${row.name}</a>
			</display:column> 
			<display:column property="stadium" title="Stadio" />
			<display:column property="posti" title="Posti" />
			<sec:authorize access="hasRole('ROLE_ADMIN')">
				<display:column style="text-align: center; width: 25px">
					<a href="#" onclick="team.editTeam('${row.id}'); return false;">
						<img src="${pageContext.request.contextPath}/images/edit.png" alt="<spring:message code="edit"/>" /> 
					</a>
				</display:column>
				<display:column style="text-align: center; width: 25px">
					<spring:url var="deleteURL" value="/teams/delete.do">
						<spring:param name="id">${row.id}</spring:param>
						<spring:param name="nation.id" value="${row.nation.id}"></spring:param>
						<spring:param name="division.id" value="${row.division.id}"></spring:param>						
					</spring:url>
					<a href="#" onclick="team.confirmDeleteTeam('${deleteURL}'); return false;"> 
						<img src="${pageContext.request.contextPath}/images/delete.png" <alt="<spring:message code="delete"/>" />
					</a>
				</display:column>
			</sec:authorize>
		</display:table>
	</div>

	<jsp:include page="secure/editTeam.jsp" />
	
	<jsp:include page="secure/deleteTeam.jsp" />	
	
	</body>
</html>