<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<h3>Archives (sous-titres) :</h3>
<ul class="archiveList">
	<c:if test="${ !empty sessionScope.archive.subtitles }" >
		<c:forEach items="${sessionScope.archive.subtitles}" var="sub" >
			<li><a href='subtitle?id=<c:out value="${sub.id}" />'> <c:out value="${sub.title}" /></a></li>
		</c:forEach>
	</c:if>
</ul>