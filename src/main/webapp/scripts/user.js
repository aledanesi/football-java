var jQ = jQuery.noConflict();

jQ(function() {	
			
	jQ( "a.targetLinkUser" ).click(function() 
	{  		
		jQ("#userId").val(jQ(this).attr("data-team"));
		jQ("#viewUsersForm").submit();  
	});
	
			
	jQ("#dEditUser").dialog({
		title : "Modifica Utente",
		width : 550,
		height : 600,
		autoOpen : false,
		modal : true,
		resizable : true,
		autoResize : true,
		dialogClass : "dlg-no-close",
		buttons : {
			"Salva": function() {
				jQ( ".validateTips" ).show();

				var bValid = true;

				var username = jQ( "#username" );
				var password = jQ( "#password" );
				var allFields = jQ( [] ).add( username ).add( password );
				
				allFields.removeClass( "ui-state-error" );
				bValid = bValid && checkLength( username, "Username" );
				bValid = bValid && checkLength( password, "Password" );
				
				if ( bValid ) {
					jQ( ".validateTips" ).hide();
					jQ("#editUser").submit();
				}
			},
			"Annulla" : function() {
				cleanUser();
				
				jQ(this).dialog("close");
			}
		}
	});

	jQ("#dDeleteUser").dialog({
		resizable : false,
		height : 170,
		autoOpen : false,
		modal : true,
		buttons : {
			"Elimina" : function() {
				jQ(window.location).attr('href', jQ("#urlDeleteUser").val());
			},
			"Annulla" : function() {
				jQ(this).dialog("close");
			}
		}
	});

});

function User() {}

// METHODS
User.prototype = {

	confirmDeleteUser : function(urlDelete) {
		jQ("#urlDeleteUser").val(urlDelete);

		jQ('#dDeleteUser').dialog('open');
	},

	newUser : function() {

		jQ('#dEditUser').dialog('open');

		cleanTeam();

	},
	editUser : function(userId) {

		jQ('#dEditUser').dialog('open');

		cleanTeam();
			
		businessDelegate.getEntityByID(userId, "USER", function(data) {
			setTextByName('editUser', 'id', teamId);
			setTextByName('editUser', 'user', data.user);
			setTextByName('editUser', 'password', data.password);
		});
	}
};

var user = new User();

function cleanUser()
{
		var user = jQ( "#user" ),
		allFields = jQ( [] ).add( user );
	
		jQ( ".validateTips" ).hide();
		allFields.removeClass( "ui-state-error" );
		
		cleanTextByName('editUser', 'id');
		cleanTextByName('editUser', 'user');
		cleanTextByName('editUser', 'password');
}