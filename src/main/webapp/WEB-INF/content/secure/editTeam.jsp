<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

	<!-- edit team -->
	<div id="dEditTeam">
		
		<p class="validateTips"></p>

		<spring:url var="saveURL" value="/teams/save.do" /> 
	
		<form:form commandName="team" id="editTeam" action="${saveURL}" enctype="multipart/form-data" method="POST" style="width: 450px;">
			<form:hidden path="id" />
			<form:hidden path="image" />
			
			<div class="fitem" style="width: 500px; margin-bottom: 100px">
				<div id="left" style="width: 250px; text-align: center">
					<spring:url var="imageURL" value="/teams/image.do" />
					<input type="hidden" value="${imageURL}" name="urlImage" />
					<img src="" id="myImage" style="width: 58px; padding: 0px; margin: 0px; border-color: #bbb; border-width: 1px; border-style: solid; background-color: rgb(255, 255, 255);" />
				</div>	
				<div id="right" style="width: 250px">
				  <div class="fitem_label"><spring:message code="photo" />:</div>
				  <div class="fitem_span"><form:input path="image" type="file" /></div>
			  </div>
			</div>			

			<div class="fitem" style="width: 500px">
				<div id="left" style="width: 250px">
				  <div class="fitem_label"><spring:message code="name" />:</div>
				  <div class="fitem_span">
						<form:input path="name" />
						<form:errors path="name" />			  	
				  </div>
				</div>	
				<div id="right" style="width: 250px">
				  <div class="fitem_label"><spring:message code="founded" />:</div>
				  <div class="fitem_span">
						<form:input path="founded" />
						<form:errors path="founded" />			  	
				  </div>
			  </div>
			</div>			

			<div class="fitem" style="width: 500px">
				<div id="left" style="width: 250px">
				  <div class="fitem_label"><spring:message code="city" />:</div>
				  <div class="fitem_span">
						<form:input path="city" />
						<form:errors path="city" />			  	
				  </div>
				</div>	
				<div id="right" style="width: 250px">
				  <div class="fitem_label"><spring:message code="address" />:</div>
				  <div class="fitem_span">
						<form:input path="address" />
						<form:errors path="address" />			  	
				  </div>
			  </div>
			</div>			

			<div class="fitem" style="width: 500px">
				<div id="right" style="width: 250px">
				  <div class="fitem_label"><spring:message code="webSite" />:</div>
				  <div class="fitem_span">
						<form:input path="webSite" />
						<form:errors path="webSite" />			  	
				  </div>
				</div>
				<div id="left" style="width: 250px">
				  <div class="fitem_label"><spring:message code="email" />:</div>
				  <div class="fitem_span">
						<form:input path="email" />
						<form:errors path="email" />			  	
				  </div>
				</div>	
			</div>			

			<div class="fitem" style="width: 500px">
				<div id="left" style="width: 250px">
				  <div class="fitem_label"><spring:message code="stadium" />:</div>
				  <div class="fitem_span">
						<form:input path="stadium" />
						<form:errors path="stadium" />			  	
				  </div>
				</div>	
				<div id="right" style="width: 250px">
				  <div class="fitem_label"><spring:message code="posti" />:</div>
				  <div class="fitem_span">
						<form:input path="posti" size="6" maxlength="6" />
						<form:errors path="posti" />			  	
				  </div>
			  </div>
			</div>			

			<div class="fitem" style="width: 500px">
				<div id="left" style="width: 250px">
				  <div class="fitem_label"><spring:message code="colorTeam" />:</div>
				  <div class="fitem_span">
						<form:input path="colorTeam" />
						<form:errors path="colorTeam" />			  	
				  </div>
				</div>	
				<div id="right" style="width: 250px">
				  <div class="fitem_label"><spring:message code="division" />:</div>
					<div class="fitem">
						<form:select path="division.id">
						  <form:options items="${divisionList}" itemLabel="name" itemValue="id" />
							<form:option value="-1" label="DILETTANTI"/>							
						</form:select>							
						<form:errors path="division.id" />
					</div>
			  </div>
			</div>			

			<form:hidden path="nation.id" />
			
		</form:form>
	</div>
	<!-- fine div -->