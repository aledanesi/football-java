<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setBundle basename="messages" var="label" scope="session" />

<spring:url var="indexURL" value="/index.jsp" />

<spring:url var="logoutURL" value="/j_spring_security_logout" />


<div id="header">

<h1 id="logo-text">JCampionato <sup style="font: Verdana, Tahoma, arial, sans-serif;"><small style="font-size: 20px">beta</small></sup></h1>
<!-- <h2 id="slogan">put your site slogan here...</h2> -->

<spring:url var="loginURL" value="/login.jsp" />
<s:url id="checkURL" value="j_spring_security_check" />
<s:url id="logoutURL" value="j_spring_security_logout" />

<div id="header-links">
	<p><a href="${indexURL}">Home</a> | <a href="#">Contact</a> | <a href="#">Site Map</a>  
	
			<c:if test="${! empty user_in_session}">
				| <a href="${logoutURL}">Logout</a>
			</c:if>	
		
	</p>
	
	<c:if test="${! empty user_in_session}">
		<p>Benvenuto: ${user_in_session.username}</p>
	</c:if>
	<c:if test="${empty user_in_session}">
		<form action="../j_spring_security_check" method="post" style="padding-bottom: 5px;">
			<table>
				<tr>
					<td colspan="5">
						<c:if test="${not empty param.login_error}">
							<label style="font-size: 11px; color: red; font-weight: bold">
								<fmt:message bundle="${label}" key="error.login" />
							</label>
						</c:if>	
						&nbsp;
					</td>
				</tr>				
				<tr>
					<td><label style="font-size: 10px; ">User:</label></td>
					<td><input type="text" name="j_username" style="width: 100px">	 </td>
					<td><label style="font-size: 10px; ">Psw:</label></td>
					<td><input type="password" name="j_password" style="width: 100px"></td>						
					<td><input type="submit" style="background:url(${pageContext.request.contextPath}/images/reg_button.png) no-repeat; width: 15px" value=""></td>
				</tr>
			</table>		
		</form>

	</c:if>	
		
</div>

</div>