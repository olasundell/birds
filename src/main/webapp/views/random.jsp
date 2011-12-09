<jsp:useBean id="birdModel" scope="request" type="se.atrosys.birds.model.BirdModel"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>A random bird.</title>
</head>
<body>
A random bird:<br/>
Group: ${birdModel.family.group}<br/>
Family: ${birdModel.family.family}<br/>
Name: ${birdModel.scientificName}<br/>
<c:forEach items="${birdModel.names}" var="name">
${name.lang}: ${name.name}<br/>
</c:forEach>
</body>
</html>