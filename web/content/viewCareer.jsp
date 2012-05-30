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
<div id="header"> 
<div id="menutop"> 
<ul> 
<li> 
<s:url id="viewPlayerURL" action="viewPlayer"> 
<s:param name="id">${player.id}</s:param> 
</s:url> 
<s:a href="%{viewPlayerURL}">Torna al dettaglio</s:a> 
</li> 
</ul> 
</div> 
</div> 
<s:push value="career"> 
<div class="scheda analitica"> 
<h1>${player.firstName} ${player.lastName}</h1> 
</div> 
<table style="width: 900px;"> 
<tr> 
<s:if test="%{image != null}"> 
<td colspan="12" style="text-align: center"><img src="<s:url action="getDynamicImageCareer"><s:param name="id">${career.id}</s:param></s:url>"></td> 
</s:if> 
<s:if test="%{image == null}"> 
<td colspan="12" style="text-align: center"> 
<img src="${pageContext.request.contextPath}/images/players/notFound.jpg" alt="Foto di ${lastName}" /> 
</td> 
</s:if> 
</tr> 
<tr class="thead1 tcharw"> 
<th>Stagione </th> 
<th>Squadra </th> 
<th>Serie </th> 
<th colspan="3">Presenze </th> 
<th colspan="2">Sostituzioni </th> 
<th colspan="3" style="text-align: center;">Disciplina </th> 
<th>&nbsp;</th> 
</tr> 
<tr class="thead2 tcharw"> 
<th colspan=3>&nbsp;</th> 
<th>Partite </th> 
<th>Min </th> 
<th>Goal </th> 
<th>Fatte </th> 
<th>Avute </th> 
<th style="text-align: center;">Amm </th> 
<th style="text-align: center;">Esp </th> 
<th style="text-align: center;">GG. squal. </th> 
<th>&nbsp;</th> 
</tr> 
<tr class="bg2"> 
<td><s:property value="stagione" /></td> 
<td style="text-align: left;"><img src="${pageContext.request.contextPath}/images/flags/${team.nation.id}.png" alt="${team.nation.name}" /> &nbsp;<s:property value="team.name" /></td> 
<td><s:property value="serie" /></td> 
<td><a href="/dettaglio/37919/37924/37935/37942/37948/37962/37985/37991/38000/38006/38015/38019/38031/38296/38301/=1637919|37924|37935|37942|37948|37962|37985|37991|38000|38006|38015|38019|38031|38296|38301|"><s:property value="dettaglioCarriera.partiteGiocate" /></a>/<s:property value="dettaglioCarriera.partiteTotali" /></td> 
<td><s:property value="minutiGiocati" />/<s:property value="dettaglioCarriera.minutiTotali" /></td> 
<td style="text-align: center;"><s:property value="goal" /></td> 
<td style="text-align: center;"><s:property value="sostFatte" /></td> 
<td style="text-align: center;"><s:property value="sostAvute" /></td> 
<td style="text-align: center;"><s:property value="ammonizioni" /></td> 
<td style="text-align: center;"><s:property value="espulsioni" /></td> 
<td style="text-align: center;"><s:property value="giornateSqualifica" /></td> 
<td>&nbsp; 
</td> 
</tr> 
</table> 
</div> 
<br /> 
</div> 
</s:push> 
</body> 
</html>