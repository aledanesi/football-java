<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="/struts-tags" prefix="s"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />


<title><s:text name="title"/> <s:property value="firstName" /> <s:property value="lastName" /> - a cura di Football.it</title>

<%-- CSS --%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/style.css" />

<script type='text/javascript' src='dwr/engine.js'></script>
<script type='text/javascript' src='dwr/util.js'></script>
<script type='text/javascript' src='dwr/interface/divisionManager.js'></script>
<script type='text/javascript' src='dwr/interface/teamManager.js'></script>


<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery.validate.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/script.js"></script>

<script type="text/javascript">
function changeNation()
{
	var nation = dwr.util.getValue("teamForm_nation_id");
	//var nation = jQ("#teamForm_nation_id").val();
	
	divisionManager.listDivisionsByNation(nation, resultNation);
}
function resultNation(data)
{
	dwr.util.removeAllOptions("teamForm_division_id");
	dwr.util.removeAllOptions("teamForm_teamId");
	dwr.util.addOptions("teamForm_division_id", data, "id", "name");
	var division = dwr.util.getValue("teamForm_division_id");
	teamManager.listTeamsByDivision(division, resultDivision);
}
function changeDivision()
{
	var division = dwr.util.getValue("teamForm_division_id");
	//var division = jQ("#teamForm_division_id").val();
	
	teamManager.listTeamsByDivision(division, resultDivision);
}
function resultDivision(data)
{
	dwr.util.removeAllOptions("teamForm_teamId");
	dwr.util.addOptions("teamForm_teamId", data, "id", "name");
}
</script>

<s:head />
</head>

<body>
<s:actionerror />
<s:fielderror  />

<s:form action="saveChangeTeamPlayer" cssClass="cmxform" id="teamForm" enctype="multipart/form-data" method="POST">


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
            <li style="width: 80px; text-align: center;">
				<input type="submit" value="Salva"  />
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
						<s:if test="%{image != null}">
				        	<img src="<s:url action="getDynamicImagePlayer">
				        		<s:param name="id" value="%{id}"></s:param>
				        		<s:param name="teamId" value="%{teamId}"></s:param>
				        	</s:url>"> 			
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
    </table>    

</div>

<div class="scheda analitica">
    <h1><s:text name="changeTeam"/></h1>
</div>

			<s:push value="player">
			
				<s:hidden name="id" />

			    <table style="width: 350px;">
			    
			    	<s:select name="nation.id" list="nationList" listKey="id" listValue="name" key="nation" onchange="changeNation()" />
			    
			    	<s:select name="division.id" list="divisionList" listKey="id" listValue="name" key="division" onchange="changeDivision()" />
			    					    
			 	    <s:select name="teamId" list="teamList" listKey="id" listValue="name" key="team" />
																
			    </table>	    
	    	</s:push>										

    </div>

</div>

</s:push>

</s:form>	

</body>
</html>