<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
  pageEncoding="ISO-8859-1"%>

<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>### editCareer.jsp ###</title>

<s:head />

<link rel="stylesheet" href="styles/Envision.css" type="text/css" />
<link rel="stylesheet" type="text/css" href="styles/jquery/jquery-ui.css" />


<%-- JQUERY --%>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery/jquery-1.7.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery/ui/jquery-ui.js"></script> 

<script type='text/javascript' src='dwr/engine.js'></script>
<script type='text/javascript' src='dwr/util.js'></script>
<script type='text/javascript' src='dwr/interface/teamManager.js'></script>
<script type='text/javascript' src='dwr/interface/divisionManager.js'></script>
 
    
<%-- APPLICATION SPECIFIC --%>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/script.js"></script>  

<script language="Javascript">
var $ = jQuery.noConflict();
             
$(document).ready(function()
{ 
  $('#firstName').focus();
});

function autoTeam()
{
  var query = $("#team").val();
  teamManager.listTeamsByQuery(query, loadData);  
}

function loadData(data)
{
  var teams = new Array(); //viene creato l'array
  var size = data.length;
  for (var i = 0; i < size; i++)
  {
    var arr = {"label":data[i].name, "id":data[i].id};
    teams[i] = arr;    
  }  
  $('#team').autocomplete({ source: teams, select: function (event, ui) 
    {  
      var id = ui.item.id;
      $('#teamId').val(id);
    } 
  });
  changeDivision();  
}

function autoOwner()
{
  var query = $("#teamOwner").val();
  teamManager.listTeamsByQuery(query, loadOwner);  
}

function loadOwner(data)
{
  var teams = new Array(); //viene creato l'array
  var size = data.length;
  for (var i = 0; i < size; i++)
  {
    var arr = {"label":data[i].name, "id":data[i].id};
    teams[i] = arr;    
  }  
  $('#teamOwner').autocomplete({ source: teams, select: function (event, ui) 
    {  
      var id = ui.item.id;
      $('#teamOwnerId').val(id);
    }
  });  
  $("#team").val($('#teamOwner').val());
  $('#teamId').val($('#teamOwnerId').val());
  autoTeam();
}

function changeDivision()
{
  
  var teamId = $("#teamId").val();
  var seasonYearId = $("#seasonYearId").val();
  
  divisionManager.getDivision(teamId, seasonYearId, loadDivision);
}

function loadDivision(data)
{
  if (data != null)
  {
    var division = data.name;
    $("#serie").val(division);    
  }
  else 
  {
    $("#serie").val('');
  }
} 
</script>

</head>
<body>
  
<s:form action="saveCareer" enctype="multipart/form-data" method="POST" style="width: 350px;"> 
  <s:push value="career">
  <input type="hidden" name="player.id" value="${playerId}" />

    <div id="header2">
        <div id="menutop">
            <ul>                                 
                <li>
            <s:url id="listURL" action="viewPlayer">
              <s:param name="id">${playerId}</s:param>
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
           Inserisci anno di carriera
        </p></td>
      </tr>
            
    <tr>
      <s:if test="%{image != null}">
            <td colspan="2" style="text-align: center"><img src="<s:url action="getDynamicImageCareer"><s:param name="id">${career.id}</s:param></s:url>"></td>       
      </s:if>  
      <s:if test="%{image == null}">
          <td colspan="2" style="text-align: center">
            <img src="${pageContext.request.contextPath}/images/players/notFound.jpg" alt="Foto di ${lastName}" />
          </td>  
      </s:if>                       
    </tr>
    
    <s:hidden name="id" />
    
    <s:file name="userImage" label="Foto" key="photo" size="15" />       
                    
    <s:select name="stagione.id" id="seasonYearId" list="seasonYearList" listKey="id" listValue="value" key="season" onchange="changeDivision()" />

    <s:textfield name="teamOwner.name" id="teamOwner" key="teamOwner" onkeyup="autoOwner();" />                
    <s:textfield name="team.name" id="team" key="team" onkeyup="autoTeam();" onblur="changeDivision()" />
    <s:hidden name="teamOwner.id" id="teamOwnerId" />
    <s:hidden name="team.id" id="teamId" />              
    
    <s:textfield name="monthTransfer" id="monthTransfer" />
    <s:textfield name="serie" id="serie" key="division" />  
    <s:textfield name="dettaglioCarriera.partiteGiocate" id="dettaglioCarriera.partiteGiocate" key="playedGames" size="4" />    

    <s:textfield name="goal" id="goal" key="goal" size="4" />                     
        
  </s:push>      

</s:form>                                

</body>
</html>