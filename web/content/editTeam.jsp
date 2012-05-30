<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> 
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%> 
<%@taglib uri="/struts-tags" prefix="s"%> 
<html> 
<head> 
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"> 
<title>### editTeam.jsp ###</title> 
<s:head /> 
<%--
<link rel="stylesheet" href="/progetto/struts/xhtml/styles.css" type="text/css"/>
<script src="/progetto/struts/utils.js" type="text/javascript"></script>
--%>
<%-- CSS --%>
<link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/styles/style.css" /> 
</head> 
<body> 
<s:actionerror/> 
<s:form action="saveOrUpdateTeam" enctype="multipart/form-data" method="POST" style="width: 300px"> 
<s:push value="team"> <s:hidden name="id" /> 
<div id="header"> 
<div id="menutop"> 
<ul> 
<li> 
<s:url id="listURL" action="listTeam" /> 
<s:a href="%{listURL}">Torna alla lista</s:a> 
</li> 
<li style="width: 80px; text-align: center;"> 
<input type="submit" value="Salva" /> 
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
<s:param name="id" value="%{id}"></s:param> </s:url>"> 
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
</body> 
</html> 