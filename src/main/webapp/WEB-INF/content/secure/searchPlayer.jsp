<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>

<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"    uri="http://www.springframework.org/security/tags"%>

<%@ taglib prefix="custom" uri="http://jfooball.it/functions"  %>

<script>
	String.prototype.capitalize = function() {
		var str = this.toLowerCase();

		return str.charAt(0).toUpperCase() + str.slice(1);
	}

	jQ(document).ready(function() {  
		jQ("#searchPlayerResults").hide();
	});		
</script>
 

	<!-- search player -->
	<div id="dSearchPlayer" style="display: none">
		
		<p class="validateTips"></p>
		
		<spring:url var="listByTeamURL" value="/players/listByTeam.do" />
			
		<spring:url var="viewURL" value="/players/view.do" />
		
		<form:form commandName="player" action="${viewURL}" id="searchPlayerPageForm" method="POST">
			<input type="hidden" name="id" id="id" />
		</form:form>			

		<form id="listByTeamFORM" action="${listByTeamURL}"  method="POST" >
			<input type="hidden" name="team.id" value="${team.id}" />
			<input type="hidden" name="teamCategory" value="1" />
		</form>

		<form action="#" id="formRicerca" onsubmit="player.execSearchPlayer(jQ('#iniziale').val(), '<spring:message code="searchPlayer.message10" />', '<spring:message code="searchPlayer.message11" />');return false;">
		
			<input type="hidden" name="team.id" id="teamId" value="${team.id}" />
			<input type="hidden" id="listByTeamURL" value="${listByTeamURL}" />
			<input type="hidden" id="teamIdTransfer" />			
			<input type="hidden" id="playerIdTransfer" />			
			<input type="hidden" id="isLoanTransfer" />		
			<input type="hidden" id="statusTransfer" />		
			
			<table class="standard_tabelle">
				<tr>
					<td>
						<table class="standard_tabelle">
						  <tr>
							  <th>
								<spring:message code="searchPlayer.message1" />
							  </th>
						  </tr>
						  <tr>
							  <td>
								 <spring:message code="searchPlayer.message2" />
							  </td>
						  </tr>
							</table>
							<table class="standard_tabelle">
						  <tr>
						
							<c:forEach items="${lettereRicerca}" var="obj">   
							  <td><a a href="#" onclick="player.execSearchPlayer('${obj}')">${obj}</a></td>
							</c:forEach>
						  
						  </tr>
						</table>
					</td>
				</tr>
				<tr>
					<td>
						<table class="standard_tabelle" style="margin-top: 15px">
						  <tr>
							  <th>
								<spring:message code="searchPlayer.message3" />
							  </th>
						  </tr>
						  <tr>
							  <td>
									<input type='radio' id="type" name="type" value='OTHERS' /><span style="padding: 5px"><spring:message code="searchPlayer.message4" /></span>
									<input type='radio' id="type" name="type" value='WITHOUT_TEAM' /><span style="padding: 5px"><spring:message code="searchPlayer.message5" /></span>
									<input type='radio' id="type" name="type" value='END_CAREER' /><span style="padding: 5px"><spring:message code="searchPlayer.message6" /></span>
									<input type='radio' id="type" name="type" value='ALL' /><span style="padding: 5px"><spring:message code="searchPlayer.message7" /></span>
							  </td>
						  </tr>
						  <tr>
							  <td>
									<input type='text' name="iniziale" id="iniziale" size='28' />
														
									<input type='button' value='<spring:message code="searchPlayer.message8" />' size='28' onclick="player.execSearchPlayer(jQ('#iniziale').val(), '<spring:message code="searchPlayer.message10" />', '<spring:message code="searchPlayer.message11" />');" />
									<input type='button' value='<spring:message code="searchPlayer.message9" />' size='28' onclick='jQ("#iniziale").val("")' />
							  </td>
						  </tr>
			
					</table>					
					</td>
				</tr>
				<tr>
					<td>
						<div id="searchPlayerResults">

						</div> 					
					</td>
				</tr>				
			</table>
   		</form>		

	</div>
	<!-- fine div -->