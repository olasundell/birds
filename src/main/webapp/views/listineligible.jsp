<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<jsp:useBean id="ieModel" scope="request" type="se.atrosys.birds.web.model.IneligibleModel"/>

<html>
<head>
	<title></title>
	<link rel="stylesheet" type="text/css" href="/css/style.css"/>
	<script type="text/javascript" src="../audio-player/audio-player.js"></script>
	<script type="text/javascript">
		AudioPlayer.setup("../audio-player/player.swf", {
			width: 290
		});
	</script>
</head>
<body>

<ul>
	<c:forEach items="${ieModel.photos}" var="photo">
		<li>
			<form:form commandName="ineligible" action="/listineligible/" method="POST">
				<input type="hidden" value="${photo.id}" name="mediaId"/>
				<input type="hidden" value="${photo.type}" name="mediaType"/>
				<img src="${photo.url}">${photo.title}<br/>
				<button class="button-class" type="submit" id="${photo.id}">Sätt som OK</button>
			</form:form>
		</li>
	</c:forEach>
</ul>

<ul>
	<c:forEach items="${ieModel.sounds}" var="sound" >
		<li>
			<form:form commandName="ineligible" action="/listineligible/" method="POST">
				<input type="hidden" value="${sound.id}" name="mediaId"/>
				<input type="hidden" value="${sound.type}" name="mediaType"/>
				<button class="button-class" type="submit" id="${sound.id}">Sätt som OK</button>
				<tags:audioplayer currentSound="${sound}" />
			</form:form>
		</li>
	</c:forEach>
</ul>

</body>
</html>