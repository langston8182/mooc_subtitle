<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>Insert title here</title>

<script type="text/javascript">
function bs_input_file() {
	$(".input-file").before(
		function() {
			if ( ! $(this).prev().hasClass('input-ghost') ) {
				var element = $("<input type='file' class='input-ghost' style='visibility:hidden; height:0'>");
				element.attr("name",$(this).attr("name"));
				element.change(function(){
					element.next(element).find('input').val((element.val()).split('\\').pop());
				});
				$(this).find("button.btn-choose").click(function(){
					element.click();
				});
				$(this).find("button.btn-reset").click(function(){
					element.val(null);
					$(this).parents(".input-file").find('input').val('');
				});
				$(this).find('input').css("cursor","pointer");
				$(this).find('input').mousedown(function() {
					$(this).parents('.input-file').prev().click();
					return false;
				});
				return element;
			}
		}
	);
}
$(function() {
	bs_input_file();
});
</script>
</head>

<body>
	<%@ include file="menu.jsp"%>

	<div class="container">
		<div class="jumbotron">
			<h2>Ajouter un fichier srt</h2>
			<br />
			<form method="POST" action="#" enctype="multipart/form-data"
				class="form-horizontal">
				<div class="form-group">
					<div class="input-group input-file" name="fichier">
						<span class="input-group-btn">
							<button class="btn btn-default btn-choose" type="button">Choisir</button>
						</span>
						<input type="text" class="form-control" placeholder="Choisissez un fichier ..." />
						<span class="input-group-btn">
							<button class="btn btn-warning btn-reset" type="button">Effacer</button>	
						</span>
					</div>
				</div>
				<div class="form-group">
					<button type="submit" class="btn btn-primary pull-right">Envoyer</button>
				</div>
			</form>
		</div>
	</div>
</body>
</html>