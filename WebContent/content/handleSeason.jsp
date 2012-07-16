<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<head> 
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Gestione Campionati</title> 
	<link rel="stylesheet" href="styles/Envision.css" type="text/css" />
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/styles/multiselect/common.css" />
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/styles/jquery/jquery-ui.css" />
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/styles/multiselect/ui.multiselect.css" />
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery/jquery-1.7.2.js"></script>
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery/ui/jquery-ui.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/multiselect/plugins/localisation/jquery.localisation-min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/multiselect/plugins/scrollTo/jquery.scrollTo-min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/multiselect/ui.multiselect.js"></script>
	<script type="text/javascript">
		var $ = jQuery.noConflict();
		
		$(function(){
			$.localise('ui-multiselect', {/*language: 'en',*/ path: 'js/locale/'});
			$(".multiselect").multiselect();
			//$('#switcher').themeswitcher();
		});
		
		function changeSelect()
		{
			var seasonId = $("#seasonYearId").val();
			var divisionId = $("#divisionId").val();
			
			window.location.href = "handleSeason.action?seasonYear.id=" + seasonId + "&division.id=" + divisionId; 
		}
	</script>
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
	
	<div align="center"> 	
	    <s:form action="saveSeason" style="width: 200px">
		    <s:push value="season">
		
		      <s:select name="seasonYear.id" id="seasonYearId" list="yearList" listKey="id" listValue="value" onchange="changeSelect()"  />
		      <s:select name="nation.id" id="nationId" list="nationList" listKey="id" listValue="name" value="11"  />
		      <s:select name="division.id" id="divisionId" list="divisionList" listKey="id" listValue="name" onchange="changeSelect()"  />
		
		      <s:select name="selTeams" id="teamsId" list="teamList" listKey="id" listValue="name" cssClass="multiselect" multiple="true" value="%{arr}" /> 
		
		      <s:submit value="Submit Form" />
		
		    </s:push>  
	    </s:form>	
	</div>

</div>

<!--footer starts here-->
<div id="footer">

<p>&copy; 2012 <strong>Football Java</strong> | Design by: <a href="#">aledanesi</a></p>

</div>

<!-- wrap ends here --></div>

</body>

</html>
