<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>### vieCareer.jsp ###</title>

<s:head />

<link rel="stylesheet" href="styles/Envision.css" type="text/css" />


<%-- JQUERY --%>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery-1.3.2.min.js"></script>
		
<%-- APPLICATION SPECIFIC --%>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/script.js"></script>	

</head>
<body>
	
<div id="wrap"><!--header -->
<div id="header">

<h1 id="logo-text">Football Java <sup style="font: Verdana, Tahoma, arial, sans-serif;"><small style="font-size: 20px">beta</small></sup></h1>
<!-- <h2 id="slogan">put your site slogan here...</h2> -->

<s:url id="logoutURL" value="j_spring_security_logout" />

<div id="header-links">
<p><a href="index.jsp">Home</a> | <a href="index.html">Contact</a> | <a href="index.html">Site Map</a> | <s:a href="%{logoutURL}">Logout</s:a></p>
</div>

</div>

<!-- content-wrap starts here -->
<div id="content-wrap">
	
<div id="header2">
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

    <table style="width: 750px;">
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

	<br />

</s:push>

</div>

<!--footer starts here-->
<div id="footer">

<p>&copy; 2012 <strong>Football Java</strong> | Design by: <a href="#">aledanesi</a></p>

</div>

<!-- wrap ends here --></div>

</body>
</html>