var jQ = jQuery.noConflict();

jQ(function() {	
	
	jQ("#prestito").click(function()
	{
		controlloPrestito();
	});	

});

function Progetto() {
}

// METHODS
Progetto.prototype = {
	// EFFECTS
	toggleHighlight : function(element, classPrefix) {
		jQ(element).toggleClass(classPrefix + '_out');
		jQ(element).toggleClass(classPrefix + '_over');
	}

};

var project = new Progetto();

function dateToYMD(date) {
	var d = date.getDate();
	var m = date.getMonth() + 1;
	var y = date.getFullYear();
	return '' + (d <= 9 ? '0' + d : d) + '/' + (m <= 9 ? '0' + m : m) + '/' + y;
}

function sleep(milliseconds) {
  var start = new Date().getTime();
  for (var i = 0; i < 1e7; i++) {
    if ((new Date().getTime() - start) > milliseconds){
      break;
    }
  }
}


function checkOnCheckBox(form, field, isTrue)
{
		if (isTrue)
			jQ('#' + form + ' input[id=' + field + ']').prop("checked", true);
		else
			jQ('#' + form + ' input[id=' + field + ']').prop("checked", false);
}

function checkOnText(form, field, value)
{
		if (value != null)
			jQ('#' + form + ' input[id=' + field + ']').val(value);
		else
			jQ('#' + form + ' input[id=' + field + ']').val('?');
}

function checkOnSelectByVal(form, field, value)
{
		jQ('#' + form + ' select[id='+ field + '] option').each(function() {
			if (jQ(this).val() == value)
			{
					jQ(this).attr("selected", "selected");				
			}
		});
}

function checkOnSelectByText(form, field, value)
{			

		jQ('#' + form + ' select[id='+ field + '] option').each(function() {
			
			if (jQ(this).text() == value )
			{
				
				//jQ('#' + form + ' select[id='+ field + '] option:first').removeAttr("selected");			

				jQ(this).attr("selected", true);	
								
			}

		});
}

function setTextByName(form, field, value)
{
		jQ('#' + form + ' input[id=' + field +']').val(value);
}

function cleanTextByName(form, field)
{
		jQ('#' + form + ' input[id=' + field +']').val('');
}


function controlloPrestito()
{
	if(jQ('input[id=prestito]').is(':checked'))
	{
		jQ("#trPrestito").show();	
	}
	else 
	{
		jQ("#trPrestito").hide();	
	}
}

function bloccoListe(obj)
{
	if (obj.prop('checked'))
	{
		jQ("#nation_id").attr("disabled", "true");
		jQ("#division_id").attr("disabled", "true");
		jQ("#team_id").attr("disabled", "true");
	}
	else 
	{
		jQ("#nation_id").removeAttr("disabled");		
		jQ("#division_id").removeAttr("disabled");		
		jQ("#team_id").removeAttr("disabled");		
	}
}

function controlloPrestito()
{
	if(jQ('input[id=prestito]').is(':checked'))
	{
		jQ("#trPrestito").show();
	}					
	else 
	{
		jQ("#trPrestito").hide();		
	}
}

function formatDate(d, e) {
	var pK = e ? e.which : window.event.keyCode;
	if (pK == 8) {d.value = substr(0,d.value.length-1); return;}
	var dt = d.value;
	var da = dt.split('/');
	for (var a = 0; a < da.length; a++) {if (da[a] != +da[a]) da[a] = da[a].substr(0,da[a].length-1);}
	if (da[0] > 31) {da[1] = da[0].substr(da[0].length-1,1);da[0] = '0'+da[0].substr(0,da[0].length-1);}
	if (da[1] > 12) {da[2] = da[1].substr(da[1].length-1,1);da[1] = '0'+da[1].substr(0,da[1].length-1);}
	if (da[2] > 9999) da[1] = da[2].substr(0,da[2].length-1);
	dt = da.join('/');
	if (dt.length == 2 || dt.length == 5) dt += '/';
	d.value = dt;
}

function formatCurrency(obj) {
	var numero = "positivo";
	if (obj.val().substr(0,1)=="-") 
		numero = "negativo";
	var valore = obj.val().replace(/[^\d]/g,'').replace(/^0+/g,'');
	var nuovovalore='';
	while(valore.length>3){
		nuovovalore='.'+valore.substr(valore.length-3)+nuovovalore;
		valore = valore.substr(0,valore.length-3);
	}
	if (numero=="negativo")
		obj.val("-"+valore+nuovovalore);
	else 
		obj.val(valore+nuovovalore);
}


function caps(element){
    element.value = element.value.toUpperCase();
}

// usate per la validazione javascript
function updateTips( text ) {
	var tips = jQ( ".validateTips" );
	tips.text( text ).addClass( "ui-state-highlight" );
	setTimeout(function() {
		tips.removeClass( "ui-state-highlight", 1500 );
	}, 500 );
}

function checkLength( o, field ) {
	if ( o.val().length == 0 ) {
		o.addClass( "ui-state-error" );
		updateTips( "Campo '" + field + "' obbligatorio." );
		return false;
	} else {
		return true;
	}
}
