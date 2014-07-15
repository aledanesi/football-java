<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />

<title>### viewCareer.jsp ###</title>

</head>

<spring:url var="listPlayerURL" value="/players/view.do">
	<spring:param name="id" value="${player.id}"></spring:param>
</spring:url>  

<spring:url var="imageURL" value="/players/image.do">
	<spring:param name="id" value="${player.id}"></spring:param>
</spring:url>

<body>
  
<div id="header2">
    <div id="menutop">
        <ul>
            <li>
		        <a href="${listPlayerURL}"><spring:message code="returnPlayer"/></a>
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
    <h1><spring:message code="title2"/> ${player.firstName} ${player.lastName}</h1>

    <table style="width: 500px;">
        <tr>
      <td style="text-align: left;"><spring:message code="birthDate"/></td>
      <td style="text-align: left;"><fmt:formatDate value="${player.birthDate}" type="both" pattern="dd/MM/yyyy" /></td>
      <td rowspan="9">
      <c:if test="${player.image != null}">
            <img src="${imageURL}">       
      </c:if>  
      <c:if test="${player.image == null}">
          <img src="${pageContext.request.contextPath}/images/players/notFound.jpg" alt="Foto di ${lastName}" />
      </c:if>              
      </td>
  </tr>
        <tr class="bg2">
      <td style="text-align: left;"><spring:message code="birthPlace"/></td>
      <td style="text-align: left;">${player.birthPlace}</td>
  </tr>
        <tr>
      <td style="text-align: left;"><spring:message code="country"/></td>
      <td style="text-align: left;">${player.nation.name}</td>
  </tr>
        <tr class="bg2">
      <td style="text-align: left;"><spring:message code="height"/></td>
      <td style="text-align: left;">${player.height}</td>
  </tr>
        <tr>
      <td style="text-align: left;"><spring:message code="weight"/></td>
      <td style="text-align: left;">${player.weight}</td>
  </tr>
        <tr class="bg2">
      <td style="text-align: left;"><spring:message code="position"/></td>
      <td style="text-align: left;">${player.position.name}</td>
  </tr>
        <tr>
      <td style="text-align: left;"><spring:message code="officialSite"/></td>
      <td style="text-align: left;"></td>
  </tr>
        <tr class="bg2">
      <td style="text-align: left;"><spring:message code="nation"/></td>
      <td style="text-align: left;"><img src="${pageContext.request.contextPath}/images/flags/${player.nation.id}.png" alt="${player.nation.name}" /></td>
  </tr>
       <tr class="bg2">
      <td style="text-align: left;"></td>
      <td style="text-align: left;"></td>
  </tr>
    </table>    

</div>

<div class="scheda analitica">
    <h1><spring:message code="title3"/></h1>
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
      	 
        <td>${career.stagione.value}</td>
        <td style="text-align: left;"><img src="${pageContext.request.contextPath}/images/flags/${career.team.nation.id}.png" alt="${career.team.nation.name}" /> &nbsp;${career.team.name} <c:if test="${career.team.name != career.teamOwner.name}">(${career.teamOwner.name})</c:if></td>
        <td>${career.serie}</td>
        <td>${career.dettaglioCarriera.partiteGiocate}</td>
        <td style="text-align: center;">${career.goal}</td>
    </tr>

    </table>

    </div>

  <br /><br />
  
</div>

</body>
</html>