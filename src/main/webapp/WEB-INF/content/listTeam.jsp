<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>

<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions"  %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"    uri="http://www.springframework.org/security/tags"%>

<html>
	<head>
		<title></title>    
	</head>

	<body>
		
		<div style="margin-bottom: 20px; margin-left: 25px">
			<div id="menutop">
				<h3>
					<sec:authorize access="hasAnyRole('ROLE_ADMIN')">
							<span class="label label-primary"><a href="#" onclick="team.newTeam()" title="<spring:message code="listTeam.message2" />"><i class="fa fa-group fa-lg"></i></a></span>
					</sec:authorize>
					<span class="label label-primary"><a href="#" onclick="player.searchPlayer()" title="<spring:message code="listTeam.message1" />"><i class="fa fa-search fa-lg"></i></a></span>
				</h3>
			</div>		
		</div>
	
		<div align="center">
	
			<spring:url var="viewURL" value="/players/listByTeam.do" />
	
			<form:form commandName="team" action="${viewURL}" id="viewPlayersForm" method="GET">
					<input type="hidden" name="team.id" id="teamId" />
					<input type="hidden" name="teamCategory" id="teamCategory" value="1" />		 
			</form:form>
			
			<spring:url var="searchTeamURL" value="/teams/list.do" />
	
			<form:form name="saison" id="listTeam" commandName="team" action="${searchTeamURL}" class="formListTeam" method="POST">
				<table class="table_filter_team">
					<tbody><tr>
						<td class="table_filter_logo" rowSpan="3">
							<img src="${pageContext.request.contextPath}/images/divisions/${team.division.id}.jpg">
						</td>
						<th>
							<table>
								<tr>
									<th>
										<sec:authorize access="hasRole('ROLE_ADMIN')">
											<form:select id="continent_id" path="nation.continent.id" items="${continentList}" itemLabel="name" itemValue="id" />
										</sec:authorize>							
									</th>
									<th>
										<form:select id="nation_id" path="nation.id" items="${nationList}" itemLabel="name" itemValue="id" />							
									</th>
									<th>
										<form:select id="division_id" path="division.id" items="${divisionList}" itemLabel="name" itemValue="id" />
									</th>
								</tr>
							</table>
						</th>
					</tr>
					<tr>
						<td>${fn:length(teamList)} <spring:message code="listTeam.message3" /></td>
					</tr>
					<tr>
						<td>${playersCount} <spring:message code="listTeam.message4" /></td>
					</tr>
					</tbody>
				</table>
			</form:form>	
		
			<div class="div_top_space">
				<display:table name="${teamList}" id="row" requestURI="" class="table_team">
				
					<display:column style="text-align: center; width: 10%">
						<spring:url var="imageURL" value="/teams/image.do">
							<spring:param name="id">${row.id}</spring:param>
						</spring:url>
						<img class="minifoto" src="${imageURL}" style="width: 60%"/>
					</display:column> 
				
					<display:column titleKey="listTeam.message5" style="width: 40%">
						<a href="#" class="targetLinkTeam" data-team="${row.id}" style="font-size: 14px;">${row.name}</a>
					</display:column> 
				
					<display:column property="stadium" titleKey="listTeam.message6" style="width: 35%" />
				
					<display:column property="posti" titleKey="listTeam.message7" style="width: 10%" />
				
					<sec:authorize access="hasRole('ROLE_ADMIN')">
						<display:column style="text-align: center; width: 25px;">
							<a href="#" onclick="team.editTeam('${row.id}'); return false;">
								<i class="fa fa-edit fa-2x" style="margin-left: 2px; margin-right: 2px"></i>
							</a>
						</display:column>
						<display:column style="text-align: center; width: 25px;">
							<spring:url var="deleteURL" value="/teams/delete.do">
								<spring:param name="id">${row.id}</spring:param>
								<spring:param name="nation.id" value="${row.nation.id}"></spring:param>
								<spring:param name="division.id" value="${row.division.id}"></spring:param>						
							</spring:url>
							<a href="#" onclick="team.confirmDeleteTeam('${deleteURL}'); return false;">
								<i class="fa fa-remove fa-2x" style="margin-left: 2px; margin-right: 2px"></i>
							</a>
						</display:column>
					</sec:authorize>
				
				</display:table>
			</div>
		</div>
	
		<%-- DIV CONFIRM --%>
		<div id="dConfirmDeleteTeam"></div>
	
		<%-- JSP INCLUDE --%>	
		<jsp:include page="secure/searchPlayer.jsp" />
	    <jsp:include page="secure/editTeam.jsp" />
	    <jsp:include page="secure/editUser.jsp" />	
	
		<%-- DWR SCRIPTS --%>
	    <script type='text/javascript' src='${pageContext.request.contextPath}/dwr/engine.js'></script>
	    <script type='text/javascript' src='${pageContext.request.contextPath}/dwr/util.js'></script>
	    <script type='text/javascript' src='${pageContext.request.contextPath}/dwr/interface/businessDelegate.js'></script>	
	
	    <%-- FUNCTION SPECIFIC --%>
	    <script type="text/javascript" src="${pageContext.request.contextPath}/scripts/team.js"></script> 
	    <script type="text/javascript" src="${pageContext.request.contextPath}/scripts/player.js"></script> 
	    <script type="text/javascript" src="${pageContext.request.contextPath}/scripts/user.js"></script>  

	</body>
</html>