<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<html>
<head>
	<title></title>    
</head>

<body>

	<div align="center">

		<display:table name="${divisionList}" id="row" pagesize="22" requestURI="" style="width: 600px" class="standard_tabelle">
			<display:column>
				<spring:url var="imageURL" value="/divisions/image.do">
					<spring:param name="id">${row.id}</spring:param>
				</spring:url>
				<img width="16" height="20" class="minifoto" src="${imageURL}"/>
			</display:column> 
			<display:column property="name" title="Divisione" />
			<display:column property="nationId" title="Nazione" />
			<sec:authorize access="hasRole('ROLE_ADMIN')">
				<display:column style="width: 15px">
					<a href="#" onclick="project.editDivision('${row.id}'); return false;">
						<img src="${pageContext.request.contextPath}/images/edit.png" alt="<spring:message code="edit"/>" /> 
					</a>
				</display:column>
				<display:column style="width: 15px">
					<spring:url var="deleteURL" value="/divisions/delete.do">
						<spring:param name="id">${row.id}</spring:param>
					</spring:url>
					<a href="#" onclick="project.confirmDeleteDivision('${deleteURL}'); return false;"> 
						<img src="${pageContext.request.contextPath}/images/delete.png" <alt="<spring:message code="delete"/>" />
					</a>
				</display:column>
			</sec:authorize>
		</display:table>
	</div>

	<jsp:include page="secure/editDivision.jsp" />
	
	<script type='text/javascript' src='${pageContext.request.contextPath}/dwr/engine.js'></script>
	<script type='text/javascript' src='${pageContext.request.contextPath}/dwr/util.js'></script>
	<script type='text/javascript' src='${pageContext.request.contextPath}/dwr/interface/businessDelegate.js'></script>
	
	
</body>

</html>