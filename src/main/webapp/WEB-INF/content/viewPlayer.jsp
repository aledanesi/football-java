<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<%@ taglib prefix="custom" uri="http://jfooball.it/functions"  %>


<html>
<head>
	<title><spring:message code="title"/> ${player.firstName} ${player.lastName}</title>
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


<form:form commandName="player" action="${listPlayerURL}" id="returnPlayersPageForm" method="GET">
	<input type="hidden" name="team.id" id="teamId" />
	<input type="hidden" name="teamCategory" id="teamCategory" value="1" />					 
</form:form>

<spring:url var="viewURL" value="/players/view.do" />

<form:form commandName="player" action="${viewURL}" id="viewPlayerPageForm" method="POST">
	<input type="hidden" name="id" id="id" value="${player.id}" />
</form:form>


	<div style="margin-bottom: 20px; margin-left: 25px">
		<div id="menutop">
			<h3>
				<c:if test="${! (player.retired || player.unemployed )}">
						<span class="label label-primary"><a href="#" class="targetLinkViewPlayer" data-id="${player.team.id}" title="<spring:message code="returnListPlayer"/>"><i class="fa fa-reply fa-lg"></i></a></span>
				</c:if>
				<sec:authorize access="hasAnyRole('ROLE_ADMIN', 'DEVELOPER')">									
					<c:if test="${user_in_session.user.roles[0].name == 'ROLE_ADMIN' || 
								 !empty user_in_session.user.profile && user_in_session.user.profile.teamId == player.team.id}"> 
							<span class="label label-primary"><a href="#" onclick="player.editPlayer('${player.id}'); return false;" title="<spring:message code="viewPlayer.message1"/>"><i class="fa fa-edit fa-lg"></i></a></span>
	
							<span class="label label-primary"><a href="#" onclick="career.insertCareer('${player.id}'); return false;" title="<spring:message code="viewPlayer.message2"/>"><i class="fa fa-history fa-lg"></i></a></span>
	
						<c:if test="${! player.retired }"> 
							<span class="label label-primary"><a href="#" onclick="player.movePlayer('${player.id}'); return false;" title="<spring:message code="viewPlayer.message3"/>"><i class="fa fa-exchange fa-lg"></i></a></span>
	
	
							<c:if test="${! player.unemployed }"> 
	
									<spring:url var="unemployURL" value="/players/withoutTeam.do">
											<spring:param name="id" value="${player.id}"></spring:param>
									</spring:url> 								

									<span class="label label-primary"><a href="#" onclick="player.confirmUnEmployPlayer('${unemployURL}'); return false;" title="<spring:message code="viewPlayer.message4"/>"><i class="fa fa-circle-o fa-lg"></i></a></span>
	
							</c:if>
							<c:if test="${! player.retired }"> 
	
									<spring:url var="retireURL" value="/players/endCareer.do">
										<spring:param name="id" value="${player.id}"></spring:param>
									</spring:url> 								
	
									<span class="label label-primary"><a href="#" onclick="player.confirmRetirePlayer('${retireURL}'); return false;" title="<spring:message code="viewPlayer.message5"/>"><i class="fa fa-ban fa-lg"></i></a></span>
	
							</c:if> 				            
						</c:if>
					</c:if>	
				</sec:authorize>
				<span class="label label-primary"><a href="#" onclick="player.searchPlayer()" title="<spring:message code="listTeam.message1" />"><i class="fa fa-search fa-lg"></i></a></span>

			</h3>
		</div>		
	</div>


	<div class="divTable">
		<!-- RIGA 1 -->
		<div class="divRow">
			<!-- RIGA 1 - CELLA 1 -->
			<div class="divCell">
				<table cellspacing="1" cellpadding="0" class="tabelle_spieler">
					<tbody>
						<tr>
							<td style="width: 100px;background-color: rgb(241, 241, 241);margin:0px;padding:0 3px;border:1px solid #bbb;" class="ac" rowspan="3">
								<img src="${imageURL}">
							</td>			
							<td style="background-color: rgb(14, 46, 128)" class="blau"><h1 style="color:#fff;">${player.number} ${player.firstName} ${player.lastName}</h1></td>
						</tr>
						<tr >
							<td class="odd">
								<c:choose>
									<c:when test="${player.retired}">
										<spring:message code="viewPlayer.message6"/>
									</c:when>
									<c:when test="${player.unemployed}">
										<spring:message code="viewPlayer.message7"/>
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
								<c:if test="${! (player.retired || player.unemployed)}">
									<c:choose>
										<c:when test="${player.national == 1}">
											<spring:message code="viewPlayer.nationalU18" />
										</c:when>
										<c:when test="${player.national == 2}">
											<spring:message code="viewPlayer.nationalU19" />
										</c:when>
										<c:when test="${player.national == 3}">
											<spring:message code="viewPlayer.nationalU20" />
										</c:when>
										<c:when test="${player.national == 4}">
											<spring:message code="viewPlayer.nationalU21" />
										</c:when>
										<c:when test="${player.national == 5}">
											<spring:message code="viewPlayer.national" />
										</c:when>
										<c:when test="${player.national == 6}">
											<spring:message code="viewPlayer.retired" />
										</c:when>								
										<c:otherwise>
											<spring:message code="viewPlayer.actualyNoNation" />
										</c:otherwise>				
									</c:choose>						
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
           	<!-- RIGA 1 - CELLA 2 -->
           	<div class="divCell2">

				<p class="hl_startseite"><spring:message code="viewPlayer.message25"/></p>
				<div class="hell lhg s10 p5">
					<form method="post" name="neuer_spieler" action="">
						<select onchange="player.changePlayer()" class="select" name="view_player_id" id="view_player_id" style="width: 250px">
							<option class="option" value=""><spring:message code="viewPlayer.message26"/>:</option>
							<optgroup style="padding: 2px 15px;" label="${player.team.name}">
						    <c:forEach items="${playerList}" var="row">
						     	<option class="option" value="${row.id}">${row.firstName} ${row.lastName}</option>
								</c:forEach>							
							</optgroup>			
					 	</select>
					</form>
				</div>

			</div>
		</div>
		
		<!-- RIGA 2 -->
        <div class="divRow">
        	<!-- RIGA 2 - CELLA 1 -->
        	<div class="divCell">        		
        		
					<p class="hl_startseite">
						<sec:authorize access="hasRole('ROLE_ADMIN')">
							<c:if test="${! empty player.lastTimeModify}">
									<span class="toggle-icon"><i class="fa fa-info-circle fa-2x" title="<spring:message code="viewPlayer.message10"/>: <fmt:formatDate pattern="dd/MM/yyyy - [HH:mm:SS]" value="${player.lastTimeModify}" /> da ${player.lastUserModify}"></i></span>
							</c:if>
						</sec:authorize>
						&nbsp;<spring:message code="viewPlayer.message28"/> ${player.firstName} ${player.lastName}
					</p>
				<table cellspacing="1" cellpadding="2" style="margin-top:0;border:1px solid #999; background-color: rgb(241, 241, 241);" class="standard_tabelle">
				<tbody>
					<tr>
					<td class="al vt">
					<table cellspacing="1" cellpadding="0" class="standard_tabelle s10">
						<tbody>
						<tr class="odd">
							<td style="width:150px;"><spring:message code="viewPlayer.message11"/>:</td>
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
							<td><spring:message code="viewPlayer.message12"/>:</td>
							<td><fmt:formatDate value="${player.dateOfBirth}" type="both" pattern="dd/MM/yyyy" /></td>
						</tr>
						<tr class="odd">
							<td><spring:message code="viewPlayer.message13"/>:</td>
							<td>${player.placeOfBirth}</td>
						</tr>
						<tr class="even">
							<td><spring:message code="viewPlayer.message14"/>:</td>
							<td>${player.age} <spring:message code="viewPlayer.years"/></td>
						</tr>
						<tr class="odd">
							<td><spring:message code="viewPlayer.message15"/>:</td>
							<td>${player.height}</td>
						</tr>
						<tr class="even">
							<td><spring:message code="viewPlayer.message16"/>:</td>
							<td class="adr">
								<span class="flaggen_sprite sprite_land_75 vt mt4">
									<img src="${pageContext.request.contextPath}/images/flags/${player.nationality.id}.png" id="flag" style="margin-top: 4px" />
									<span class="country-name s10">${custom:nationalCapitalize(player.nationality.name)}</span>
										<c:if test="${! empty player.nationality2.id}">
											<br><img src="${pageContext.request.contextPath}/images/flags/${player.nationality2.id}.png" alt="${player.nationality2.name}" id="flag" style="margin-top: 4px" />
										</c:if>
									</span>
									<span class="country-name s10">${custom:nationalCapitalize(player.nationality2.name)}</span>
								</span>
							</td>
						</tr>
						<tr class="odd">
							<td style="width:115px;" class="category"><spring:message code="viewPlayer.message17"/>:</td>
							<td><spring:message code="${player.position.descPosizione}" /></td>
						</tr>
						<tr class="even">
							<td><spring:message code="viewPlayer.message18"/>:</td>
							<td>
								<c:choose>
									<c:when test="${player.foot == 1}">
										<spring:message code="viewPlayer.rightFoot" />
									</c:when>
									<c:when test="${player.foot == 2}">
										<spring:message code="viewPlayer.leftFoot" />
									</c:when>
									<c:when test="${player.foot == 3}">
										<spring:message code="viewPlayer.bothFoot" />
									</c:when>
									<c:otherwise></c:otherwise>				
								</c:choose>
							</td>
						</tr>
						<%--
						<c:if test="${! (player.retired || player.unemployed) }"> 
							<tr class="odd">
								<td>Valore di mercato:</td>
								<td class="note">${custom:currencyValue(player.value)} &euro;</td>
							</tr>
						</c:if>
						--%>
						</tbody>
						</table>
					</td>
					</tr>
					</tbody>
				</table>			
			</div>
			<!-- RIGA 2 - CELLA 2 -->
            <div class="divCell2">
				<p class="hl_startseite"><spring:message code="viewPlayer.message27"/></p>
				<table class="tabelle_spieler" cellSpacing="0" cellPadding="0" style="height: 175px;">
					<tbody>
						<tr>
		        			<td style="padding: 2px 5px; border-bottom-color: rgb(255, 255, 255); border-bottom-width: 1px; border-bottom-style: solid; background-color: rgb(241, 241, 241);" class="vt" colSpan="2">
								<c:if test="${player.position.codRuolo == '01'}">
									<div style='height: 165px; position: relative; background-image: url(${pageContext.request.contextPath}/images/Torwart.png); background-repeat: no-repeat;'>
										<img class="${player.position.codCss}" title="${player.position.descPosizione}" alt="${player.position.descPosizione}" src="${pageContext.request.contextPath}/images/hauptposition.png">
									</div>
								</c:if>	
								<c:if test="${player.position.codRuolo == '02'}">
									<div style='height: 165px; position: relative; background-image: url(${pageContext.request.contextPath}/images/Abwehr.png); background-repeat: no-repeat;'>
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
									<div style='height: 165px; position: relative; background-image: url(${pageContext.request.contextPath}/images/Mittelfeld.png); background-repeat: no-repeat;'>
											<img class="${player.position.codCss}" title="${player.position.descPosizione}" alt="${player.position.descPosizione}" src="${pageContext.request.contextPath}/images/hauptposition.png">
									</div>
								</c:if>	
								<c:if test="${player.position.codRuolo == '04'}">
									<div style='height: 165px; position: relative; background-image: url(${pageContext.request.contextPath}/images/Sturm.png); background-repeat: no-repeat;'>
										<img class="${player.position.codCss}" title="${player.position.descPosizione}" alt="${player.position.descPosizione}" src="${pageContext.request.contextPath}/images/hauptposition.png">
									</div>
								</c:if>	
							</td>
						</tr>
					</tbody>
				</table>				
			</div>
        </div>
		
		<!-- RIGA 3 -->
        <div class="divRow">
        	<!-- RIGA 3 - CELLA 1 -->
        	<div class="divCell">
				<p class="hl_startseite">
					<a href="#" class="accordion-toggle">
						<span class="toggle-icon" style=""><i class="fa fa-plus-circle fa-2x"></i></span>
					</a>
					&nbsp;<spring:message code="viewPlayer.message29"/> ${player.firstName} ${player.lastName} 
				</p>
				<div class="accordion-content">
				<table cellspacing="1" cellpadding="2" style="margin-top:0;border:1px solid #999; background-color: rgb(241, 241, 241);" class="standard_tabelle">
				<tbody>
					<tr>
						<td class="al vt">
							<table cellspacing="1" cellpadding="0" class="standard_tabelle s10">
								<tbody>
									<c:choose>
										<c:when test="${player.team.nation.id == 13}">
											<tr class="odd">
												<td style="width: 150px;"><spring:message code="viewPlayer.message19"/>:</td>
												<td>
													<c:if test="${! empty player.grossWeeklySalary}">
														${custom:currencyValue(player.grossWeeklySalary)} ${custom:symbol(player.team.nation.language, player.team.nation.country)}
														<spring:message code="viewPlayer.message20"/>	
													</c:if>
												</td>
											</tr>						
										</c:when>
										<c:otherwise>
											<tr class="odd">
												<td style="width: 150px;"><spring:message code="viewPlayer.message21"/>:</td>
												<td>
													<c:if test="${! empty player.netAnnualSalary}">
														${custom:currencyValue(player.netAnnualSalary)} ${custom:symbol(player.team.nation.language, player.team.nation.country)}
														<spring:message code="viewPlayer.message22"/>	
													</c:if>
												</td>
											</tr>							
										</c:otherwise>
									</c:choose>
									<tr class="even">
										<td><spring:message code="viewPlayer.message23"/>:</td>
										<td>${player.contractUntil}</td>
									</tr>
								</tbody>
							</table>
						</td>
					<td>
				</table>
				</div>
			</div>
			
        </div>		
		
				<c:if test="${fn:length(careerList) gt 0}">
	    <!-- RIGA 4 -->
        <div class="divRow">
        	<!-- RIGA 4 - CELLA 1 -->
        	<div class="divCell">
					<p class="hl_startseite">
						<a href="#" class="accordion-toggle">
							<span class="toggle-icon" style=""><i class="fa fa-plus-circle fa-2x"></i></span>
						</a>
						&nbsp;<spring:message code="viewPlayer.message24"/> ${player.firstName} ${player.lastName} 
					</p>
					<div class="accordion-content">
					<table cellspacing="1" cellpadding="2" style="margin-top:0;border:1px solid #999; background-color: rgb(241, 241, 241);" class="standard_tabelle">
						<tbody>
							<tr>
								<td class="vt">
									<table cellspacing="1" cellpadding="0" class="standard_tabelle s10">
										<tbody>
											<tr class="thead1 tcharw">
											  <th style="width: 100px;"><spring:message code="viewPlayer.message30"/></th>
											  <th style="width: 200px;"><spring:message code="viewPlayer.message31"/></th>   
											  <th style="width: 100px;"><spring:message code="viewPlayer.message32"/></th>
											  <th style="width: 50px;"><spring:message code="viewPlayer.message33"/></th>
											  <th style="width: 50px;"><spring:message code="viewPlayer.message34"/></th>
											  <sec:authorize access="hasAnyRole('ROLE_ADMIN','DEVELOPER')"> 
												<th>&nbsp;</th>
											  </sec:authorize>
											  <sec:authorize access="hasAnyRole('ROLE_ADMIN','DEVELOPER')"> 
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
												<sec:authorize access="hasAnyRole('ROLE_ADMIN','DEVELOPER')"> 
												  <td style="text-align: center">
												  	<a href="#" onclick="career.updateCareer('${player.id}', '${careerStatus.id}'); return false;">
															<i class="fa fa-edit fa-2x"></i>
												  	</a>          
												  </td>
												</sec:authorize> 
												<sec:authorize access="hasAnyRole('ROLE_ADMIN', 'DEVELOPER')">									
													<c:if test="${user_in_session.user.roles[0].name == 'ROLE_ADMIN' || 
																 !empty user_in_session.user.profile && user_in_session.user.profile.teamId == player.team.id}">
														<td style="text-align: center">
															<spring:url var="deleteURL" value="/careers/delete.do">
																<spring:param name="id" value="${careerStatus.id}"></spring:param>
																<spring:param name="player.id" value="${player.id}"></spring:param>
															</spring:url>         
															<a href="#" onclick="career.confirmDeleteCareer('${deleteURL}'); return false;">
																<i class="fa fa-remove fa-2x"></i>
															</a>          
														</td>
													</c:if>
											  </sec:authorize> 
											</tr>
											</c:forEach>							
											
										</tbody>
									</table>
								</td>
							</tr>
						</tbody>
					</table>
					</div>
			</div>
        </div>		   
				</c:if>				
    </div>

	<jsp:include page="secure/searchPlayer.jsp" />
	
	<sec:authorize access="hasAnyRole('ROLE_ADMIN','DEVELOPER')">   

		<%-- DIV CONFIRM --%>
		<div id="dConfirmDeleteCareer"></div>
		<div id="dConfirmUnemployPlayer"></div>
		<div id="dConfirmRetirePlayer"></div>
		<div id="dConfirmTransferPlayer"></div>

		<%-- JSP INCLUDE --%>
		<jsp:include page="secure/editPlayer.jsp" />	
		<jsp:include page="secure/movePlayer.jsp" />
		<jsp:include page="secure/editCareer.jsp" />
			
	</sec:authorize>	
	
	<%-- DWR SCRIPTS --%>
	<script type='text/javascript' src='${pageContext.request.contextPath}/dwr/engine.js'></script>
	<script type='text/javascript' src='${pageContext.request.contextPath}/dwr/util.js'></script>
	<script type='text/javascript' src='${pageContext.request.contextPath}/dwr/interface/businessDelegate.js'></script>
	
	<%-- FUNCTION SPECIFIC --%>
	<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/player.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/career.js"></script>

</body>
</html>