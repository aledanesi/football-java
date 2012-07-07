var jQ = jQuery.noConflict(); 
function
Progetto() { 
// FIELDS 
this.fadeTime = 400; 
this.slideTime = 250; 
}
// METHODS
Progetto.prototype = { 
// EFFECTS 
toggleHighlight : function(element, classPrefix) 
{ 
jQ(element).toggleClass(classPrefix + '_out'); 
jQ(element).toggleClass(classPrefix + '_over'); 
}, 
confirmDeleteTeam : function() 
{ 
return confirm("Vuoi davvero eliminare questa squadra?"); 
}, 
confirmDeletePlayer : function() 
{ 
return confirm("Vuoi davvero eliminare questo giocatore?"); 
} }
varproject = new Progetto(); 