<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>


<spring:url var="indexURL" value="/index.jsp" />

<spring:url var="logoutURL" value="/j_spring_security_logout" />


<div id="header">

<h1 id="logo-text">JCampionato <sup style="font: Verdana, Tahoma, arial, sans-serif;"><small style="font-size: 20px">beta</small></sup></h1>
<!-- <h2 id="slogan">put your site slogan here...</h2> -->

<s:url id="logoutURL" value="j_spring_security_logout" />

<div id="header-links">
<p><a href="${indexURL}">Home</a> | <a href="index.html">Contact</a> | <a href="index.html">Site Map</a> | <a href="${logoutURL}">Logout</a></p><%-- <s:a href="%{logoutURL}">Logout</s:a>  --%>
<c:if test="${! empty user_in_session}"><p>Benvenuto: ${user_in_session.username}</p></c:if>
</div>

</div>