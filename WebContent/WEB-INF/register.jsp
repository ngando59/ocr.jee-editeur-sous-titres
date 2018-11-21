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

</head>
<body>
	<jsp:include page="header.jsp" />
	<section id="bigWrapper" class="container row">
		<button onclick="topFunction()" id="myBtn" title="Go to top">Haut</button>
		<aside class="archive col-xs-12 col-md-3">
			<jsp:include page="archive.jsp" />
		</aside>
		<section class="wrapper col-xs-12 col-md-9">	
			<c:if test="${ !empty registerForm.subtitle }">
				<h2>${ registerForm.subtitle.title } est maintenant enregistré dans la bdd !</h2>
				<ul class="subtitleList">
					<c:forEach items="${registerForm.subtitle.content.subtitles}" var="subBlock" >
			 			<li>
			 				<c:out value="${subBlock.id}" /><br />
			 				<c:out value="${subBlock.begin} --> ${subBlock.end}" /><br />
			 				<div class="subBlock"><c:out value="${subBlock.content}" /><br /></div> 
			 			</li>
		 			</c:forEach>
				</ul>
			</c:if>
		</section>
	</section>
</body>
</html>
