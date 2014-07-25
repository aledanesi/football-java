<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
 

	<!-- edit player -->
	<div id="dEditPlayer">
		
		<p class="validateTips"></p>
		
		<spring:url var="saveURL" value="/players/save.do" /> 
	
		<form:form commandName="player" id="editPlayer" action="${saveURL}" enctype="multipart/form-data" method="POST" style="width: 450px;">
			<form:hidden path="id"/>
			<form:hidden path="image" />		
			<input type="hidden" id="team_id" name="team.id" value="${team.id}" />			
			<input type="hidden" id="team_nation_id" name="team.nation.id" value="${team.nation.id}" />
			<input type="hidden" id="team_division_id" name="team.division.id" value="${team.division.id}" />		
			<input type="hidden" id="team_prev_id" name="teamPrev.id" value="${teamPrev.id}" />		

			<input type="hidden" id="endCareer" name="endCareer" value="${endCareer}" />
			<input type="hidden" id="withoutTeam" name="withoutTeam" value="${withoutTeam}" />						
	
			
			<table style="width: 650px;">
				
				<tr>
					<td style="text-align: center">
						<spring:url var="imageURL" value="/players/image.do" />
						<input type="hidden" value="${imageURL}" name="urlImage" />
						<img src="" id="myImage" style="width: 58px; padding: 0px; border-color: #bbb; border-width: 1px; border-style: solid; background-color: rgb(255, 255, 255);" />
					</td>
					<td>				  
					  <div class="fitem_label"><spring:message code="photo" />:</div>
					  <div class="fitem_span"><form:input path="image" type="file" /></div>
					</td>
					<td>
						  <div class="fitem_label">Capitano:</div>
						  <div class="fitem_span">
								<form:checkbox path="captain" id="captain" />
						  </div>
					</td>					
					<td>
						  <div class="fitem_label">Prestito:</div>
						  <div class="fitem_span">
								<form:checkbox path="onLoan" id="prestito" />
						  </div>						  
					</td>					
				</tr>					

				<tr>
					<td>
					  <div class="fitem_label"><spring:message code="firstName" />:</div>
					  <div class="fitem_span">
							<form:input path="firstName" />
							<form:errors path="firstName" />
					  </div>						
					</td>
					<td>				  
						<div class="fitem_label"><spring:message code="lastName" />:</div>
					  <div class="fitem_span">
							<form:input path="lastName" />
							<form:errors path="lastName" />
					  </div>
					</td>
					<td colspan="2">
					  <div class="fitem_label">Nome completo:</div>
					  <div class="fitem_span">
							<form:input path="completeName" style="width: 200px" />
							<form:errors path="completeName" />
					  </div>						
					</td>
				</tr>					

				<tr>
					<td>				  
					  <div class="fitem_label"><spring:message code="birthDate" />:</div>
					  <div class="fitem_span">
							<fmt:formatDate var="f_BirthDate" value="${player.birthDate}" type="both" pattern="dd/MM/yyyy" />	
							<form:input path="birthDate" value="${f_BirthDate}" style="width: 80px" maxlength="10" onkeyup="formatDate(this, event)" />
							<form:errors path="birthDate" />
					  </div>
					</td>
					<td>
					  <div class="fitem_label"><spring:message code="birthPlace" />:</div>
					  <div class="fitem_span">
							<form:input path="birthPlace" />
							<form:errors path="birthPlace" />
					  </div>
					</td>
					<td>
					  <div class="fitem_label">Categoria squadra:</div>
					  <div class="fitem_span">
					  		<form:select path="teamCategory">
					  			<form:option label="Prima squadra" value="1" />
					  			<form:option label="Primavera" value="2" />
					  			<form:option label="Giovanili" value="3" />
					  			<form:option label="Altri giocatori" value="4" />							  				 
					  		</form:select>	
					  </div>
					</td>
				</tr>					

				<tr>
					<td>
					  <div class="fitem_label">Nazionalita 1:</div>
					  <div class="fitem_span">
						  <form:select path="nation.id" id="nation_id">
						      <form:option  value="" />
						      <form:options items="${nationList}" itemValue="id" itemLabel="name"/>
							</form:select>
					  </div>
					</td>
					<td>				  
					  <div class="fitem_label">Nazionalita 2:</div>
					  <div class="fitem_span">
						  <form:select path="nation2.id" id="nation2_id">
						      <form:option  value="" />
						      <form:options items="${nationList}" itemValue="id" itemLabel="name"/>
							</form:select>
					  </div>
					</td>
					<td>
					  <div class="fitem_label">Nazionale:</div>
					  <div class="fitem_span">
					  		<form:select path="national">
					  			<form:option label="Attualmente no" value="0" />
					  			<form:option label="Naz. U18" value="1" />
					  			<form:option label="Naz. U19" value="2" />
					  			<form:option label="Naz. U20" value="3" />
					  			<form:option label="Naz. U21" value="4" />
					  			<form:option label="Naz. Maggiore" value="5" />
					  			<form:option label="Ritirato" value="6" />
					  		</form:select>	
					  </div>
					</td>
				</tr>					

				<tr>
					<td>
					  <div class="fitem_label"><spring:message code="height" />:</div>
					  <div class="fitem_span">
							<form:input path="height" style="width: 30px" maxlength="3" />
							<form:errors path="height" />
					  </div>
					</td>
					<td>				  
					  <div class="fitem_label"><spring:message code="weight" />:</div>
					  <div class="fitem_span">
							<form:input path="weight" style="width: 30px" maxlength="3" />
							<form:errors path="weight" />
					  </div>
					</td>
					<td>
					  <div class="fitem_label">Numero:</div>
					  <div class="fitem_span">
							<form:input path="number" style="width: 20px" maxlength="2" />
							<form:errors path="number" />
					  </div>
					</td>
				</tr>					

				<tr>
					<td>
							  <div class="fitem_label"><spring:message code="position" />:</div>
							  <div class="fitem_span">
							  		<select id="ruolo_id">
											<option value="01">Porta</option>
											<option value="02">Difesa</option>
											<option value="03">Centrocampo</option>
											<option value="04">Attacco</option>
										</select>
							  </div>
					</td>
					<td>				  
							  <div class="fitem_label">Posizione:</div>
							  <div class="fitem_span">
							  		<form:select path="position.id" id="position_id" />
							  </div>
					</td>
					<td>
							  <div class="fitem_label">Piede:</div>
							  <div class="fitem_span">
							  		<form:select path="foot">
							  			<form:option value="" />
							  			<form:option value="destro" />
							  			<form:option value="sinistro" />
							  			<form:option value="entrambi" />							  				 
							  		</form:select>	
							  </div>
					</td>
				</tr>					
												
				<tr>
					<td>				  
					  <div class="fitem_label">Scadenza contratto:</div>
					  <div class="fitem_span">
							<form:input path="dateContract" style="width: 80px" maxlength="10" onkeyup="formatDate(this, event)" />
							<form:errors path="dateContract" />
					  </div>
					</td>
					<td>
					  <div class="fitem_label">Ingaggio:</div>
					  <div class="fitem_span">
							<form:input path="income" style="width: 80px" onkeyup="formatCurrency(jQ(this))" />
							<form:errors path="income" />
					  </div>
					</td>
					<td>
					  <div class="fitem_label">Valore:</div>
					  <div class="fitem_span">
							<form:input path="value" style="width: 80px" onkeyup="formatCurrency(jQ(this))"  />
							<form:errors path="value" />
					  </div>
					</td>
				</tr>		
				
				<tr id="trPrestito" style="display: none; border: 1px; color: red; font-weight: bold;">
					<td>
					  <div class="fitem_label">Nazione:</div>
					  <div class="fitem_span">
						<form:select id="owner_nation_id" path="teamOwner.nation.id" items="${nationList}" itemLabel="name" itemValue="id" />
					  </div>
					</td>
					<td>
					  <div class="fitem_label">Categoria:</div>
					  <div class="fitem_span">
			            <select id="owner_division_id" name="teamOwner.division.id">
			              <c:forEach var="item" items="${divisionList}">
			                <option value="${item.id}">
			                  <c:out value="${item.name}" />
			                </option>
			              </c:forEach>
			            </select>                      	
					  </div>
					</td>
					<td>				  
					  <div class="fitem_label">Squadra:</div>
					  <div class="fitem_span">
						<form:select id="owner_team_id" path="teamOwner.id" items="${teamList}" itemLabel="name" itemValue="id" />
					  </div>
					</td>
				</tr>
							

				
			</div>					


			</table>	
		</form:form>
	</div>
	<!-- fine div -->