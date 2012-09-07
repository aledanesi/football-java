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
  
<s:push value="career">
  
<div id="header2">
    <div id="menutop">
        <ul>
            <li>
        <s:url id="listPlayerURL" action="viewPlayer">
          <s:param name="id" value="%{player.id}"></s:param>
        </s:url>  
        <s:a href="%{listPlayerURL}"><s:text name="returnPlayer"/></s:a>
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
    <h1><s:text name="title2"/> <s:property value="player.firstName" /> <s:property value="player.lastName" /></h1>

    <table style="width: 500px;">
        <tr>
      <td style="text-align: left;"><s:text name="birthDate"/></td>
      <td style="text-align: left;"><s:date name="player.birthDate" format="dd/MM/yyyy" /></td>
      <td rowspan="9">
      <s:if test="%{image != null}">
            <img src="<s:url action="getDynamicImageCareer"><s:param name="id" value="%{career.id}"></s:param></s:url>">       
      </s:if>  
      <s:if test="%{image == null}">
          <img src="${pageContext.request.contextPath}/images/players/notFound.jpg" alt="Foto di ${lastName}" />
      </s:if>              
      </td>
  </tr>
        <tr class="bg2">
      <td style="text-align: left;"><s:text name="birthPlace"/></td>
      <td style="text-align: left;"><s:property value="player.birthPlace" /></td>
  </tr>
        <tr>
      <td style="text-align: left;"><s:text name="country"/></td>
      <td style="text-align: left;"><s:property value="player.nation.name" /></td>
  </tr>
        <tr class="bg2">
      <td style="text-align: left;"><s:text name="height"/></td>
      <td style="text-align: left;"><s:property value="player.height" /></td>
  </tr>
        <tr>
      <td style="text-align: left;"><s:text name="weight"/></td>
      <td style="text-align: left;"><s:property value="player.weight" /></td>
  </tr>
        <tr class="bg2">
      <td style="text-align: left;"><s:text name="position"/></td>
      <td style="text-align: left;"><s:property value="player.position.name" /></td>
  </tr>
        <tr>
      <td style="text-align: left;"><s:text name="officialSite"/></td>
      <td style="text-align: left;"></td>
  </tr>
        <tr class="bg2">
      <td style="text-align: left;"><s:text name="nation"/></td>
      <td style="text-align: left;"><img src="${pageContext.request.contextPath}/images/flags/${player.nation.id}.png" alt="${player.nation.name}" /></td>
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
        <th colspan="2">Presenze </th>
    </tr>
        <tr class="thead2 tcharw">
        <th colspan=3>&nbsp;</th>        
        <th>Partite </th>
        <th>Goal </th>
    </tr>
    
		<tr class="bg2" style="">
      	 
        <td><s:property value="stagione.value" /></td>
        <td style="text-align: left;"><img src="${pageContext.request.contextPath}/images/flags/${team.nation.id}.png" alt="${team.nation.name}" /> &nbsp;<s:property value="team.name" /> <s:if test="team.name != teamOwner.name">(<s:property value="teamOwner.name" />)</s:if></td>
        <td><s:property value="serie" /></td>
        <td><s:property value="dettaglioCarriera.partiteGiocate" /></td>
        <td style="text-align: center;"><s:property value="goal" /></td>
    </tr>

    </table>

    </div>

  <br /><br />
  
</div>

</s:push>

</body>
</html>