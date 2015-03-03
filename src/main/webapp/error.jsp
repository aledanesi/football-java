<?xml version="1.0" encoding="ISO-8859-1" ?> 

<%@page isErrorPage="true" %> 

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%> 

<link type="text/css" href="/JFootball/scripts/jquery-ui-1.11.3/jquery-ui.css" rel="stylesheet" />
<script type="text/javascript" src="/JFootball/scripts/jquery-1.11.2/jquery.js"></script>
<script type="text/javascript" src="/JFootball/scripts/jquery-1.11.2/jquery.validate.js"></script>
<script type="text/javascript" src="/JFootball/scripts/jquery-ui-1.11.3/jquery-ui.js"></script>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> 

<html> 
	<head> 
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
		<title>error.jsp</title> 
	</head> 
	<body> 
		<p style="color: #f00; font-weight: bold">
				<span style="font-size: 26px; margin-right: 10px">Application Error</span>
				<i class="fa fa-warning fa-3x"></i>
		</p> 
		<p style="font-size: 14px; color: #000">
				The application encountered a problem and your request was not completed successfully. <br />
				Please relay the information listed below to our technical staff.
		</p> 

		<p style="color: #000;">
			<span style="font-size: 14px; font-weight: bold">Tecnical staff contact info:<br/></span>
			<span style="font-size: 14px;">Phone#: 349-4933807<br/></span>
			<span style="font-size: 14px;">Email#: danesiale@gmail.com</span>
		</p> 

		<p style="font-size: 14px; color: #000">
			<span style="font-size: 14px; font-weight: bold;">Error message</span>: <%= exception.toString() %>
		</p> 
		<p style="font-size: 14px; color: #000">
			<span style="font-size: 14px; font-weight: bold;">Exception stack trace: <button id="toggle">Hide/Show</button></span>
		</p>   

	  <p id="stackTrace" style="font-size: 10px; color: #000; display: none">  
    
	    <c:forEach var="trace" items="${pageContext.exception.stackTrace}">        
	    	${trace}<br/>    
	    </c:forEach>  
			
		</p> 
		<p><a href="/JFootball/loading.jsp">Return Home</a>
			
<script>
	jQ(document).ready(function(){
	    jQ("#toggle").click(function(){
	        jQ("#stackTrace").toggle();
	    });
	});
</script>			 
	</body> 
</html>