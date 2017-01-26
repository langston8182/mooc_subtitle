<nav class="navbar navbar-default">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">Subtitles Web Site</a>
    </div>
    <ul class="nav navbar-nav">
      <li class="${pageContext.request.requestURI eq '/Subtitlor/WEB-INF/accueil.jsp' ?  'active' : ''}"><a href="accueil">Home</a></li>
      <li class="${pageContext.request.requestURI eq '/Subtitlor/WEB-INF/ajouter.jsp' ?  'active' : ''}"><a href="ajouter">Ajouter</a></li>
      <li class="${pageContext.request.requestURI eq '/Subtitlor/WEB-INF/edit_subtitle.jsp' ?  'active' : ''}"><a href="edit">Editer</a></li>
    </ul>
  </div>
</nav>
<%@ page pageEncoding="UTF-8"%>
<c:if test="${!empty erreur }">
	<div class="alert alert-danger alert-dismissable">
		<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
		${erreur }
	</div>
</c:if>