<%@ taglib uri="http://java.sun.com/jsp/jstl/core"   prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"    prefix="fmt" %>

<fmt:setBundle basename="messages" var="label" scope="session" />

<spring:url var="indexURL" value="/loading.jsp" />

<spring:url var="logoutURL" value="/j_spring_security_logout" />


<div id="header">

	<h1 id="logo-text"><spring:message code="header.message1" /> <sup style="font: Verdana, Tahoma, arial, sans-serif;"><small style="font-size: 20px">beta</small></sup></h1>
	
	<c:choose>
		<c:when test="${not empty param.login_error}">
			<c:set var="visibility" value="visible" />
		</c:when>
		<c:otherwise>
			<c:set var="visibility" value="hidden" />
		</c:otherwise>
	</c:choose>
	
	<div id="header-links">
		<p style="text-align: right"><a href="${indexURL}"><spring:message code="header.message2" /></a> | <a href="#"><spring:message code="header.message3" /></a> | <a href="${pageContext.request.contextPath}/site/"><spring:message code="header.message4" /></a>  
		
				<c:if test="${empty user_in_session}">
					| <a href="#" onclick="document.getElementById('loginForm').style.visibility = 'visible';"><spring:message code="header.message5" /></a>
				</c:if>	
				<c:if test="${! empty user_in_session}">
					| <a href="${logoutURL}"><spring:message code="header.message6" /></a>
				</c:if>	
		</p>
		
		<c:if test="${! empty user_in_session}">
			<p><spring:message code="header.message7" />: ${user_in_session.username}</p>
		</c:if>
		<c:if test="${empty user_in_session}">
			<form id="loginForm" action="../j_spring_security_check" method="post" style="padding-bottom: 5px; visibility: ${visibility}">
				<table>
					<tr>
						<td colspan="5">
							<a href="#" onclick="user.newUser(); return false;" style="font-size: 11px; color: black; font-weight: bold">
								<spring:message code="header.message8" />
							</a>
							&nbsp;					
							<c:if test="${not empty param.login_error}">
								<label style="font-size: 11px; color: red; font-weight: bold">
									<fmt:message bundle="${label}" key="error.login" />
								</label>
							</c:if>	
							&nbsp;
						</td>
					</tr>				
					<tr>
						<td><label style="font-size: 10px; "><spring:message code="header.message9" />:</label></td>
						<td><input type="text" name="j_username" style="width: 100px">	 </td>
						<td><label style="font-size: 10px; "><spring:message code="header.message10" />:</label></td>
						<td><input type="password" name="j_password" style="width: 100px"></td>						
						<td><input type="submit" style="background:url(${pageContext.request.contextPath}/images/reg_button.png) no-repeat; width: 15px" value=""></td>
					</tr>
				</table>		
			</form>
	
		</c:if>	
			
	</div>

</div>