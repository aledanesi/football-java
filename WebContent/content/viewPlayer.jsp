<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />


<title><s:text name="title"/> <s:property value="firstName" /> <s:property value="lastName" /> - a cura di Football.it</title>

<%-- CSS --%>
<link rel="stylesheet" href="styles/Envision.css" type="text/css" />

</head>
<body>
	
<s:push value="player">
	
<div id="header2">
    <div id="menutop">
        <ul>
            <li>
				<s:url id="listPlayerURL" action="listByTeamPlayer">
					<s:param name="teamId" value="%{teamId}"></s:param>
					<s:param name="id" value=""></s:param>
				</s:url>	
				<s:a href="%{listPlayerURL}"><s:text name="returnListPlayer"/></s:a>
            </li>
            <sec:authorize access="hasRole('ROLE_ADMIN')">   
            <li>
				<s:url id="insertCareerURL" action="insertCareer">
					<s:param name="playerId" value="%{id}"></s:param>
				</s:url>
				<s:a href="%{insertCareerURL}"><s:text name="insertCareer"/></s:a>
            </li>  
            <li>
				<s:url id="changeTeamURL" action="changeTeam">
					<s:param name="idPlayer" value="%{id}"></s:param>
				</s:url>
				<s:a href="%{changeTeamURL}"><s:text name="changeTeam"/></s:a>
            </li>                                   
            </sec:authorize>
            <li>
				<s:url id="listPlayerURL" action="searchPlayer">
					<s:param name="division.id" value=""></s:param>
					<s:param name="teamId" value=""></s:param>
					<s:param name="id" value=""></s:param>
				</s:url>	
				<s:a href="%{listPlayerURL}"><s:text name="searchPlayer"/></s:a>
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
	    <td rowspan="9">
			<s:if test="%{image != null}">
	        	<img src="<s:url action="getDynamicImagePlayer"><s:param name="id" value="%{id}"></s:param></s:url>"> 			
			</s:if>	
			<s:if test="%{image == null}">
	    		<img src="${pageContext.request.contextPath}/images/players/notFound.jpg" alt="Foto di ${lastName}" />
			</s:if>		    			
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
       <tr class="bg2">
    	<td style="text-align: left;"></td>
	    <td style="text-align: left;"></td>
	</tr>
    </table>    

</div>

<div class="scheda analitica">
    <h1><s:text name="title3"/></h1>
</div>

    <table style="width: 700px;">
	    <tr class="thead1 tcharw">
		    <th>Stagione </th>
		    <th>Squadra </th>
		    <th>Serie </th>
		    <th colspan="3">Presenze </th>
		    <th colspan="2">Sostituzioni </th>
		    <th colspan="3" style="text-align: center;">Disciplina </th>
	       	<sec:authorize access="hasRole('ROLE_ADMIN')"> 
			    <th>&nbsp;</th>
		    </sec:authorize>
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
		    <sec:authorize access="hasRole('ROLE_ADMIN')"> 
		    	<th>&nbsp;</th>
		    </sec:authorize> 
		    <th>&nbsp;</th>
		</tr>
	
	<s:if test="careerList.size() > 0">
		<s:iterator value="careerList" status="careerStatus">
		
	    <tr class="bg2">
		    <td><s:property value="stagione.value" /></td>
		    <td style="text-align: left;"><img src="${pageContext.request.contextPath}/images/flags/${team.nation.id}.png" alt="${team.nation.name}" /> &nbsp;<s:property value="team.name" /></td>
		    <td><s:property value="serie" /></td>
		    <td><a href="/dettaglio/37919/37924/37935/37942/37948/37962/37985/37991/38000/38006/38015/38019/38031/38296/38301/=1637919|37924|37935|37942|37948|37962|37985|37991|38000|38006|38015|38019|38031|38296|38301|"><s:property value="dettaglioCarriera.partiteGiocate" /></a>/<s:property value="dettaglioCarriera.partiteTotali" /></td>
		    <td><s:property value="dettaglioCarriera.minutiGiocati" />/<s:property value="dettaglioCarriera.minutiTotali" /></td>
		    <td style="text-align: center;"><s:property value="goal" /></td>
		    <td style="text-align: center;"><s:property value="dettaglioCarriera.sostFatte" /></td>
		    <td style="text-align: center;"><s:property value="dettaglioCarriera.sostAvute" /></td>
		    <td style="text-align: center;"><s:property value="dettaglioCarriera.ammonizioni" /></td>
		    <td style="text-align: center;"><s:property value="dettaglioCarriera.espulsioni" /></td>
		    <td style="text-align: center;"><s:property value="dettaglioCarriera.giornateSqualifica" /></td>
		    <sec:authorize access="hasRole('ROLE_ADMIN')"> 
			    <td>
					<s:url id="editURL" action="editCareer">
						<s:param name="id" value="%{id}"></s:param>
						<s:param name="playerId" value="%{player.id}"></s:param>
					</s:url> 
					<s:a href="%{editURL}"><img src="${pageContext.request.contextPath}/images/edit.png" alt="<s:text name="edit"/>" />
					</s:a>		    	
			    </td>
		    </sec:authorize> 
		    <td>
				<s:url id="viewURL" action="viewCareer">
					<s:param name="id" value="%{id}"></s:param>
					<s:param name="playerId" value="%{player.id}"></s:param>
				</s:url> 
				<s:a href="%{viewURL}"><img src="${pageContext.request.contextPath}/images/view.png" alt="<s:text name="view"/>" />
				</s:a>		    	
		    </td>		    
		</tr>
		</s:iterator>
	</s:if>

    </table>

    </div>

	<br /><br />
	
</div>

</s:push>

</body>
</html>