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
		$("#edit").click(function() {
			$("#translate").removeClass("active");
			$(this).addClass("active");
			$("#myForm textarea").hide();
			$("#myForm div.subBlock").hide();
			$("#translateInput").hide();
			$("#myForm textarea.editSub").show();
		});
		$("#translate").click(function() {
			$("#edit").removeClass("active");
			$(this).addClass("active");
			$("#myForm textarea").hide();
			$("#myForm div.subBlock").show();
			$("#translateInput").show();
			$("#myForm textarea.translateSub").show();
		});
		$("#register").click(function() {
			if($("#languageInput").val() == '') {
				alert("Veuillez entrer la langue des sous-titres !");	
				$("#languageInput").focus();
				return;
			} else {	
				$("#myForm").submit();
				//location.reload();
			}
		});
		$("#export").click(function(){
			$.post("download", $("#myForm").serialize() );
			document.location.href="download";
		});
		$("#exportTranslate").click(function(){
			if($("#translateLanguage").val() == '') {
				alert("Veuillez entrer la langue de traduction des sous-titres !");	
				$("#translateLanguage").focus();
			} else {
				$.post("downloadCurrentTranslation", $("#myForm").serialize() );
				document.location.href="downloadCurrentTranslation";
			}
		});
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
			<c:choose>
				<c:when test="${ !empty uploadForm.subtitle }">
					<form id="myForm" action="register" method="post">
						<nav class="row">
							<div class="col-md-6">
								<input name="title" type="hidden" value ="${uploadForm.subtitle.title}" >
								<h2 class="paddingright10">${ uploadForm.subtitle.title }</h2>
							</div>
							<div class="col-md-6 text-right">
								<button type="button" id="register" class="btn btn-info">Sauvegarder</button>
								<a id="export" class="btn btn-info">Exporter</a>
							</div>
							<div class="margin20">
								<span>Langue : </span><c:out value="${subtitle.language}" />
								<input id="languageInput" name="language" type="text" value="${uploadForm.subtitle.language}" />
							</div>
						</nav>
						<nav class="margin20">
							<button type="button" id="edit" class="btn btn-info maringright10 ">Editer</button>
							<button type="button" id="translate" class="btn btn-info maringright10">Traduire</button>
							<br><br>
							<div id="translateInput">
								<label for="translateLanguage">Langue traduction :</label>&nbsp;<input id="translateLanguage" class="maringright5" name="translateLanguage" value="" type="text" />
								<a id="exportTranslate" class="btn btn-info maringright5">Exporter traduction</a>
							</div>
						</nav>	
						<ul class="subtitleList">
							<c:forEach items="${uploadForm.subtitle.content.subtitles}" var="subBlock" >
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
				</c:when>
				<c:otherwise>
					<form id="uplodForm" action="" method="post" class="center js" enctype="multipart/form-data">
						<c:if test="${ !empty uploadForm.messageError }">
							<p class="error">Format de fichier inconnu !</p>
						</c:if>	
						<h2>Uploadez un fichier (.srt)</h2>
						<div class="input-file-container">  
    						<input class="input-file" id="myfile" type="file" name="myfile" required />
    						<label tabindex="0" for="my-file" id="input-file-trigger" class="input-file-trigger">Choisir un fichier...</label>
  						</div>
  						<p id="file-return" class="file-return"></p>
  						<br>
  						<button type="submit">charger</button>
					</form>
				</c:otherwise>
			</c:choose>
		</section>
	</section>
</body>
</html>
