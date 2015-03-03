<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
 

	<!-- edit player -->
	<div id="dEditStaff" style="display: none">
		
		<p class="validateTips"></p>
		
		<spring:url var="saveURL" value="/staffs/save.do" /> 
		
		<form:form commandName="staff" id="editStaff" action="${saveURL}" enctype="multipart/form-data" method="POST" style="width: 450px;">
			<form:hidden path="id"/>
			<form:hidden path="image" />		
			<input type="hidden" id="staff_team_id" name="team.id" value="${team.id}" />			
			<input type="hidden" id="staff_team_nation_id" name="team.nation.id" value="${team.nation.id}" />
			<input type="hidden" id="staff_team_division_id" name="team.division.id" value="${team.division.id}" />		
			<input type="hidden" id="staff_team_prev_id" name="teamPrev.id" value="${teamPrev.id}" />		

			<input type="hidden" id="staff_retired" name="retired" value="${retired}" />
			<input type="hidden" id="staff_unemployed" name="unemployed" value="${unemployed}" />	
			
			<input type="hidden" id="locale" name="locale" value="${pageContext.response.locale}" />
			
			<div id="tabs_staff">
				<ul>
				<li><a href="#tabs-1">Dati generali</a></li>
				<li><a href="#tabs-2">Dati tecnici</a></li>
				<li><a href="#tabs-3">Contratto</a></li>
				</ul>
			<div id="tabs-1">
				<table class="standard_tabelle" style="border: 0px">
					
					<tr>
						<td style="text-align: center">
							<spring:url var="imageURL" value="/staffs/image.do" />
							<input type="hidden" value="${imageURL}" name="urlImage" />
							<img src="" id="myImage" style="width: 58px; padding: 0px; border-color: #bbb; border-width: 1px; border-style: solid; background-color: rgb(255, 255, 255);" />
						</td>							
						<td>				  
						  <div class="fitem_label"><spring:message code="editPlayer.message1" />:</div>
						  <div class="fitem_span"><form:input path="image" type="file" /></div>
						</td>								
					</tr>
					<tr>
						<td>
						  <div class="fitem_label"><spring:message code="editPlayer.message4" />:</div>
						  <div class="fitem_span">
								<form:input path="firstName" />
								<form:errors path="firstName" />
						  </div>						
						</td>
						<td>				  
							<div class="foitem_label"><spring:message code="editPlayer.message5" />:</div>
						  <div class="fitem_span">
								<form:input path="lastName" />
								<form:errors path="lastName" />
						  </div>
						</td>	
					</tr>
				
					<tr>
						<td>				  
						  <div class="foitem_label"><spring:message code="editPlayer.message7" />:</div>
						  <div class="fitem_span">
								<fmt:formatDate var="f_dateOfBirth" value="${staff.dateOfBirth}" type="both" pattern="dd/MM/yyyy" />	
								<form:input path="dateOfBirth" value="${f_dateOfBirth}" style="width: 80px" maxlength="10" onkeyup="formatDate(this, event)" />
								<form:errors path="dateOfBirth" />
						  </div>
						</td>
						<td>
						  <div class="fitem_label"><spring:message code="editPlayer.message8" />:</div>
						  <div class="fitem_span">
								<form:input path="placeOfBirth" />
								<form:errors path="placeOfBirth" />
						  </div>
						</td>
						<td>
			  
						</td>			
					</tr>

					<tr>
						<td>
						  <div class="fitem_label"><spring:message code="editPlayer.message10" /> 1:</div>
						  <div class="fitem_span">
							  <form:select path="nationality.id" id="staff_nation_id">
								  <form:option  value="" />
								  <form:options items="${nationList}" itemValue="id" itemLabel="name"/>
								</form:select>
						  </div>
						</td>
						<td>				  
						  <div class="fitem_label"><spring:message code="editPlayer.message10" /> 2:</div>
						  <div class="fitem_span">
							  <form:select path="nationality2.id" id="staff_nation2_id">
								  <form:option  value="" />
								  <form:options items="${nationList}" itemValue="id" itemLabel="name"/>
								</form:select>
						  </div>
						</td>
						<td>

						</td>			
					</tr>
					
					
				</table>	
			</div>
			<div id="tabs-2">
		
				<table class="standard_tabelle" style="border: 0px">
					
					<tr>
						<td>
						  <div class="fitem_label"><spring:message code="editPlayer.message9" />:</div>
						  <div class="fitem_span">
								<form:select path="teamBranch" id="staff_teamBranch">
									<c:forEach var="type" items="<%=com.jfootball.domain.type.TeamBranchType.values()%>">
										<c:choose>
											<c:when test="${type == 'FIRST_TEAM'}">
												<form:option value="${type.value}"><spring:message code="editPlayer.firstTeam" /></form:option>
											</c:when>
											<c:when test="${type == 'SECOND_TEAM'}">
												<form:option value="${type.value}"><spring:message code="editPlayer.secondTeam" /></form:option>
											</c:when>
											<c:when test="${type == 'YOUTH_TEAM'}">
												<form:option value="${type.value}"><spring:message code="editPlayer.youthTeam" /></form:option>
											</c:when>
											<c:when test="${type == 'OTHER_PLAYERS'}">
												<form:option value="${type.value}"><spring:message code="editPlayer.otherPlayers" /></form:option>
											</c:when>																																				
											<c:otherwise></c:otherwise>									
										</c:choose>
									</c:forEach>
								</form:select>					  		
						  </div>
						</td>
						<td>
						</td>								
					</tr>
					
				</table>	
			</div>			
			<div id="tabs-3">
		
				<table class="standard_tabelle" style="border: 0px">
					
					<tr>
						<td style="width: 55%">
							<c:choose>
							<c:when test="${team.nation.id == 13}">
							  <div class="fitem_label"><spring:message code="editPlayer.message18" />:</div>
							  <div class="fitem_span">
									<form:input id="staff_grossWeeklySalary" path="grossWeeklySalary" style="width: 80px" onkeyup="formatCurrency(jQ(this))"  />
									<form:errors path="grossWeeklySalary" />
							  </div>
							  <input type="hidden" id="staff_netAnnualSalary" name="netAnnualSalary" value="${player.netAnnualSalary}" />
							</c:when>
							<c:otherwise>
							  <div class="fitem_label"><spring:message code="editPlayer.message19" />:</div>
							  <div class="fitem_span">
									<form:input id="staff_netAnnualSalary" path="netAnnualSalary" style="width: 80px" onkeyup="formatCurrency(jQ(this))" />
									<form:errors path="netAnnualSalary" />
							  </div>
							  <input type="hidden" id="staff_grossWeeklySalary" name="grossWeeklySalary" value="${player.grossWeeklySalary}" />
							</c:otherwise>						
							</c:choose>
						</td>
		
						<td style="width: 45%">
						  <div class="fitem_label"><spring:message code="editPlayer.message20" />:</div>
						  <div class="fitem_span">
								<form:input path="contractUntil" style="width: 80px" maxlength="10" onkeyup="formatDate(this, event)" />
								<form:errors path="contractUntil" />
						  </div>
						</td>
					</tr>		

				</table>	
			</div>

			</div>		
		
		</form:form>

	</div>
	<!-- fine div -->