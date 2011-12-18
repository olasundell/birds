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
<c:choose>
	<c:when test="${pageModel.soundMedia}">
		<audio controls="controls">
			<source src="${pageModel.sound.URL}" type="${pageModel.sound.type}" />
			Your browser does not support the audio element.
		</audio>
	</c:when>
	<c:when test="${pageModel.pictureMedia}">
		<img src="${pageModel.birdModel.photos[0].url}"/><br/>
	</c:when>
	<c:otherwise>
		An error occurred, neither picture nor sound for the bird ${pageModel.birdModel.scientificName}.<br/>
		Please contact the author and report this error.
	</c:otherwise>
</c:choose>
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