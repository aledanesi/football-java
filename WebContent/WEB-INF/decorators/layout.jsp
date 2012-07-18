<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>

<%@page contentType="text/html; charset=UTF-8" %>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
    <title><decorator:title default="Personal budget management system"/></title>
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <link rel="stylesheet" type="text/css" href="../skins/skin.css"/>
    <decorator:head/>
</head>

<body>
	
<div id="wrap">

<!--header starts here-->
<%@ include file="/content/includes/header.jsp"%>

<!-- content-wrap starts here -->
<div id="content-wrap">
	
<decorator:body/>

</div>

<!--footer starts here-->
<%@ include file="/content/includes/footer.jsp"%>

<!-- wrap ends here --></div>

</body>
</html>