<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> 
<%@taglib uri="/struts-tags" prefix="s"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> 
<html xmlns="http://www.w3.org/1999/xhtml"> 
<head> 
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" /> 
<title><s:text name="title"/> <s:property value="firstName" /> <s:property value="lastName" /> - a cura di Football.it</title> 
<%-- CSS --%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/style.css" /> 
</head> 
<body> 
<s:push value="player"> 
<div id="header"> 
<div id="menutop"> 
<ul> 
<li> 
<s:url id="listPlayerURL" action="searchPlayer"> 
<s:param name="teamId" value="%{teamId}"></s:param> 
</s:url> 
<s:a href="%{listPlayerURL}"><s:text name="searchPlayer"/></s:a> 
</li> 
<li> 
<s:url id="listPlayerURL" action="listByTeamPlayer"> 
<s:param name="teamId" value="%{teamId}"></s:param> 
</s:url> 
<s:a href="%{listPlayerURL}">Cerca nelle squadra</s:a> 
</li> 
</ul> 
</div> 
</div> 
<div id="menubline"> 
</div> 
<div id="page"> 
<!-- 
<div id="sidebar">
<ul></ul>
</div>
-->
<div id="content"> 
<div class="scheda giocatore"> 
<h1><s:text name="title2"/> <s:property value="firstName" /> <s:property value="lastName" /></h1> 
<table style="width: 500px;"> 
<tr> 
<td style="text-align: left;"><s:text name="birthDate"/></td> 
<td style="text-align: left;"><s:date name="birthDate" format="dd/MM/yyyy" /></td> 
<td rowspan=8> 
<img src="<s:url action="getDynamicImagePlayer"> 
<s:param name="id" value="%{id}"></s:param> </s:url>"> 
</td> 
</tr> 
<tr class="bg2"> 
<td style="text-align: left;"><s:text name="birthPlace"/></td> 
<td style="text-align: left;"><s:property value="birthPlace" /></td> 
</tr> 
<tr> 
<td style="text-align: left;"><s:text name="country"/></td> 
<td style="text-align: left;"><s:property value="nation.name" /></td> 
</tr> 
<tr class="bg2"> 
<td style="text-align: left;"><s:text name="height"/></td> 
<td style="text-align: left;"><s:property value="height" /></td> 
</tr> 
<tr> 
<td style="text-align: left;"><s:text name="weight"/></td> 
<td style="text-align: left;"><s:property value="weight" /></td> 
</tr> 
<tr class="bg2"> 
<td style="text-align: left;"><s:text name="position"/></td> 
<td style="text-align: left;"><s:property value="position.name" /></td> 
</tr> 
<tr> 
<td style="text-align: left;"><s:text name="officialSite"/></td> 
<td style="text-align: left;"></td> 
</tr> 
<tr class="bg2"> 
<td style="text-align: left;"><s:text name="nation"/></td> 
<td style="text-align: left;"><img src="${pageContext.request.contextPath}/images/flags/${nation.id}.png" alt="${nation.name}" /></td> 
</tr> 
</table> 
</div> 
<div class="scheda analitica"> 
<h1><s:text name="changeTeam"/></h1> 
</div> 
<s:form action="changeTeamPlayer" cssClass="cmxform" id="commentForm" enctype="multipart/form-data" method="POST"> 
<s:push value="player"> 
<s:hidden name="id" /> 
<table style="width: 350px;"> 
<s:select name="teamId" list="teamList" listKey="id" listValue="name" key="team" /> 
<s:submit align="center" cssClass="button_accept button_out" cssStyle="width: 110px;" 
onmouseout="project.toggleHighlight(this, 'button');" 
onmouseover="project.toggleHighlight(this, 'button');" /> 
</table> 
</s:push> 
</s:form> 
</div> 
</div> 
</s:push> 
</body> 
</html> 