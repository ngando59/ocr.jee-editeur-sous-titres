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
	$().ready(function(){
		$("#myForm textarea").hide();
		$("#translateInput").hide();
		$("#titleInput").hide();
		$("#languageInput").hide();
		$("#edit").click(function() {
			$("#translate").removeClass("active");
			$(this).addClass("active");
			$("#h2Title").hide();
			$("#myForm textarea").hide();
			$("#myForm div.subBlock").hide();
			$("#translateInput").hide();
			$("#languageSpan").hide();
			$("#titleInput").show();
			$("#languageInput").show();
			$("#myForm textarea.editSub").show();
			$("#register").prop("disabled", false);
			$("#export").attr("onclick", "return false");
			$("#export").addClass("disabled");
		});
		$("#translate").click(function() {
			$("#edit").removeClass("active");
			$(this).addClass("active");
			$("#titleInput").hide();
			$("#myForm textarea").hide();
			$("#languageInput").hide();
			$("#h2Title").show();
			$("#languageSpan").show();
			$("#myForm div.subBlock").show();
			$("#translateInput").show();
			$("#myForm textarea.translateSub").show();
			$("#register").prop("disabled", true);
			$("#export").attr("onclick", "return false");
			$("#export").addClass("disabled");
		});
		$("#register").click(function() {
			  $("#myForm").submit();
		});
		$("#registerTranslation").click(function(){
			if($("#translateLanguageInput").val() == '') {
				alert("Veuillez entrer la langue de traduction !");	
				$("#translateLanguageInput").focus();
				return;
			} else {
				$.post("addTranslation", $("#myForm").serialize() );
				location.reload();
			}
		});
// 		$("#exportTranslation").click(function() {
// 			if($("#translateLanguageInput").val() == '') {
// 				$(this).attr("onclick", "return false");
// 				alert("Veuillez entrer la langue de traduction !");	
// 				$("#translateLanguageInput").focus();
// 			} else {
// 				$(this).attr("onclick", "return true");
// 			}
// 		});
	});
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
			<c:if test="${ !empty subtitle }">
				<form id="myForm" action="update" method="post">
				<input name="id" type="hidden" value="${ subtitle.id }">
					<nav class="row">
						<div class="col-md-6">
							<input id="titleInput" name="title" value ="${subtitle.title}" >
							<h2 id="h2Title">${subtitle.title}</h2>	
						</div>
						<div class="col-md-6 text-right">
							<button type="button" id="register" class="btn btn-info">Sauvegarder</button>
							<a id="export" class="btn btn-info" href="download?id=${ subtitle.id }">Exporter</a>
						</div>
					</nav>
					<div class="margin20">
						<span>Langue : </span><c:out value="${subtitle.language}" />
						<input id="languageInput" name="language" type="text" value="${subtitle.language}" />
						<c:if test="${subtitle.language == ''}">
							<em id="languageSpan">Aucune</em>
						</c:if>
					</div>
					<div class="margin20">
						<c:choose>
							<c:when test="${ empty subtitle.translations }">
								<span>Traduction(s) : </span>
								<em id="languageSpan">Aucune</em>
							</c:when>
							<c:otherwise>
								<ul class="transList">
									<li><span>Traduction(s) : </span></li>
									<c:forEach items="${ subtitle.translations }" var="transLang">
										<li>
											<a href="translate?id=${ transLang.id }"><c:out value="${ transLang.language }" /></a>
										</li>
									</c:forEach>
								</ul>
							</c:otherwise>
						</c:choose>
					</div>
					<nav class="margin20">
						<button type="button" id="edit" class="btn btn-info maringright10 ">Editer</button>
						<button type="button" id="translate" class="btn btn-info maringright10">Traduire</button>
						<br><br>
						<div id="translateInput">
							<label for="translateLanguage">Langue traduction :</label>&nbsp;<input id="translateLanguageInput" class="maringright5" name="translateLanguage" value="" type="text" />
							<button type="button" id="registerTranslation" class="btn btn-info maringright5">Enregistrer traduction</button>
							<!--  <a id="exportTranslation" class="btn btn-info maringright5" href="downloadTranslation" onclick="return false">Exporter traduction</a> -->
						</div>
					</nav>	
					<ul class="subtitleList">
						<c:forEach items="${subtitle.content.subtitles}" var="subBlock" >
				 			<li>
				 				<input name="idSub-<c:out value="${subBlock.id}" />" type="hidden" value="<c:out value="${subBlock.id}" />" >
				 				<c:out value="${subBlock.id}" /><br />
				 				<c:out value="${subBlock.begin} --> ${subBlock.end}" /><br />
				 				<input name="begin-<c:out value="${subBlock.id}" />" type="hidden" value="<c:out value="${subBlock.begin}" />">
				 				<input name="end-<c:out value="${subBlock.id}" />" type="hidden" value="<c:out value="${subBlock.end}" />">
				 				<div class="subBlock"><c:out value="${subBlock.content}" /><br /></div> 
				 				<textarea class="editSub" name="editSub-<c:out value="${subBlock.id}" />">${subBlock.content}</textarea>
				 				<textarea class="translateSub" name="translateSub-<c:out value="${subBlock.id}" />"></textarea>
				 			</li>
			 			</c:forEach>
					</ul>
				</form>
			</c:if>
		</section>
	</section>
</body>
</html>
