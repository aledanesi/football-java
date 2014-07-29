<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>

<%@page contentType="text/html; charset=UTF-8" %>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
    <title><decorator:title default="JCampionato - Beta"/></title>
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    
    <%-- CSS --%>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/mystyle.css" rel="stylesheet" />


    <%-- JQUERY --%>
    <link type="text/css" href="${pageContext.request.contextPath}/scripts/jquery/themes/base/jquery.ui.all.css" rel="stylesheet">
    <script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery/jquery-1.10.2.js"></script>
	  <script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery/ui/jquery-ui.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery/jquery.validate.js"></script>

	
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