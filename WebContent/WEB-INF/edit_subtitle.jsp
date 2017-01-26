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
<title>Editer les sous-titres</title>
</head>

<body>
	<%@ include file="menu.jsp"%>

	<div class="container">
		<div class="jumbotron">
			<div class="list-group">
				<div class="list-group-item active">Liste des fichiers
					enregistrés</div>
				<c:forEach items="${ fichiers}" varStatus="status" var="fichier">
					<!-- <a href="export?id=${fichier.id }" class="btn btn-primary">Exporter</a>-->
					<div class="list-group-item clearfix">
						${fichier.nom } <span class="pull-right"> <span
							class="btn btn-xs btn-info"
							onclick='window.location.href="edit?id=${fichier.id}"'><c:out
									value="Editer" /></span> <span class="btn btn-xs btn-info"
							onclick='window.location.href="export?id=${fichier.id}"'>Exporter</span>
						</span>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>

	<c:if test="${!empty lignes }">
		<form method="post" action="#" class="form-horizontal">
			<p class="text-center">${fichier.nom }</p>
			<p class="text-center">
				<input type="submit" class="btn btn-success" />
			</p>
			<c:forEach items="${ lignes }" var="ligne" varStatus="status">
				<div class="form-group">
					<label class="control-label col-sm-5" for="ligne#${ligne.id }">
						<c:out value="${ligne.original } : " />
					</label>
					<div class="col-sm-5">
						<input type="text" name="ligne#${ligne.id }"
							id="ligne#${ligne.id }" value="${ligne.traduit }"
							class="form-control" />
					</div>
				</div>
			</c:forEach>
		</form>
	</c:if>
</body>
</html>