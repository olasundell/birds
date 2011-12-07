<jsp:useBean id="birdModel" scope="request" type="se.atrosys.birds.model.BirdModel"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>A random bird.</title>
</head>
<body>
<h1>${birdModel.scientificName}</h1>
</body>
</html>