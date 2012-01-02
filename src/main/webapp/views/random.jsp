<%--<jsp:useBean id="birdModel" scope="request" type="se.atrosys.birds.model.BirdModel"/>--%>
<jsp:useBean id="pageModel" scope="request" type="se.atrosys.birds.model.PageModel" />
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
	<title>A random bird.</title>
	<link rel="stylesheet" type="text/css" href="/css/style.css"/>
	<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
	<script type="text/javascript" src="/js/script.js"></script>
</head>
<body>
<%--TODO add button toolbar--%>
<span id="toolbar" class="ui-widget-header ui-corner-all">
	<button id="beginning" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-icon-only" role="button" aria-disabled="false" title="go to beginning"><span class="ui-button-icon-primary ui-icon ui-icon-seek-start"></span><span class="ui-button-text">go to beginning</span></button>
	<button id="rewind" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-icon-only" role="button" aria-disabled="false" title="rewind"><span class="ui-button-icon-primary ui-icon ui-icon-seek-prev"></span><span class="ui-button-text">rewind</span></button>
	<button id="play" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-icon-only" role="button" aria-disabled="false" title="play"><span class="ui-button-icon-primary ui-icon ui-icon-play"></span><span class="ui-button-text">play</span></button>
	<button id="stop" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-icon-only" role="button" aria-disabled="false" title="stop"><span class="ui-button-icon-primary ui-icon ui-icon-stop"></span><span class="ui-button-text">stop</span></button>
	<button id="forward" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-icon-only" role="button" aria-disabled="false" title="fast forward"><span class="ui-button-icon-primary ui-icon ui-icon-seek-next"></span><span class="ui-button-text">fast forward</span></button>
	<button id="end" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-icon-only" role="button" aria-disabled="false" title="go to end"><span class="ui-button-icon-primary ui-icon ui-icon-seek-end"></span><span class="ui-button-text">go to end</span></button>

	<input type="checkbox" id="shuffle" class="ui-helper-hidden-accessible"><label for="shuffle" aria-pressed="true" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only ui-state-active" role="button" aria-disabled="false"><span class="ui-button-text">Shuffle</span></label>

	<span id="repeat" class="ui-buttonset">
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
</span>

<c:choose>
	<c:when test="${pageModel.soundMedia}">
		<audio controls autoplay autobuffer>
			<%--<source src="${pageModel.sound.URL}" type="${pageModel.sound.type}" />--%>
			<source src="${pageModel.currentSound.URL}" />
			Your browser does not support the audio element.
		</audio>
	</c:when>
	<c:when test="${pageModel.pictureMedia}">
		<img src="${pageModel.currentPhoto.url}"/><br/>
	</c:when>
	<c:otherwise>
		An error occurred, neither picture nor sound for the bird ${pageModel.birdModel.scientificName}.<br/>
		Please contact the author and report this error.
	</c:otherwise>
</c:choose>
<form:form commandName="ineligible">
	<form:hidden path="mediaId"/>
	<button class="button-class" type="submit">Inte OK</button>
</form:form>
<%--Group: ${pageModel.birdModel.family.group}<br/>
Family: ${pageModel.birdModel.family.family}<br/>
Name: ${pageModel.birdModel.scientificName}<br/>
<c:forEach items="${pageModel.birdModel.names}" var="name">
${name.lang}: ${name.name}<br/>
</c:forEach>--%>
<form:form commandName="answer" id="answerform">
    <form:hidden path="id" />
	<ul>
	<c:forEach items="${pageModel.siblings}" var="sibling">
		<%--<div><form:radiobutton path="choice" id="${sibling.scientificName}" value="${sibling.scientificName}"/>${sibling.nameMap["Swedish"]}</div><br/>--%>
		<li id="${sibling.scientificNameUnderscore}"><form:radiobutton path="choice" value="${sibling.scientificNameUnderscore}"/>${sibling.nameMap["Swedish"]}</li>
	</c:forEach>
	<li><button class="button-class" type="submit" id="answerbutton">Svara</button></li>
	</ul>
</form:form>
</body>
</html>