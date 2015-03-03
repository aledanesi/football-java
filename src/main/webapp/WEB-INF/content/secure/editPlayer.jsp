<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
 

	<!-- edit player -->
	<div id="dEditPlayer" style="display: none">
		
		<p class="validateTips"></p>
		
		<spring:url var="saveURL" value="/players/save.do" /> 
		
		<form:form commandName="player" id="editPlayer" action="${saveURL}" enctype="multipart/form-data" method="POST" style="width: 450px;">
			<form:hidden path="id"/>
			<form:hidden path="image" />		
			<input type="hidden" id="team_id" name="team.id" value="${team.id}" />			
			<input type="hidden" id="team_nation_id" name="team.nation.id" value="${team.nation.id}" />
			<input type="hidden" id="team_division_id" name="team.division.id" value="${team.division.id}" />		
			<input type="hidden" id="team_prev_id" name="teamPrev.id" value="${teamPrev.id}" />		

			<input type="hidden" id="retired" name="retired" value="${retired}" />
			<input type="hidden" id="unemployed" name="unemployed" value="${unemployed}" />	
			
			<input type="hidden" id="locale" name="locale" value="${pageContext.response.locale}" />
			
			<div id="tabs">
				<ul>
				<li><a href="#tabs-1">Dati generali</a></li>
				<li><a href="#tabs-2">Dati tecnici</a></li>
				<li><a href="#tabs-3">Contratto</a></li>
				</ul>
			<div id="tabs-1">
				<table class="standard_tabelle" style="border: 0px">
					
					<tr>
						<td style="text-align: center">
							<spring:url var="imageURL" value="/players/image.do" />
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
						  <div class="fitem_label"><spring:message code="editPlayer.message6" />:</div>
						  <div class="fitem_span">
								<form:input path="completeName" />
								<form:errors path="completeName" />
						  </div>						
						</td>
						<td>
						  <div class="fitem_label"><spring:message code="editPlayer.message12" /> / <spring:message code="editPlayer.message13" />:</div>
						  <div class="fitem_span">
								<form:input path="height" style="width: 30px" maxlength="3" />
								<form:errors path="height" />
								&nbsp;&nbsp;&nbsp;
								<form:input path="weight" style="width: 30px" maxlength="3" />
								<form:errors path="weight" />

						  </div>
						</td>						
					</tr>				
					<tr>
						<td>				  
						  <div class="foitem_label"><spring:message code="editPlayer.message7" />:</div>
						  <div class="fitem_span">
								<fmt:formatDate var="f_dateOfBirth" value="${player.dateOfBirth}" type="both" pattern="dd/MM/yyyy" />	
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
							  <form:select path="nationality.id" id="nation_id">
								  <form:option  value="" />
								  <form:options items="${nationList}" itemValue="id" itemLabel="name"/>
								</form:select>
						  </div>
						</td>
						<td>				  
						  <div class="fitem_label"><spring:message code="editPlayer.message10" /> 2:</div>
						  <div class="fitem_span">
							  <form:select path="nationality2.id" id="nation2_id">
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
								<form:select path="teamBranch">
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
						  <div class="fitem_label"><spring:message code="editPlayer.message11" />:</div>
						  <div class="fitem_span">
								<form:select path="national">
									<form:option value="0">&nbsp;</form:option>
									<c:forEach var="type" items="<%=com.jfootball.domain.type.NationalType.values()%>">
										<c:choose>
											<c:when test="${type == 'NATIONAL_U18'}">
												<form:option value="${type.value}"><spring:message code="editPlayer.nationalU18" /></form:option>
											</c:when>
											<c:when test="${type == 'NATIONAL_U19'}">
												<form:option value="${type.value}"><spring:message code="editPlayer.nationalU19" /></form:option>
											</c:when>
											<c:when test="${type == 'NATIONAL_U20'}">
												<form:option value="${type.value}"><spring:message code="editPlayer.nationalU20" /></form:option>
											</c:when>
											<c:when test="${type == 'NATIONAL_U21'}">
												<form:option value="${type.value}"><spring:message code="editPlayer.nationalU21" /></form:option>
											</c:when>
											<c:when test="${type == 'NATIONAL'}">
												<form:option value="${type.value}"><spring:message code="editPlayer.national" /></form:option>
											</c:when>	
											<c:when test="${type == 'RETIRED'}">
												<form:option value="${type.value}"><spring:message code="editPlayer.retired" /></form:option>
											</c:when>																																							
											<c:otherwise></c:otherwise>									
										</c:choose>
									</c:forEach>
								</form:select>
						  </div>
						
						</td>								
					</tr>
					
					<tr>
						<td>
						  <div class="fitem_label"><spring:message code="editPlayer.message14" />:</div>
						  <div class="fitem_span">
								<form:input path="number" style="width: 20px" maxlength="2" />
								<form:errors path="number" />
						  </div>
						</td>
						<td>
						  <div class="fitem_label"><spring:message code="editPlayer.message2" />:</div>
						  <div class="fitem_span">
								<form:checkbox path="captain" id="captain" />
						  </div>
						</td>						
					</tr>
					
					<tr>
						<td>
							  <div class="fitem_label"><spring:message code="editPlayer.message15" />:</div>
							  <div class="fitem_span">
									<select id="ruolo_id">
											<option value="01"><spring:message code="editPlayer.message25" /></option>
											<option value="02"><spring:message code="editPlayer.message26" /></option>
											<option value="03"><spring:message code="editPlayer.message27" /></option>
											<option value="04"><spring:message code="editPlayer.message28" /></option>
										</select>
							  </div>
						</td>
						<td>				  
							  <div class="fitem_label"><spring:message code="editPlayer.message16" />:</div>
							  <div class="fitem_span">
									<form:select path="position.id" id="position_id" />
							  </div>
						</td>			
					</tr>	
					<tr>
						<td></td>
						<td>
							  <div class="fitem_label"><spring:message code="editPlayer.message17" />:</div>
							  <div class="fitem_span">
								<form:select path="foot">
									<form:option value="0">&nbsp;</form:option>
									<c:forEach var="type" items="<%=com.jfootball.domain.type.FootType.values()%>">
										<c:choose>
											<c:when test="${type == 'RIGHT'}">
												<form:option value="${type.value}"><spring:message code="editPlayer.rightFoot" /></form:option>
											</c:when>
											<c:when test="${type == 'LEFT'}">
												<form:option value="${type.value}"><spring:message code="editPlayer.leftFoot" /></form:option>
											</c:when>
											<c:when test="${type == 'BOTH'}">
												<form:option value="${type.value}"><spring:message code="editPlayer.bothFoot" /></form:option>
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
									<form:input path="grossWeeklySalary" style="width: 80px" onkeyup="formatCurrency(jQ(this))"  />
									<form:errors path="grossWeeklySalary" />
							  </div>
							  <input type="hidden" id="netAnnualSalary" name="netAnnualSalary" value="${player.netAnnualSalary}" />
							</c:when>
							<c:otherwise>
							  <div class="fitem_label"><spring:message code="editPlayer.message19" />:</div>
							  <div class="fitem_span">
									<form:input path="netAnnualSalary" style="width: 80px" onkeyup="formatCurrency(jQ(this))" />
									<form:errors path="netAnnualSalary" />
							  </div>
							  <input type="hidden" id="grossWeeklySalary" name="grossWeeklySalary" value="${player.grossWeeklySalary}" />
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
					
					<tr>		
						<td>	
						  <div class="fitem_label"><spring:message code="editPlayer.message21" />:</div>
						  <div class="fitem_span">
								<form:select path="status">
									<form:option value="0">&nbsp;</form:option>
									<c:forEach var="type" items="<%= com.jfootball.domain.type.StatusType.values() %>">
										<c:choose>
											<c:when test="${type == 'SUMMER_SIGNING'}">
												<form:option value="${type.value}"><spring:message code="editPlayer.summerSigning" /></form:option>
											</c:when>
											<c:when test="${type == 'WINTER_SIGNING'}">
												<form:option value="${type.value}"><spring:message code="editPlayer.winterSigning" /></form:option>
											</c:when>
											<c:when test="${type == 'RETURN_FROM_LOAN'}">
												<form:option value="${type.value}"><spring:message code="editPlayer.returnFromLoan" /></form:option>
											</c:when>
											<c:otherwise></c:otherwise>									
										</c:choose>
									</c:forEach>
								</form:select>					  
						  </div>					
						</td>					
						<td style="white-space: nowrap">
							  <div class="fitem_label"><spring:message code="editPlayer.message3" />:</div>
							  <div class="fitem_span">
									<form:checkbox path="onLoan" id="prestito" />
							  </div>						  
						</td>			
					</tr>				

					<tr id="rowPrestito" style="display: none; border: 1px; color: red; font-weight: bold;">
						<td>
						  <div class="fitem_label"><spring:message code="editPlayer.message22" />:</div>
						  <div class="fitem_span">
							<form:select id="owner_nation_id" path="teamOwner.nation.id" items="${nationList}" itemLabel="name" itemValue="id" />
						  </div>
						</td>
						<td>
						  <div class="fitem_label"><spring:message code="editPlayer.message23" />:</div>
						  <div class="fitem_span">
							<select id="owner_division_id" name="teamOwner.division.id">
							  <c:forEach var="item" items="${divisionList}">
								<option value="${item.id}">
								  <c:out value="${item.name}" />
								</option>
							  </c:forEach>
							</select>                      	
						  </div>
						</td>
					</tr>
					<tr id="rowPrestito2" style="display: none; border: 1px; color: red; font-weight: bold;">
						<td>

						</td>
						<td>				  
						  <div class="fitem_label"><spring:message code="editPlayer.message24" />:</div>
						  <div class="fitem_span">
							<form:select id="owner_team_id" path="teamOwner.id" items="${teamList}" itemLabel="name" itemValue="id" />
						  </div>
						</td>
					</tr>					
					
				</table>	
			
			</div>

			</div>		
		
		</form:form>

	</div>
	<!-- fine div -->