<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>### listTeam.jsp ###</title>

<s:head />

<link rel="stylesheet" href="styles/Envision.css" type="text/css" />


<%-- JQUERY --%>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery-1.3.2.min.js"></script>
		
<%-- APPLICATION SPECIFIC --%>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/script.js"></script>	

</head>
<body>
	
<div id="wrap"><!--header -->
<div id="header">

<h1 id="logo-text">Football Java <sup style="font: Verdana, Tahoma, arial, sans-serif;"><small style="font-size: 20px">beta</small></sup></h1>
<!-- <h2 id="slogan">put your site slogan here...</h2> -->

<s:url id="logoutURL" value="j_spring_security_logout" />

<div id="header-links">
<p><a href="index.jsp">Home</a> | <a href="index.html">Contact</a> | <a href="index.html">Site Map</a> | <s:a href="%{logoutURL}">Logout</s:a></p>
</div>

</div>

<!-- content-wrap starts here -->
<div id="content-wrap">
	
<s:actionerror/>
<s:form action="saveOrUpdateTeam" enctype="multipart/form-data" method="POST" style="width: 300px">

<s:push value="team">								
<s:hidden name="id" />

	<div id="header2">
	    <div id="menutop">
	        <ul>                                 
	            <li>
					<s:url id="listURL" action="listTeam" />
					<s:a href="%{listURL}">Torna alla lista</s:a>
	            </li>
	            <li style="width: 80px; text-align: center;">
					<input type="submit" value="Salva"  />
	            </li>
	         </ul>               
	    </div>
	</div>

	<div align="center">	

		<tr>
		     <td colspan="2" style="margin: 15pt;">
		      	<p align="center" style="margin: 10px; font-weight: bold; font-size: 20px">${team.name}</p>
		     </td>
		</tr>			
			
		<tr>
			<s:if test="%{image == null}">
	    		<td colspan="2" style="text-align: center"><img src="${pageContext.request.contextPath}/images/players/notFound.jpg" alt="Foto di ${lastName}" /></td>
			</s:if>
			<s:if test="%{image != null}">
				<td colspan="2" style="text-align: center">
			       <img src="<s:url action="getDynamicImageTeam">  
						<s:param name="id" value="%{id}"></s:param>  
						</s:url>"> 
				</td>
			</s:if>	
		</tr>			
						
		<s:file name="userImage" label="Foto" key="photo" />
		<s:textfield name="name" key="name" />
		<s:select name="division.id" list="divisionList" listKey="id" listValue="name" key="division" />
		<s:textfield name="city" key="city" />
		<s:textfield name="colorTeam" key="colorTeam" />
		<s:textfield name="email" key="email" />
		<s:textfield name="address" key="address" />
		<s:textfield name="stadium" key="stadium" />
		<s:textfield name="phone" key="phone" />
		<s:textfield name="posti" key="posti" size="6" maxlength="6" />		
		<s:select name="nation.id" list="nationList" listKey="id" listValue="name" key="nation" />								    				
	</div>
		
</s:push>
	
</s:form>

</div>

<!--footer starts here-->
<div id="footer">

<p>&copy; 2012 <strong>Football Java</strong> | Design by: <a href="#">aledanesi</a></p>

</div>

<!-- wrap ends here --></div>

</body>
</html>