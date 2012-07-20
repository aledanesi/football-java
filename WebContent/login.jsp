<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core'   prefix='c' %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<html>
	<head>
		<title>### login.jsp ###</title>
		<s:head />	
		<link rel="stylesheet" href="styles/Envision.css" type="text/css" />
	</head>
	<body>
	
		<div id="sidebar">
			<c:if test="${not empty param.login_error}">    
				<span class="actionError">    
					Your login attempt was not successful, try again.<br/><br/>         
					Reason: <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/>.   
				</span> 
			</c:if>	
			<form action="j_spring_security_check" method=post>
				<p><label>Name</label><input type="text" name="j_username"><br>
				<label>Password</label><input type="password" name="j_password"><br>
				<br><input type="submit" class="button" value="Login"></p>
			</form>
		</div>
		
		<div id="main">
			<p style="vertical-align: middle">
				<img src="images/site/soccer-player.jpg" style="border:none;" />
			</p>
		</div>
	
	</body>
</html>