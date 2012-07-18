<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags"       prefix="s"%>
<%@ taglib uri="/struts-dojo-tags" prefix="sx" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>editPlayer.jsp</title>
<sx:head /> 

<link rel="stylesheet" type="text/css" media="screen"  href="${pageContext.request.contextPath}/styles/Envision.css" />

<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery/jquery-1.7.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery/jquery.validate.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/script.js"></script>

<script language="Javascript">  
var jQ = jQuery.noConflict();

jQ(document).ready(function()
{ 
	jQ('#firstName').focus(); 
});
</script>

<s:head />
</head>
<body>
	
<s:actionerror />
<s:fielderror  />		
	
<s:form action="savePlayer" enctype="multipart/form-data" method="POST" style="width: 300px"> 
<s:push value="player">

<div id="header2">
    <div id="menutop">
        <ul>                                 
            <li>
				<s:url id="listURL" action="listByTeamPlayer">
					<s:param name="teamId" value="%{teamId}"></s:param>
					<s:param name="id" value=""></s:param>
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
		        Modifica Giocatore
		      </p></td>
		    </tr>
									
				<tr>
					<s:if test="%{image == null}">
		    			<td colspan="2" style="text-align: center"><img src="${pageContext.request.contextPath}/images/players/notFound.jpg" alt="Foto di ${lastName}" /></td>

					</s:if>
					<s:if test="%{image != null}">
						<td colspan="2" style="text-align: center">
					       <img src="<s:url action="getDynamicImagePlayer"><s:param name="id" value="%{id}"></s:param></s:url>"> 
						</td>								
					
					</s:if>
				</tr>
				
										
				<s:hidden name="id" />
				<s:hidden name="teamId" />
				
				<s:file name="userImage" label="Foto" key="photo" />
					
				<s:textfield name="firstName" id="firstName" key="firstName" />
				<s:textfield name="lastName" id="lastName" key="lastName" />
				<sx:datetimepicker id="birthDate" name="birthDate" key="birthDate" cssStyle="text-align: left;" />
				
				<s:textfield name="birthPlace" key="birthPlace" cssStyle="text-align: left;" />	
				<s:select name="nation.id" list="nationList" listKey="id" listValue="name" key="nation" />		
				<s:select name="position.id" list="positionList" listKey="id" listValue="name" key="position" />		
				<s:textfield name="height" key="height" size="4" maxlength="4" />
				<s:textfield name="weight" key="weight" size="4" maxlength="3" />							
						 
	</s:push>
						
</s:form>	

</body>
</html>