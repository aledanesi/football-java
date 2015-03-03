<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<%@ taglib prefix="custom" uri="http://jfooball.it/functions"  %>

<script>
	String.prototype.capitalize = function() {
		var str = this.toLowerCase();

		return str.charAt(0).toUpperCase() + str.slice(1);
	}

	jQ(document).ready(function() {     
		jQ("#buyStaffResults").hide();
	});	

	function searchBuyStaff()
	{
		var letteraRicerca = jQ("#iniziale").val();

		if (letteraRicerca != '')
		{
			businessDelegate.getEntitiesByParams(letteraRicerca, "ALL", "STAFF", function (data)
			{	
			
				var str = "<table class=\"standard_tabelle\" style=\"width: 730px\"><tr><th colspan=\"3\">Risultati ottenuti:</th></tr><tr><th colspan=\"3\">Clicca su un nome per acquistare un membro dello staff:</th></tr>";

				
				for (var i = 0; i < data.length; i++) {
					
					if (i % 2 == 0)
						str += "<tr class='even'>";
					else 
						str += "<tr class='odd'>";	
						
					str += "<td class=\"bg2 alle\">" + data[i].lastName + " " + data[i].firstName + " (" + data[i].position.descPosizione;
					
					if (data[i].team != null){
						str += " - " + data[i].team.name.capitalize() + ") ";
					}
					else 
					{
						str += ") ";
					}					
					
					str += "nato il " + jQ.datepicker.formatDate("dd/mm/yy", data[i].dateOfBirth) + " a " + data[i].placeOfBirth;
					str += "</td>";

					if (data[i].team != null && data[i].team.id != jQ("#teamId").val())
					{
						str += "<td class=\"bg2 alle\" style=\"text-align: center\"><a href=\"#\" onclick='acquistaStaff(" + data[i].id + ", " + jQ("#teamId").val() + ", false);'>Acquisto</a></td>";				
						str += "<td class=\"bg2 alle\" style=\"text-align: center\"><a href=\"#\" onclick='acquistaStaff(" + data[i].id + ", " + jQ("#teamId").val() + ", true);'>Prestito</a></td>";				
					}
					else 
					{
						str += "<td class=\"bg2 alle\" style=\"text-align: center\">&nbsp;</td>";				
						str += "<td class=\"bg2 alle\" style=\"text-align: center\">&nbsp;</td>";									
					}
				}
				
				str += "</tr></table>"
				
				jQ("#buyStaffResults").html(str);
				
				jQ("#buyStaffResults").show();				
						
			});	
		}
		else 
		{
			alert("Inserire il membro dello staff da trovare...");
			jQ("#buyStaffResults").hide();	
		}		
	}
	
	function acquistaStaff(staffId, teamId, isLoan)
	{	
		jQ("#staffIdTransfer").val(staffId);
		jQ("#teamIdTransfer").val(teamId);
		jQ("#isLoanTransfer").val(isLoan);		
	
		staff.confirmBuyStaff();
	}
</script>
 

	<!-- buy staff -->
	<div id="dBuyStaff" style="display: none">
		
		<p class="validateTips"></p>
		
		<spring:url var="listByTeamURL" value="/players/listByTeam.do" />

		<spring:url var="buyURL" value="/staffs/buy.do" />
		
		<form id="listByTeamFORM" action="${listByTeamURL}"  method="POST">
			<input type="hidden" name="team.id" value="${team.id}" />
			<input type="hidden" name="teamCategory" value="1" />
		</form>

		<form action="#" id="formRicerca" onsubmit="searchBuyStaff();return false;">
   
			<input type="hidden" name="team.id" id="teamId" value="${team.id}" />
			<input type="hidden" id="listByTeamURL" value="${listByTeamURL}" />
			<input type="hidden" id="teamIdTransfer" />			
			<input type="hidden" id="playerIdTransfer" />			
			<input type="hidden" id="isLoanTransfer" />			
   
			<table class="standard_tabelle" style="width: 730px">
			  <tr>
				  <th>
				  Scegli il manager da acquistare
				  </th>
			  </tr>
			  <tr>
				  <td>
				  Seleziona l'iniziale del cognome:
				  </td>
			  </tr>
			</table>
			<table class="standard_tabelle" style="width: 730px">
				  <tr>
					  <th>
					  Ricerca libera: inserisci le lettere iniziali o il cognome intero
					  </th>
				  </tr>
				  <tr>
					  <td>
							<input type="text" name="iniziale" id="iniziale" size='28' />
												
							<input type='button' value='Cerca' size='28' onclick='searchBuyStaff();' />
							<input type='button' value='Reset' size='28' onclick='jQ("#iniziale").val("")' />
					  </td>
				  </tr>

			</table>
   
			<div id="buyStaffResults">

				<table class="standard_tabelle" style="width: 730px">
					  <tr>
						<th colspan="3">Risultati ottenuti:</th>
					  </tr>
					  <tr>
						<th colspan="3">Clicca su un nome per acquistare il giocatore:</th>
					  </tr>
								
			  </table>
			</div> 
		</form>		

	</div>
	<!-- fine div -->