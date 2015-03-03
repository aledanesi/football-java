<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">

<link rel="stylesheet" type="text/css" href="/JFootball/styles/mystyle.css" rel="stylesheet" />
<link rel="stylesheet" type="text/css" href="/JFootball/styles/font-awesome-4.2.0/css/font-awesome.min.css" rel="stylesheet" />

<link type="text/css" href="/JFootball/scripts/jquery-ui-1.11.3/jquery-ui.css" rel="stylesheet" />
<script type="text/javascript" src="/JFootball/scripts/jquery-1.11.2/jquery.js"></script>
<script type="text/javascript" src="/JFootball/scripts/jquery-1.11.2/jquery.validate.js"></script>
<script type="text/javascript" src="/JFootball/scripts/jquery-ui-1.11.3/jquery-ui.js"></script>


<script>
var jQ = jQuery.noConflict();

jQ(function() {
	var progressbar = jQ( "#progressbar" ),
	progressLabel = jQ( ".progress-label" );
	progressbar.progressbar({
		value: false,
		change: function() {
		progressLabel.text( progressbar.progressbar( "value" ) + "%" );
		},
		complete: function() {
		jQ(window.location).attr('href', 'teams/list.do');
		}
	});
	function progress() {
		var val = progressbar.progressbar( "value" ) || 0;
		progressbar.progressbar( "value", val + 10 );
		if ( val < 99 ) {
			setTimeout( progress, 50 );
		}
	}
	setTimeout( progress, 2000 );
});
</script>
</head>
<body>
<div id="progressbar"><div class="progress-label">Loading...</div></div>

<div class="header">Benvenuto, ti sei appena loggato in JCampionato</div>

<div class="header_img"><img src="${pageContext.request.contextPath}/images/site/soccer-player.jpg"></div>
</body>
</html>