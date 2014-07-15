<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<spring:url var="saveURL" value="/careers/save.do" /> 

<spring:url var="imageTURL" value="/teams/image.do"> 
   <spring:param name="id" value="${player.team.id}"></spring:param> 
</spring:url>

<spring:url var="listURL" value="/players/view.do">
	<spring:param name="id" value="${career.player.id}" />
</spring:url>    

<script>
	function abilita()
	{
		if (jQ('#checkStagioni').is(':checked'))
		{
			jQ("#stagioni").removeAttr("readonly");
			jQ("#stagioni").css('background-color', "#ffffff");			

			jQ("#serie").attr("readonly", "true");
			jQ("#serie").css('background-color', "gray");						
			jQ("#serie").val("?");
			jQ("#presenze").attr("readonly", "true");
			jQ("#presenze").css('background-color', "gray");						
			jQ("#presenze").val("?");
			jQ("#reti").attr("readonly", "true");
			jQ("#reti").css('background-color', "gray");						
			jQ("#reti").val("?");
		}
		else 
		{
			jQ("#stagioni").attr("readonly", "true");
			jQ("#stagioni").css('background-color', "gray");						

			jQ("#serie").removeAttr("readonly");
			jQ("#serie").css('background-color', "#ffffff");			
			jQ("#presenze").removeAttr("readonly");
			jQ("#presenze").css('background-color', "#ffffff");			
			jQ("#reti").removeAttr("readonly");
			jQ("#reti").css('background-color', "#ffffff");			
		}
		
	}
</script>
	

	<!-- edit career -->
	<div id="dEditCareer">
		
		<p class="validateTips"></p> 
	
			<form:form action="${saveURL}" id="editCareer" method="post" style="width: 350px;" commandName="career"> 

			<input type="hidden" name="player.id" id="playerId" value="${career.player.id}"  />

			<input type="hidden" name="id" id="id" value="${career.id}" />			
			
				<!-- dati giocatore -->
				<div style="width:500px;">
					<p class="hl_startseite mt10">Inserisci un anno di carriera di ${player.firstName} ${player.lastName}</p>
					<table cellspacing="1" cellpadding="2" style="margin-top:0;border:1px solid #ccc;" class="tabelle_grafik vcard">
					<tbody>
						<tr>
						<td class="al vt">
						<table cellspacing="1" cellpadding="0" class="tabelle_spieler s10">
							<tbody>

							<tr>
								<td>Squadra:</td>
								<td>
									<form:input id="squadra" path="squadra" onkeyup="caps(this)" />
									<form:errors path="squadra" />
								</td>
							</tr>	
							<tr>
								<td colspan="2">&nbsp;</td>
							</tr>								
							<tr>
								<td>
									Stagioni:
								</td>
								<td>
									<input type="checkbox" id="checkStagioni" onclick="abilita()"> 									
									<form:input id="stagioni" path="stagioni" maxlength="2" style="width: 30px; background-color: gray" readonly="true" />
									<form:errors path="stagioni" />
								</td>
							</tr>								
							<tr>
								<td colspan="2">&nbsp;</td>
							</tr>								
							<tr>
								<td>Periodo:</td>
								<td>
									<form:input id="periodo" path="periodo" style="width: 60px" />
									<form:errors path="periodo" />
								</td>
							</tr>	
							<tr>
								<td colspan="2">&nbsp;</td>
							</tr>								
							<tr>
								<td>Serie:</td>
								<td>
									<form:input id="serie" path="serie" onkeyup="caps(this)" style="width: 30px" />
									<form:errors path="serie" />
								</td>
							</tr>	
							<tr>
								<td colspan="2">&nbsp;</td>
							</tr>								
							<tr>
								<td>Presenze:</td>
								<td>
									<form:input id="presenze" path="presenze" maxlength="3" style="width: 30px" />
									<form:errors path="presenze" />
								</td>
							</tr>	
							<tr>
								<td colspan="2">&nbsp;</td>
							</tr>								
							<tr>
								<td>Reti:</td>
								<td>
									<form:input id="reti" path="reti" maxlength="4" style="width: 30px" />
									<form:errors path="reti" />
								</td>
							</tr>	
							<tr>
								<td colspan="2">&nbsp;</td>
							</tr>								
							<tr>
								<td>Prestito:</td>
								<td>
									<form:checkbox path="onLoan" id="prestito" />
								</td>
							</tr>	
							</tbody>
							</table>
						</td>
						</tr>
						</tbody>
					</table>
				</div>			
			
		</form:form>
	</div>
	<!-- fine div -->