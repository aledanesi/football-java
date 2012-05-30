<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> 
<%@taglib uri="/struts-tags" prefix="s"%> 
<%@taglib uri="/struts-dojo-tags" prefix="sx" %> 
<html> 
<head> 
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"> 
<title>insertCareer.jsp</title> 
<%-- CSS --%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/style.css" /> 
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/calendar/win2k_ie.css" /> 
<%-- JQUERY --%>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery-1.3.2.min.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/script.js"></script> 
<script language="Javascript"> 
var jQ = jQuery.noConflict(); 
jQ(document).ready(function() 
{
jQ('#firstName').focus(); }
); 
</script> 
</head> 
<body> 
<div align="center"> 
<center> 
<table cellspacing="0" cellpadding="0" bordercolor="#111111" border="0" width="600" style="word-spacing: 0pt; margin-top: 10pt; margin-bottom: 10pt; border-collapse: collapse;"> 
<tbody><tr> 
<td valign="middle" style="margin: 15px;"> 
<hr> 
<p align="center" style="margin: 10px;" class="zona"> 
Inserisci anno di carriera
</p></td> 
</tr> 
<tr> 
<td valign="middle" style="margin: 15px;"> 
<hr> 
</td> 
</tr> 
</tbody> 
</table> 
<s:form action="saveCareer"> 
<s:hidden name="player.id" /> 
<s:push value="career"> 
<table cellpadding="5px" style="width: 350px; text-align: left"> 
<tr class="odd"> 
<td> 
<s:hidden name="id" /> 
<s:textfield name="stagione" id="stagione" key="stagione" labelposition="left" /> 
<s:select name="team.id" list="teamList" listKey="id" listValue="name" key="squadra" /> 
<s:textfield name="serie" id="serie" key="serie" /> 
<s:textfield name="presenze.partiteGiocate" id="presenze.partiteGiocate" key="playedGames" /> 
<s:textfield name="presenze.partiteTotali" id="presenze.partiteTotali" key="totalGames" /> 
<s:textfield name="goal" id="goal" key="goal" /> 
<s:textfield name="presenze.minutiGiocati" id="presenze.minutiGiocati" key="playedMinutes" /> 
<s:textfield name="presenze.minutiTotali" id="presenze.minutiTotali" key="totalMinutes" /> 
<s:textfield name="sostituzioni.fatte" id="sostituzioni.fatte" key="sostitutionMade" /> 
<s:textfield name="sostituzioni.avute" id="sostituzioni.avute" key="sostitutionReceived" /> 
<s:submit cssClass="button_accept button_out" cssStyle="width: 110px" 
onmouseout="project.toggleHighlight(this, 'button');" 
onmouseover="project.toggleHighlight(this, 'button');" /> 
</td> 
</tr> 
</table> 
</s:push> 
<br /> 
<s:url id="listURL" action="viewPlayer"> 
<s:param name="id" value="%{player.id}"></s:param> 
</s:url> 
<s:a href="%{listURL}">Ritorna alla lista</s:a> 
</s:form> 
</center> 
</div> 
</body> 
</html> 