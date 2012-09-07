<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
    
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<s:head />
<link rel="stylesheet" href="styles/Envision.css" type="text/css" />

<%-- JQUERY --%>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery/jquery-1.7.2.js"></script>
    
<%-- APPLICATION SPECIFIC --%>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/script.js"></script>  </head>


<body>
  
<div id="header2">
    <div id="menutop">
        <ul>
          <sec:authorize access="hasRole('ROLE_ADMIN')">
              <li>
          <s:url id="insertTeamURL" action="insertTeam">
            <s:param name="nation.id" value="%{nation.id}"></s:param>
            <s:param name="division.id" value="%{division.id}"></s:param>
          </s:url>
          <s:a href="%{insertTeamURL}"><s:text name="insertTeam"/></s:a>
              </li>
            </sec:authorize>
            <li>
        <s:url id="searchPlayerURL" action="searchPlayer">
          <s:param name="team.id" value="%{teamId}"></s:param>
        </s:url>  
        <s:a href="%{searchPlayerURL}"><s:text name="searchPlayer"/></s:a>
            </li>
    </ul>
    </div>
</div>  

<div align="center">   
  <s:form action="searchTeam" style="width: 200px">
    <input type="hidden" name="divisionId" value="${division.id}" /> 
    <s:push value="team">  
      <s:select name="nation.id" list="nationList" listKey="id" listValue="name" key="nation" />
      <s:select name="division.id" list="divisionList" listKey="id" listValue="name" key="division" />
      <s:token />  
      <s:submit value="Cerca" />
    </s:push>
  </s:form>
</div>

<br /><br />  

<div align="center"> 
  
  <display:table name="${teamList}" id="row" pagesize="22" requestURI="" style="width: 600px" >
    <display:column property="name" title="Squadra" />
    <display:column property="stadium" title="Stadio" />
    <display:column property="posti" title="Posti" />
    <sec:authorize access="hasRole('ROLE_ADMIN')">
      <display:column style="width: 5%">
        <s:url id="editURL" action="editTeam">
          <s:param name="id">
            ${row.id}
          </s:param>
        </s:url>   
        <s:a href="%{editURL}">
          <img src="${pageContext.request.contextPath}/images/edit.png" alt="<s:text name="edit"/>" />
        </s:a>          
      </display:column>
    </sec:authorize>
    <display:column style="width: 5%">
      <s:url id="viewURL" action="listByTeamPlayer">
        <s:param name="team.id">
          ${row.id}
        </s:param>  
      </s:url> 
      <s:a href="%{viewURL}">
        <img src="${pageContext.request.contextPath}/images/view.png" alt="<s:text name="view"/>" />
      </s:a>        
    </display:column>
    <sec:authorize access="hasRole('ROLE_ADMIN')">      
      <display:column style="width: 5%">
        <s:url id="deleteURL" action="deleteTeam">
          <s:param name="id">
            ${row.id}
          </s:param>
          <s:param name="division.id">${divisionId}</s:param>
        </s:url>     
        <s:a href="%{deleteURL}" onclick="return project.confirmDeleteTeam();">
          <img src="${pageContext.request.contextPath}/images/delete.png" alt="<s:text name="delete"/>" />
        </s:a>        
      </display:column>
    </sec:authorize>            
  </display:table>

</div>

</body>
</html>