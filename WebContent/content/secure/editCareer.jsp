<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
  pageEncoding="ISO-8859-1"%>

<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>### editCareer.jsp ###</title>

<s:head />

<link rel="stylesheet" href="styles/Envision.css" type="text/css" />


<%-- JQUERY --%>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery-1.3.2.min.js"></script>
    
<%-- APPLICATION SPECIFIC --%>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/script.js"></script>  

<script language="Javascript">
var jQ = jQuery.noConflict();

jQ(document).ready(function()
{ 
  jQ('#firstName').focus(); 
});
</script>

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
  
<s:form action="saveCareer" enctype="multipart/form-data" method="POST" style="width: 350px;"> 
<s:push value="career">
<input type="hidden" name="player.id" value="${playerId}" />
  


<div id="header2">
    <div id="menutop">
        <ul>                                 
            <li>
        <s:url id="listURL" action="viewPlayer">
          <s:param name="id">${playerId}</s:param>
        </s:url>              
        <s:a href="%{listURL}">Torna alla lista</s:a>
            </li>
            <li style="width: 80px; text-align: center;">
        <input type="submit" value="Salva"  />
            </li>  
         </ul>             
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
              <td colspan="2" style="text-align: center"><img src="<s:url action="getDynamicImageCareer"><s:param name="id">${career.id}</s:param></s:url>"></td>       
        </s:if>  
        <s:if test="%{image == null}">
            <td colspan="2" style="text-align: center">
              <img src="${pageContext.request.contextPath}/images/players/notFound.jpg" alt="Foto di ${lastName}" />
            </td>  
        </s:if>                       
      </tr>
      
      <s:hidden name="id" />
      
      <s:file name="userImage" label="Foto" key="photo" size="15" /> 
                      
      <s:textfield name="stagione" id="stagione" key="season" />
      <s:select name="team.id" list="teamList" listKey="id" listValue="name" key="team" />
      
      <s:textfield name="serie" id="serie" key="division" />    
      <s:textfield name="dettaglioCarriera.partiteGiocate" id="dettaglioCarriera.partiteGiocate" key="playedGames" size="4" />    

      <s:textfield name="goal" id="goal" key="goal" size="4" />                                  

      <s:textfield name="dettaglioCarriera.sostFatte" id="dettaglioCarriera.sostFatte" key="sostitutionMade" size="4" />    
      <s:textfield name="dettaglioCarriera.sostAvute" id="dettaglioCarriera.sostAvute" key="sostitutionReceived" size="4" />                                  

      <s:textfield name="dettaglioCarriera.ammonizioni"       id="dettaglioCarriera.ammonizioni"     key="yellowCard" size="4" />    
      <s:textfield name="dettaglioCarriera.espulsioni"       id="dettaglioCarriera.espulsioni"      key="redCard" size="4" />                                  
      
  </s:push>      
                  
  </s:form>  

</div>

<!--footer starts here-->
<div id="footer">

<p>&copy; 2012 <strong>Football Java</strong> | Design by: <a href="#">aledanesi</a></p>

</div>

<!-- wrap ends here --></div>

</body>
</html>