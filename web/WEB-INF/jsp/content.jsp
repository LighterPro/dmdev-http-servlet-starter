<%--
  Created by IntelliJ IDEA.
  User: dos
  Date: 15.03.2024
  Time: 15:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@include file="header.jsp" %>
<div>
    <span>Content. Русский</span>
    <p>requestScope.flights.size() : ${requestScope.flights.size()}</p>
    <p>requestScope.flights.get(0).id : ${requestScope.flights.get(0).id}</p>
    <p>requestScope.flights[1].id : ${requestScope.flights[1].id}</p>
    <p>requestScope.flightsMap[1] : ${requestScope.flightsMap[1].id}</p>
    <p>cookie["JSESSIONID"] : ${cookie["JSESSIONID"].value}, unique identifier</p>
    <p>header["Cookie"] : ${header["Cookie"]}</p>
    <p>param.id : ${param.id}</p>
    <p>param.test : ${param.test}</p>
    <p>not empty requestScope.flights : ${not empty requestScope.flights}</p>
</div>
<%@include file="footer.jsp" %>
</body>
</html>
