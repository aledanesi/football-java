<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> 
<%@taglib uri="/struts-tags" prefix="s"%> 
<html> 
<head> 
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"> 
<title>updateTeam.jsp</title> 
<s:head /> 
<style type="text/css"> 
@import url(styles/style.css); 
</style> 
<SCRIPT language="Javascript"> 
function confirmDelete() 
{ 
return confirm("Vuoi davvero eliminare questo record?"); 
} 
</SCRIPT> 
</head> 
<body> 
<s:form action="saveOrUpdateTeam"> 
<s:push value="team"> 
<s:hidden name="id" /> 
<s:textfield name="name" key="name" /> 
<s:textfield name="city" key="city" /> 
<s:textfield name="colorTeam" key="colorTeam" /> 
<s:textfield name="email" key="email" /> 
<s:textfield name="address" key="address" /> 
<s:textfield name="stadium" key="stadium" /> 
<s:textfield name="phone" key="phone" /> 
<s:textfield name="fax" key="fax" /> 
<s:submit /> 
</s:push> 
</s:form> 
<s:if test="teamList.size() > 0"> 
<div class="content"> 
<table class="listTable" cellpadding="5px"> 
<tr class="even"> 
<th><s:text name="team"/></th> 
<%-- 
<th><s:text name="city"/></th>
<th><s:text name="colorTeam"/></th>
<th><s:text name="email"/></th>
<th><s:text name="address"/></th>
--%>
<th><s:text name="stadium"/></th> 
<th><s:text name="phone"/></th> 
<th><s:text name="fax"/></th> 
<th style="border-collapse:collapse; empty-cells: show;"></th> 
<th style="border-collapse:collapse; empty-cells: show;"></th> 
<th>&nbsp;</th> 
</tr> 
<s:iterator value="teamList" status="teamStatus"> 
<tr class="<s:if test="#teamStatus.odd == true ">odd</s:if><s:else>even</s:else>"> 
<td style="white-space: nowrap"><s:property value="name" /></td> 
<%-- 
<td><s:property value="city" /></td>
<td><s:property value="colorTeam" /></td>
<td><s:property value="email" /></td>
<td style="white-space: nowrap"><s:property value="address" /></td>
--%>
<td style="white-space: nowrap"><s:property value="stadium" /></td> 
<td style="white-space: nowrap"><s:property value="phone" /></td> 
<td style="white-space: nowrap"><s:property value="fax" /></td> 
<td style="width: 5%"> 
<s:url id="editURL" action="editTeam"> 
<s:param name="id" value="%{id}"></s:param> 
</s:url> 
<s:a href="%{editURL}"> 
<img src="${pageContext.request.contextPath}/images/edit.png" alt="<s:text name="edit"/>" /> 
</s:a> 
</td> 
<td style="width: 5%"> 
<s:url id="viewURL" action="listByTeamPlayer"> 
<s:param name="team.id" value="%{id}"></s:param> 
</s:url> 
<s:a href="%{viewURL}"><img src="${pageContext.request.contextPath}/images/view.png" alt="<s:text name="view"/>" /> 
</s:a> 
</td> 
<td style="width: 5%"> 
<s:url id="deleteURL" action="deleteTeam"> 
<s:param name="team.id" value="%{id}"></s:param> 
</s:url> 
<s:a href="%{deleteURL}" onclick="return confirmDelete();"> 
<img src="${pageContext.request.contextPath}/images/delete.png" alt="<s:text name="delete"/>" /> 
</s:a> 
</td> 
</tr> 
</s:iterator> 
</table> 
</div> 
</s:if> 
</body> 
</html> 