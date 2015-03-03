<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

	<!-- edit team -->
	<div id="dEditTeam" style="display: none">
		
		<p class="validateTips"></p>

		<spring:url var="saveURL" value="/divisions/save.do" /> 
	
		<form:form commandName="division" id="editDivision" action="${saveURL}" enctype="multipart/form-data" method="POST" style="width: 450px;">
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
				  <div class="fitem_label">Level:</div>
				  <div class="fitem_span">
						<form:input path="level" />
						<form:errors path="level" />			  	
				  </div>
				</div>	
				<div id="right" style="width: 250px">
				  <div class="fitem_label">Value:</div>
				  <div class="fitem_span">
						<form:input path="value" />
						<form:errors path="value" />			  	
				  </div>
			  </div>
			</div>			

			<div class="fitem" style="width: 500px">
				<div id="left" style="width: 250px">
				  <div class="fitem_label">Name:</div>
				  <div class="fitem_span">
						<form:input path="name" />
						<form:errors path="name" />			  	
				  </div>
				</div>	
				<div id="right" style="width: 250px">
				  <div class="fitem_label"><spring:message code="nation" />:</div>
					<div class="fitem">
						<form:select path="nationId" items="${nationList}" itemLabel="name" itemValue="id" />
						<form:errors path="nationId" />
					</div>
			  </div>
			</div>			
			
		</form:form>
	</div>
	<!-- fine div -->