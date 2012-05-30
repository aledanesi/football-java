<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> 
<%@taglib uri="/struts-tags" prefix="s"%> 
<%@taglib uri="/struts-dojo-tags" prefix="sx" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> 
<html xmlns="http://www.w3.org/1999/xhtml"> 
<head> 
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" /> 
<title>editPlayer.jsp</title> 
<sx:head /> 
<link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/styles/style.css" /> 
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery-1.3.2.min.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery.validate.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/script.js"></script> 
<script language="Javascript"> 
var jQ = jQuery.noConflict(); 
jQ(document).ready(function() 
{
jQ('#firstName').focus(); }
); 
</script> 
<s:head />
</head>
<body>
<div id="header">
<div id="menutop">
<ul>
<li>
<s:url id="returnURL" action="searchTeam">
<s:param name="division.id">1</s:param>
</s:url> 
<s:a href="%{returnURL}">
Torna all'elenco delle squadre
</s:a> 
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
<s:form action="searchPlayer" name="formRicerca">
<table summary="Ricerca giocatori AIC">
<tr class="thead1 tcharw bolded">
<td>
Cerca un Giocatore
</td>
</tr>
<tr class="thead3">
<td>
Seleziona l'iniziale del cognome:
</td>
</tr>
</table>
<table summary="Iniziali dei giocatori AIC">
<tr>
<s:iterator value="lettereRicerca" status="obj">
<td><a href="searchPlayer.action?iniziale=<s:property />"><s:property /></a></td>
</s:iterator>
</tr>
</table>
<table summary="Ricerca giocatori AIC per iniziale">
<tr class="thead1 tcharw bolded">
<td>
Ricerca libera: inserisci le lettere iniziali o il cognome intero
</td>
</tr>
<tr>
<td>
<input type="text" name="iniziale" size='28' />
<input type='button' name='iniziale' value='Cerca' size='28' onclick='javascript: window.document.formRicerca.submit();' />
<input type='button' name='iniziale' value='Reset' size='28' onclick="javascript: document.location.href='searchPlayer.action?iniziale=';" />
</td>
</tr>
</table>
</s:form>
<br/>
<s:if test="playerList.size() > 0">
<s:set name="nomeSquadra" value="%{international}" />
<div class="content">
<table summary="Risultati ricerca giocatori">
<tr class="thead1 tcharw">
<th>Risultati ottenuti:</th>
</tr>
<tr class="thead2 tcharw">
<th>Clicca su un nome per accedere alla relativa scheda:</th>
</tr>
<s:iterator value="playerList" status="playerStatus">
<tr>
<td class="bg2 alle">
<s:url id="viewURL" action="viewPlayer">
<s:param name="id" value="%{id}"></s:param> 
</s:url> 
<s:a href="%{viewURL}"><s:property value="lastName" /> <s:property value="firstName" />
</s:a> 
(<s:property value="nation.value" />) nato a <s:property value="birthPlace" /> il <s:date name="birthDate" format="dd/MM/yyyy" /> 
</td>
</tr> 
</s:iterator>
</table>
</div>
</s:if> 
</div>
</div>
<br/><br/>
</body>
</html>