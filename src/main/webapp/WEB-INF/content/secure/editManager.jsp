<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
 

	<!-- edit manager -->
	<div id="dEditManager">
		
		<p class="validateTips"></p>
		
		<spring:url var="saveURL" value="/managers/save.do" /> 
	
		<form:form commandName="manager" id="editManager" action="${saveURL}" enctype="multipart/form-data" method="POST" style="width: 450px;">
			<form:hidden path="id"/>
			<form:hidden path="image" />		
			<input type="hidden" id="team_id" name="team.id" value="${team.id}" />			
			<input type="hidden" id="team_nation_id" name="team.nation.id" value="${team.nation.id}" />
			<input type="hidden" id="team_division_id" name="team.division.id" value="${team.division.id}" />		

			<input type="hidden" id="endCareer" name="endCareer" value="${endCareer}" />
			<input type="hidden" id="withoutTeam" name="withoutTeam" value="${withoutTeam}" />						
			
			<table style="width: 650px;">
				
				<tr>
					<td style="text-align: center">
						<spring:url var="imageURL" value="/managers/image.do" />
						<input type="hidden" value="${imageURL}" name="urlImage" />
						<img src="" id="myImage" style="width: 58px; padding: 0px; border-color: #bbb; border-width: 1px; border-style: solid; background-color: rgb(255, 255, 255);" />
					</td>
					<td>				  
					  <div class="fitem_label"><spring:message code="photo" />:</div>
					  <div class="fitem_span"><form:input path="image" type="file" /></div>
					</td>
					<td>

					</td>					
					<td>

					</td>					
				</tr>					

				<tr>
					<td>
					  <div class="fitem_label"><spring:message code="firstName" />:</div>
					  <div class="fitem_span">
							<form:input path="firstName" />
							<form:errors path="firstName" />
					  </div>						
					</td>
					<td>				  
						<div class="fitem_label"><spring:message code="lastName" />:</div>
					  <div class="fitem_span">
							<form:input path="lastName" />
							<form:errors path="lastName" />
					  </div>
					</td>
					<td colspan="2">
					  <div class="fitem_label">Nome completo:</div>
					  <div class="fitem_span">
							<form:input path="completeName" style="width: 200px" />
							<form:errors path="completeName" />
					  </div>						
					</td>
				</tr>					

				<tr>
					<td>				  
					  <div class="fitem_label"><spring:message code="birthDate" />:</div>
					  <div class="fitem_span">
							<fmt:formatDate var="f_BirthDate" value="${manager.birthDate}" type="both" pattern="dd/MM/yyyy" />	
							<form:input path="birthDate" value="${f_BirthDate}" style="width: 80px" maxlength="10" onkeyup="formatDate(this, event)" />
							<form:errors path="birthDate" />
					  </div>
					</td>
					<td>
					  <div class="fitem_label"><spring:message code="birthPlace" />:</div>
					  <div class="fitem_span">
							<form:input path="birthPlace" />
							<form:errors path="birthPlace" />
					  </div>
					</td>
					<td>

					</td>
				</tr>					

				<tr>
					<td>
					  <div class="fitem_label">Nazionalita 1:</div>
					  <div class="fitem_span">
						  <form:select path="nation.id" id="nation_id">
						      <form:option  value="" />
						      <form:options items="${nationList}" itemValue="id" itemLabel="name"/>
							</form:select>
					  </div>
					</td>
					<td>				  

					</td>
					<td>

					</td>
				</tr>										
												
				<tr>
					<td>				  
					  <div class="fitem_label">Scadenza contratto:</div>
					  <div class="fitem_span">
							<form:input path="dateContract" style="width: 80px" maxlength="10" onkeyup="formatDate(this, event)" />
							<form:errors path="dateContract" />
					  </div>
					</td>
					<td>
					  <div class="fitem_label">Ingaggio:</div>
					  <div class="fitem_span">
							<form:input path="income" style="width: 80px" onkeyup="formatCurrency(jQ(this))" />
							<form:errors path="income" />
					  </div>
					</td>
					<td>
					  <div class="fitem_label">Valore:</div>
					  <div class="fitem_span">
							<form:input path="value" style="width: 80px" onkeyup="formatCurrency(jQ(this))"  />
							<form:errors path="value" />
					  </div>
					</td>
				</tr>		
				
			</div>					


			</table>	
		</form:form>
	</div>
	<!-- fine div -->