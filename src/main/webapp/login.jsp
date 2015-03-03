<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<fmt:setBundle basename="messages" var="label" scope="session" />

<html>
<head>
<title>### login.jsp ###</title>
<link rel="stylesheet" href="styles/Envision.css" type="text/css" />
</head>
<body><i18n:message key="insertPlayer"/>
 
	<div id="sidebar">
		
		<div>
			<c:if test="${not empty param.login_error}">
				<br /> 
				<span style="margin-left: 15px; font-size: 11px; font-weight: bold; color: red"><fmt:message bundle="${label}" key="error.login" />
	
				<br /> 
				<%--
				Reason: ${SPRING_SECURITY_LAST_EXCEPTION.message}. 
				--%>
			</c:if>

			<form action="j_spring_security_check" method=post>
				<div id="fitem">
				  <div><label>Name:</label></div>
				  <div>
						<input type="text" name="j_username" value="user" style="width: 150px">	  	
				  </div>
				</div>
				<div id="fitem">
				  <div><label>Password:</label></div>
				  <div>
						<input type="password" name="j_password" value="user" style="width: 150px">
				  </div>
				</div>
				<div id="fitem">
					<input type="submit" value="Login">
				</div>			
			</form>
		</div>
				
	</div>
		<div>
			<p style="vertical-align: middle">
				<img src="images/site/soccer-player.jpg" style="border: none;" />
			</p>
		</div>
	

</body>
</html>