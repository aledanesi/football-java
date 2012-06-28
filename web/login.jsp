<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core'   prefix='c' %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<html>
<head>
<link rel="stylesheet" href="styles/Envision.css" type="text/css" />
</head>
<body>
<div id="wrap"><!--header -->
<div id="header">

<h1 id="logo-text">Football Java<sup style="font: 40%/ 1.5em Verdana, Tahoma, arial, sans-serif;">beta</sup>
</h1>
<!-- <h2 id="slogan">put your site slogan here...</h2> -->

<div id="header-links">
<p><a href="index.html">Home</a> | <a href="index.html">Contact</a>
| <a href="index.html">Site Map</a></p>
</div>

</div>

<!-- content-wrap starts here -->
<div id="content-wrap">
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
<br>
<input type="submit" class="button" value="Login"></p>
</form>
</div>

<div id="main">
<p style="vertical-align: middle"><img
	src="images/site/soccer-player.jpg" style="border:none;" /></p>
</div>

</div>

<!--footer starts here-->
<div id="footer">

<p>&copy; 2012 <strong>Football Java</strong> | Design by: <a href="#">aledanesi</a></p>

</div>

<!-- wrap ends here --></div>

</body>
</html>