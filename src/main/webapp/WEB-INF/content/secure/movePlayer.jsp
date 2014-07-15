<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>


<script>
/*

$(document).ready(function() {

      alert("document ready occurred!");

});

$(window).load(function() {

      alert("window load occurred!");

});

*/



jQ(window).load(function() {

	var nation = jQ("#nation_id_move").val();
	
	divisionManager.listDivisionsByNation(nation, function(data) {

		jQ( "#division_id_move" ).empty();

		dwr.util.addOptions("division_id_move", data, "id", "name");

		var division = jQ("#division_id_move").val();

		teamManager.listTeamsByDivision(nation, division, function(data2) {
			jQ("#team_id_move").empty();
			dwr.util.addOptions("team_id_move", data2, "id", "name");
		});				

	});	

});	
	
	jQ(function() {	

		jQ("#nation_id_move").change(function() {
			var nation = jQ("#nation_id_move").val();
			
			divisionManager.listDivisionsByNation(nation, function(data) {
	
				jQ( "#division_id_move" ).empty();
	
				dwr.util.addOptions("division_id_move", data, "id", "name");
	
				var division = jQ("#division_id_move").val();
	
				teamManager.listTeamsByDivision(nation, division, function(data2) {
					jQ("#team_id_move").empty();
					dwr.util.addOptions("team_id_move", data2, "id", "name");
				});				
	
			});	
		});
		
		jQ("#division_id_move").change(function() {
			var nation = jQ("#nation_id_move").val();
			var division = jQ("#division_id_move").val();
	
			teamManager.listTeamsByDivision(nation, division, function(data2) {
				jQ("#team_id_move").empty();
				dwr.util.addOptions("team_id_move", data2, "id", "name");
			});
	
		});
		
	
	});
</script>	

<!-- edit player -->
<div id="dMovePlayer">
	
	<p class="validateTips"></p>

		<spring:url var="saveURL" value="/players/movePlayer.do" /> 
						
		<spring:url var="imageTURL" value="/teams/image.do"> 
		   <spring:param name="id" value="${player.team.id}"></spring:param> 
		</spring:url>			

		<form:form action="${saveURL}" enctype="multipart/form-data" id="movePlayer" method="POST" commandName="player">
		
				<form:hidden path="id" />
				<form:hidden path="team.nation.id" id="teamNationId" />
				<form:hidden path="team.division.id" id="teamDivisionId" />		
				<form:hidden path="teamOwner.id" id="teamOwnerId" />
				
				<input type="hidden" id="teamBeforeChange" value="${player.teamOwner.id}" />
									

		
				<!-- dati giocatore -->
				<div style="width:500px;">
					<p class="hl_startseite mt10">Scegli la nuova squadra di ${player.firstName} ${player.lastName}</p>
					<table cellspacing="1" cellpadding="2" style="margin-top:0;border:1px solid #ccc;" class="tabelle_grafik vcard">
					<tbody>
						<tr>
						<td class="al vt">
						<table cellspacing="1" cellpadding="0" class="tabelle_spieler s10">
							<tbody>
							<tr>
								<td style="width:130px;">Nazione:</td>
								<td class="fn n">
										<form:select id="nation_id_move" path="team.nation.id" items="${nationList}" itemLabel="name" itemValue="id" />									
								</td>
							</tr>
							<tr>
								<td>Categoria:</td>
								<td>
										<select id="division_id_move" name="division.id">
				              <c:forEach var="item" items="${divisionList}">
				                <option value="${item.id}">
				                  <c:out value="${item.name}" />
				                </option>
				              </c:forEach>
				            </select>
								</td>
							</tr>
							<tr>
								<td>Squadra:</td>
								<td>
									<form:select id="team_id_move" path="team.id" itemLabel="name" itemValue="id" onchange="changeTeam()"/>
								</td>
							</tr>
							<tr>
								<td>Prestito:</td>
								<td>
									<form:checkbox path="onLoan" id="onLoan" />
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