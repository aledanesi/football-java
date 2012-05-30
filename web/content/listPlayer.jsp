<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> 

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%> 
<%@taglib uri="/struts-tags" prefix="s"%> 
<%@taglib uri="http://displaytag.sf.net" prefix="display" %> 

<html> 
<head> 
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"> 
<title>### listPlayer.jsp ###</title> 

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
<s:url id="returnListURL" action="searchTeam"> 
<s:param name="teamId" value=""></s:param> 
</s:url> 
<s:a href="%{returnListURL}"><s:text name="returnList"/></s:a> 
</li> 
<li> 
<s:url id="insertPlayerURL" action="insertPlayer"> 
<s:param name="teamId" value="%{teamId}"></s:param> 
</s:url> 
<s:a href="%{insertPlayerURL}"><s:text name="insertPlayer"/></s:a> 
</li> 
</ul> 
</div> 
</div> 
<table bordercolor="#111111" style="margin-top: 10pt; margin-bottom: 10pt; border-collapse: collapse; width: 400px"> 
<tbody> 
<tr> 
<td valign="middle" style="margin: 15pt; vertical-align: top"> 
<p align="center" style="margin: 10px; font-weight: bold; font-size: 20px">${team.name}</p> 
<br>
<p align="center" style="margin: 10px; font-weight: bold; font-size: 20px">
<img src="<s:url action="getDynamicImageTeam"> 
<s:param name="id" value="%{teamId}"></s:param> </s:url>"> 
</p>
</td> 
<td valign="middle" style="margin: 15pt;"> 
 <s:if test="playerList.size() > 0"> 
<div class="content"> 
<table class="listTable" cellpadding="5px" style="width: 600px"> 
<tr class="title"> 
<th></th> 
<th style="text-align: left"><s:text name="footballer"/></th> 
<th><s:text name="birthDate"/></th> 
<th><s:text name="appearances2"/></th> 
<th><s:text name="goalScored"/></th> 
<th><s:text name="position"/></th> 
<th></th> 
<th></th> 
<th></th> 
</tr> 
<s:iterator value="playerList" status="playerStatus"> 

<%-- 
_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=

COLORE DELLA COLONNA DIVERSO PER RUOLO

_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=

--%>

<s:if test="position.id == 1"> 
<tr style="background-color: #00FF99"> 
</s:if> 
<s:elseif test="position.id == 2"> 
<tr style="background-color: #33CCFF"> 
</s:elseif> 
<s:elseif test="position.id == 3"> 
<tr style="background-color: #CCFF66"> 
</s:elseif> 
<s:else> 
<tr style="background-color: #FF9966"> 
</s:else> 
<td><img src="${pageContext.request.contextPath}/images/flags/${nation.id}.png" alt="${nation.name}" /> </td> 
<td style="white-space: nowrap;text-align: left"><s:property value="lastName" /> 
<s:property value="firstName" /> 
</td> 
<td style="text-align: center"><s:date name="birthDate" format="dd/MM/yyyy" /></td> 
<td style="text-align: center"><s:property value="appearances" /></td> 
<td style="text-align: center"><s:property value="goalScored" /></td> 
<td style="text-align: center"><s:property value="position.value" /></td> 
<td style="text-align: center"> 
<s:url id="editURL" action="editPlayer"> 
<s:param name="id" value="%{id}"></s:param> 
</s:url> 
<s:a href="%{editURL}"><img src="${pageContext.request.contextPath}/images/edit.png" alt="<s:text name="edit"/>" /> 
</s:a> 
</td> 
<td style="text-align: center"> 
<s:url id="viewURL" action="viewPlayer"> 
<s:param name="id" value="%{id}"></s:param> 
</s:url> 
<s:a href="%{viewURL}"><img src="${pageContext.request.contextPath}/images/view.png" alt="<s:text name="view"/>" /> 
</s:a> 
</td> 
<td> 
<s:url id="deleteURL" action="deletePlayer"> 
<s:param name="id" value="%{id}"></s:param> 
</s:url> 
<s:a href="%{deleteURL}" onclick="return project.confirmDeletePlayer();"> 
<img src="${pageContext.request.contextPath}/images/delete.png" alt="<s:text name="delete"/>" /> 
</s:a> 
</td> 
</tr> 
</s:iterator> 
</table> 
</div> 
</s:if> 
</td> 
</tr> 
</tbody> 
</table> 


</body> 
</html> 