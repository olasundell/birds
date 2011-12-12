<%--<jsp:useBean id="birdModel" scope="request" type="se.atrosys.birds.model.BirdModel"/>--%>
<jsp:useBean id="pageModel" scope="request" type="se.atrosys.birds.model.PageModel" />
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>A random bird.</title>
</head>
<body>
A random bird:<br/>
<img src="${pageModel.birdModel.photos[0].url}"/><br/>
<%--Group: ${pageModel.birdModel.family.group}<br/>
Family: ${pageModel.birdModel.family.family}<br/>
Name: ${pageModel.birdModel.scientificName}<br/>
<c:forEach items="${pageModel.birdModel.names}" var="name">
${name.lang}: ${name.name}<br/>
</c:forEach>--%>
<c:forEach items="${pageModel.siblings}" var="sibling">
	${sibling.scientificName}<br/>
</c:forEach>
</body>
</html>