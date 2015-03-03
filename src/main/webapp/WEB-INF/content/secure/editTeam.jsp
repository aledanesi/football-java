<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

	<!-- edit team -->
	<div id="dEditTeam" style="display: none">
		
		<p class="validateTips"></p>

		<spring:url var="saveURL" value="/teams/save.do" /> 
	
		<form:form commandName="team" id="editTeam" action="${saveURL}" enctype="multipart/form-data" method="POST">
			<form:hidden path="id" />
			<form:hidden path="image" />
			<form:hidden path="nation.id" />
			
			<table class="standard_tabelle" style="border: 0px">
				
				<tr>
					<td style="text-align: center">
						<spring:url var="imageURL" value="/teams/image.do" />
						<input type="hidden" value="${imageURL}" name="urlImage" />
						<img src="" id="myImage" style="width: 58px; padding: 0px; border-color: #bbb; border-width: 1px; border-style: solid; background-color: rgb(255, 255, 255);" />
					</td>
					<td>				  
					  <div class="fitem_label"><spring:message code="editTeam.message1" />:</div>
					  <div class="fitem_span"><form:input path="image" type="file" /></div>
					</td>			
				</tr>
				<tr>
					<td>
					  <div class="foitem_label"><spring:message code="editTeam.message2" />:</div>
					  <div class="fitem_span">
							<form:input path="name" />
							<form:errors path="name" />			  	
					  </div>					
					</td>
					<td>				  
					  <div class="fitem_label"><spring:message code="editTeam.message3" />:</div>
					  <div class="fitem_span">
							<form:input path="founded" />
							<form:errors path="founded" />			  	
					  </div>					  
					</td>			
				</tr>
				<tr>
					<td>
					  <div class="fitem_label"><spring:message code="editTeam.message4" />:</div>
					  <div class="fitem_span">
							<form:input path="city" />
							<form:errors path="city" />			  	
					  </div>					
					</td>
					<td>				  
					  <div class="fitem_label"><spring:message code="editTeam.message5" />:</div>
					  <div class="fitem_span">
							<form:input path="address" />
							<form:errors path="address" />			  	
					  </div>					  
					</td>			
				</tr>
				<tr>
					<td>
					  <div class="fitem_label"><spring:message code="editTeam.message6" />:</div>
					  <div class="fitem_span">
							<form:input path="webSite" />
							<form:errors path="webSite" />			  	
					  </div>					
					</td>
					<td>				  
					  <div class="fitem_label"><spring:message code="editTeam.message7" />:</div>
					  <div class="fitem_span">
							<form:input path="email" />
							<form:errors path="email" />			  	
					  </div>					  
					</td>			
				</tr>	
				<tr>
					<td>
					  <div class="fitem_label"><spring:message code="editTeam.message8" />:</div>
					  <div class="fitem_span">
							<form:input path="stadium" />
							<form:errors path="stadium" />			  	
					  </div>					
					</td>
					<td>				  
					  <div class="fitem_label"><spring:message code="editTeam.message9" />:</div>
					  <div class="fitem_span">
							<form:input path="posti" />
							<form:errors path="posti" />			  	
					  </div>					  
					</td>			
				</tr>
				<tr>
					<td>
					  <div class="fitem_label"><spring:message code="editTeam.message10" />:</div>
					  <div class="fitem_span">
							<form:input path="colorTeam" />
							<form:errors path="colorTeam" />			  	
					  </div>					
					</td>
					<td>				  
					  <div class="fitem_label"><spring:message code="editTeam.message11" />:</div>
					  <div class="fitem_span">
							<form:select path="division.id" id="division_id">
							  <form:options items="${divisionList}" itemLabel="name" itemValue="id" />
								<form:option value="-1" label="DILETTANTI"/>							
							</form:select>							
							<form:errors path="division.id" />		  	
					  </div>					  
					</td>			
				</tr>				
			</table>	
			
		</form:form>
	</div>
	
	<!-- fine div -->