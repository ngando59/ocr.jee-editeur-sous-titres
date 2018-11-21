<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Subtitle Editor 1.0</title>
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/style.css">
<script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/upload.js"></script>
<script type="text/javascript">
	window.onscroll = function() {scrollFunction()};
	function scrollFunction() {
	    if (document.body.scrollTop > 20 || document.documentElement.scrollTop > 20) {
	        document.getElementById("myBtn").style.display = "block";
	    } else {
	        document.getElementById("myBtn").style.display = "none";
	    }
	}
	function topFunction() {
	    document.body.scrollTop = 0;
	    document.documentElement.scrollTop = 0;
	}
</script>
</head>
<body>
	<jsp:include page="header.jsp" />
	<section id="bigWrapper" class="container row">
		<button onclick="topFunction()" id="myBtn" title="Go to top">Haut</button>
		<aside class="archive col-xs-12 col-md-3">
			 <jsp:include page="archive.jsp" />
		</aside>
		<section class="wrapper col-xs-12 col-md-9">	
			<c:if test="${ !empty translate }">
				<br>
				<u>Sous-titres original :</u>&nbsp;&nbsp;<a href="subtitle?id=${ translate.idSubtitle }" class="tt"><c:out value="${ origin.title }" /></a>
				<form id="myForm" action="updateTranslate" method="post">
					<input name="id" type="hidden" value="${ translate.id }">
					<div class="margin20">
						<span>Langue : </span>
						<input id="languageInput" name="language" type="text" value="${translate.language}" />
					</div>
					<div class="margin20">
						<button type="submit" id="register" class="btn btn-info">Sauvegarder</button>
						<a id="export" class="btn btn-info" href="downloadTranslation?id=${ translate.id }">Exporter</a>
					</div>
					<ul class="subtitleList">
						<c:forEach items="${translate.content.subtitles}" var="subBlock" >
				 			<li>
				 				<input name="idSub-<c:out value="${subBlock.id}" />" type="hidden" value="<c:out value="${subBlock.id}" />" >
				 				<c:out value="${subBlock.id}" /><br />
				 				<c:out value="${subBlock.begin} --> ${subBlock.end}" /><br />
				 				<input name="begin-<c:out value="${subBlock.id}" />" type="hidden" value="<c:out value="${subBlock.begin}" />">
				 				<input name="end-<c:out value="${subBlock.id}" />" type="hidden" value="<c:out value="${subBlock.end}" />">
				 				<div class="subBlock"><span class="orange">Original : </span><em><c:out value="${origin.content.getSubtitleBlockById(subBlock.id).content}" /></em><br /></div> 
				 				<textarea class="editSub" name="editSub-<c:out value="${subBlock.id}" />">${subBlock.content}</textarea>
				 			</li>
			 			</c:forEach>
					</ul>
				</form>
			</c:if>
		</section>
	</section>
</body>
</html>
