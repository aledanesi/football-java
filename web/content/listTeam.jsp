<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> 
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%> 
<%@taglib uri="/struts-tags" prefix="s"%> 
<%@taglib uri="http://displaytag.sf.net" prefix="display" %> 
<html> 
<head> 
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"> 
<title>### listTeam.jsp ###</title> 
<s:head /> 
<%--
<link rel="stylesheet" href="/progetto/struts/xhtml/styles.css" type="text/css"/>
<script src="/progetto/struts/utils.js" type="text/javascript"></script>
--%>
<%-- CSS --%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/style.css" /> 
<%-- JQUERY --%>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery-1.3.2.min.js"></script> 
<%-- APPLICATION SPECIFIC --%>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/script.js"></script> 
</head>
<body>
<div id="header">
<div id="menutop">
<ul>
<li>
<s:url id="insertTeamURL" action="insertTeam">
<s:param name="division.id" value="%{division.id}"></s:param>
</s:url>
<s:a href="%{insertTeamURL}"><s:text name="insertTeam"/></s:a>
</li>
<li>
<s:url id="searchPlayerURL" action="searchPlayer">
<s:param name="teamId" value="%{teamId}"></s:param>
</s:url> 
<s:a href="%{searchPlayerURL}"><s:text name="searchPlayer"/></s:a>
</li>
</ul>
</div>
</div> 
<div align="center"> 
<s:form action="searchTeam" style="width: 200px">
<input type="hidden" name="divisionId" value="${division.id}" /> 
<s:push value="team"> 
<s:select name="division.id" list="divisionList" listKey="id" listValue="name" key="division" />
<s:token /> 
<s:submit value="Search" />
</s:push>
</s:form>
</div>
<br /><br />
<div align="center"> 
<display:table name="${teamList}" id="row" pagesize="22" requestURI="" style="width: 600px" >
<display:column property="name" title="Squadra" />
<display:column property="stadium" title="Stadio" />
<display:column property="posti" title="Posti" />
<display:column style="width: 5%">
<s:url id="editURL" action="editTeam">
<s:param name="id">
${row.id}
</s:param>
</s:url> 
<s:a href="%{editURL}">
<img src="${pageContext.request.contextPath}/images/edit.png" alt="<s:text name="edit"/>" />
</s:a> 
</display:column>
<display:column style="width: 5%">
<s:url id="viewURL" action="listByTeamPlayer">
<s:param name="teamId">
${row.id}
</s:param> 
<s:param name="division.id">${divisionId}</s:param> 
</s:url> 
<s:a href="%{viewURL}">
<img src="${pageContext.request.contextPath}/images/view.png" alt="<s:text name="view"/>" />
</s:a> 
</display:column> 
<display:column style="width: 5%">
<s:url id="deleteURL" action="deleteTeam">
<s:param name="id">
${row.id}
</s:param>
<s:param name="division.id">${divisionId}</s:param>
</s:url> 
<s:a href="%{deleteURL}" onclick="return project.confirmDeleteTeam();">
<img src="${pageContext.request.contextPath}/images/delete.png" alt="<s:text name="delete"/>" />
</s:a> 
</display:column> 
</display:table> 
</div> 
</body>
</html>