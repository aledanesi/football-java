<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

	<!-- edit user -->
	<div id="dEditUser" style="display: none">
		
		<p class="validateTips"></p>

		<spring:url var="saveURL" value="/users/save.do" /> 
	
		<form:form commandName="user" id="editUser" action="${saveURL}" enctype="multipart/form-data" method="POST" style="width: 450px;">
			<form:hidden path="id" />		

			<div class="fitem" style="width: 500px">
				<div id="left" style="width: 250px">
				  <div class="fitem_label">Nome:</div>
				  <div class="fitem_span">
						<form:input path="username" />
						<form:errors path="username" />			  	
				  </div>
				</div>	
				<div id="right" style="width: 250px">
				  <div class="fitem_label">Cognome:</div>
				  <div class="fitem_span">
						<form:input path="username" />
						<form:errors path="username" />			  	
				  </div>
			  </div>
			</div>			

			<div class="fitem" style="width: 500px">
				<div id="left" style="width: 250px">
				  <div class="fitem_label">Username:</div>
				  <div class="fitem_span">
						<form:input path="username" />
						<form:errors path="username" />			  	
				  </div>
				</div>	
				<div id="right" style="width: 250px">
				  <div class="fitem_label">Password:</div>
				  <div class="fitem_span">
						<form:input path="username" />
						<form:errors path="username" />			  	
				  </div>
			  </div>
			</div>			

			<div class="fitem" style="width: 500px">
				<div id="right" style="width: 250px">
				  <div class="fitem_label">Email:</div>
				  <div class="fitem_span">
						<form:input path="username" />
						<form:errors path="username" />			  	
				  </div>
				</div>
				<div id="left" style="width: 250px">
				  <div class="fitem_label">Squadra:</div>
				  <div class="fitem_span">
						<form:input path="username" />
						<form:errors path="username" />			  	
				  </div>
				</div>	
			</div>		
			

		</form:form>
	</div>
	<!-- fine div -->