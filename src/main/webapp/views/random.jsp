<jsp:useBean id="birdModel" scope="request" type="se.atrosys.birds.model.BirdModel"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>A random bird.</title>
</head>
<body>
A random bird:<br/>
Group: ${birdModel.family.group}<br/>
Family: ${birdModel.family.family}<br/>
Name: ${birdModel.scientificName}<br/>
</body>
</html>