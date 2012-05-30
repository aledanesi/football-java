<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> 
<%@taglib uri="/struts-tags" prefix="s"%> 
<%@taglib uri="/struts-dojo-tags" prefix="sx" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> 
<html xmlns="http://www.w3.org/1999/xhtml"> 
<head> 
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" /> 
<title>editCareer.jsp</title> 
<%-- CSS --%>
<link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/styles/style.css" /> 
<%-- JQUERY --%>
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
</head> 
<body> 
<s:form action="saveCareer" enctype="multipart/form-data" style="width: 300px;"> 
<s:push value="career"> 
<input type="hidden" name="player.id" value="${playerId}" /> 
<div id="header"> 
<div id="menutop"> 
<ul> 
<li> 
<s:url id="listURL" action="viewPlayer"> 
<s:param name="teamId" value="%{teamId}"></s:param> 
<s:param name="id">${playerId}</s:param> 
<s:param name="playerId" value=""></s:param> 
<s:param name="division.id" value=""></s:param> 
</s:url> 
<s:a href="%{listURL}">Torna alla lista</s:a> 
</li> 
<li style="width: 80px; text-align: center;"> 
<input type="submit" value="Salva" /> 
</li> 
</div> 
</div> 
<tr> 
<td colspan="2" style="margin: 15px;"> 
<p align="center" style="margin: 10px; font-weight: bold; font-size: 14px"> 
Inserisci anno di carriera
</p></td> 
</tr> 
<tr> 
<s:if test="%{image != null}"> 
<td colspan="2" style="text-align: center"><img src="<s:url action="getDynamicImageCareer"><s:param name="id">${id}</s:param></s:url>"></td> 
</s:if> 
<s:if test="%{image == null}"> 
<td colspan="2" style="text-align: center"> 
<img src="${pageContext.request.contextPath}/images/players/notFound.jpg" alt="Foto di ${lastName}" /> 
</td> 
</s:if> 
</tr> 
<s:hidden name="id" /> 
<s:file name="userImage" label="Foto" key="photo" /> 
<s:textfield name="stagione" id="stagione" key="season" /> 
<s:select name="team.id" list="teamList" listKey="id" listValue="name" key="team" /> 
<s:textfield name="serie" id="serie" key="division" /> 
<s:textfield name="dettaglioCarriera.partiteGiocate" id="dettaglioCarriera.partiteGiocate" key="playedGames" /> 
<s:textfield name="dettaglioCarriera.partiteTotali" id="dettaglioCarriera.partiteTotali" key="totalGames" /> 
<s:textfield name="dettaglioCarriera.minutiGiocati" id="dettaglioCarriera.minutiGiocati" key="playedMinutes" /> 
<s:textfield name="dettaglioCarriera.minutiTotali" id="dettaglioCarriera.minutiTotali" key="totalMinutes" /> 
<s:textfield name="goal" id="goal" key="goal" /> 
<s:textfield name="dettaglioCarriera.sostFatte" id="dettaglioCarriera.sostFatte" key="sostitutionMade" /> 
<s:textfield name="dettaglioCarriera.sostAvute" id="dettaglioCarriera.sostAvute" key="sostitutionReceived" /> 
<s:textfield name="dettaglioCarriera.ammonizioni" id="dettaglioCarriera.ammonizioni" key="yellowCard" /> 
<s:textfield name="dettaglioCarriera.espulsioni" id="dettaglioCarriera.espulsioni" key="redCard" /> 
<s:textfield name="dettaglioCarriera.giornateSqualifica" id="dettaglioCarriera.giornateSqualifica" key="disqMatchs" /> 
</s:push> 
</s:form> 
</body> 
</html>