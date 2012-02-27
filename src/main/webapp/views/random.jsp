<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<jsp:useBean id="pageModel" scope="request" type="se.atrosys.birds.model.PageModel" />
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
	<title>A simple game of identifying birds.</title>
	<link rel="stylesheet" type="text/css" href="../css/style.css"/>
	<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
	<script type="text/javascript" src="../js/script.js"></script>

	<script type="text/javascript" src="../audio-player/audio-player.js"></script>
	<script type="text/javascript">
		AudioPlayer.setup("../audio-player/player.swf", {
			width: 290
		});
	</script>

</head>
<body>
<%--TODO add button toolbar--%>
<%--<span id="toolbar" class="ui-widget-header ui-corner-all">
	<span id="media" class="ui-buttonset">
		<input type="radio" id="all" name="repeat" checked="checked" class="ui-helper-hidden-accessible">
			<label for="all" class="ui-button ui-widget ui-state-default ui-button-text-only ui-corner-left" aria-pressed="false" role="button" aria-disabled="false">
				<span class="ui-button-text">Både bilder och ljud</span>
			</label>
		<input type="radio" id="sound" name="repeat" class="ui-helper-hidden-accessible">
			<label for="sound" aria-pressed="false" class="ui-button ui-widget ui-state-default ui-button-text-only" role="button" aria-disabled="false">
				<span class="ui-button-text">Endast ljud</span>
			</label>
		<input type="radio" id="image" name="repeat" class="ui-helper-hidden-accessible">
			<label for="image" aria-pressed="true" class="ui-button ui-widget ui-state-default ui-button-text-only ui-corner-right ui-state-active" role="button" aria-disabled="false">
				<span class="ui-button-text">Endast bilder</span>
			</label>
	</span>
</span>--%>

<br/>

<c:choose>
	<c:when test="${pageModel.soundMedia}">
		<tags:audioplayer currentSound="${pageModel.currentSound}"/>
	</c:when>
	<c:when test="${pageModel.pictureMedia}">
		<img src="${pageModel.currentPhoto.url}"/><br/>
	</c:when>
	<c:otherwise>
		An error occurred, neither picture nor sound for the bird ${pageModel.birdModel.scientificName}.<br/>
		Please contact the author and report this error.
	</c:otherwise>
</c:choose>

<form:form commandName="ineligible" action="/ineligible/">
	<form:hidden path="mediaId"/>
	<form:hidden path="mediaType"/>
	<button id="ineligiblebutton" class="button-class" type="submit">Inte OK</button>
</form:form>

<form:form commandName="answer" id="answerform" action="/random/">
    <form:hidden path="id" />
	<ul>
	<c:forEach items="${pageModel.siblings}" var="sibling">
		<%--<div><form:radiobutton path="choice" id="${sibling.scientificName}" value="${sibling.scientificName}"/>${sibling.nameMap["Swedish"]}</div><br/>--%>
		<li id="${sibling.scientificNameUnderscore}"><form:radiobutton path="choice" value="${sibling.scientificNameUnderscore}"/>${sibling.nameMap["swedish"]}</li>
	</c:forEach>
	<li><button class="button-class" type="submit" id="answerbutton">Svara</button></li>
	</ul>
</form:form>
</body>
</html>