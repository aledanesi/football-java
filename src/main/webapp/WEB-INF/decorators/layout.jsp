<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
<head>
    
    <title><decorator:title default="JCampionato - Beta"/></title>
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    
    <%-- CSS --%>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/mystyle.css" rel="stylesheet" />
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/font-awesome-4.3.0/css/font-awesome.min.css" rel="stylesheet" />

    <%-- JQUERY --%>
    <link type="text/css" href="${pageContext.request.contextPath}/scripts/jquery-ui-1.11.3/jquery-ui.css" rel="stylesheet" />
    <script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery-1.11.2/jquery.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery-1.11.2/jquery.validate.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery-ui-1.11.3/jquery-ui.js"></script>
	
    <%-- APPLICATION SPECIFIC --%>
    <script type="text/javascript" src="${pageContext.request.contextPath}/scripts/script.js"></script>
    
    <decorator:head/>
</head>

<body>
  
<div id="wrap">

<!--header starts here-->
<%@ include file="/WEB-INF/content/includes/header.jsp"%>

<!-- content-wrap starts here -->
<div id="content-wrap">
  
<decorator:body/>

</div>

<!--footer starts here-->
<%@ include file="/WEB-INF/content/includes/footer.jsp"%>

<!-- wrap ends here --></div>

</body>
</html>