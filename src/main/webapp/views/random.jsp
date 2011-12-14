<%--<jsp:useBean id="birdModel" scope="request" type="se.atrosys.birds.model.BirdModel"/>--%>
<jsp:useBean id="pageModel" scope="request" type="se.atrosys.birds.model.PageModel" />
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>A random bird.</title>
    <link rel="stylesheet" type="text/css" href="/css/style.css"/>
</head>
<body>

<c:if test="${not empty pageModel.previousAnswer}">
<h1>${pageModel.previousAnswer}</h1><br/>
</c:if>

<img src="${pageModel.birdModel.photos[0].url}"/><br/>
<%--Group: ${pageModel.birdModel.family.group}<br/>
Family: ${pageModel.birdModel.family.family}<br/>
Name: ${pageModel.birdModel.scientificName}<br/>
<c:forEach items="${pageModel.birdModel.names}" var="name">
${name.lang}: ${name.name}<br/>
</c:forEach>--%>
<form:form commandName="answer">
    <form:hidden path="id" />
	<div class="boxed">
		<ul>
			<c:forEach items="${pageModel.siblings}" var="sibling">
				<li><form:radiobutton path="choice" value="${sibling.scientificName}"/>${sibling.nameMap["Swedish"]}</li>
			</c:forEach>
			<li><input type="submit" value="Svara" /></li>
		</ul>
	</div>
</form:form>
</body>
</html>