<%@ page import="com.dmdev.http.service.TicketService" %>
<%@ page import="com.dmdev.http.dto.TicketDto" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Купленные билеты: </h1>
<ul>
    <%--    ТАК УЖЕ НЕ ДЕЛАЮТ!! ЭТО ПРОСТО ДЛЯ ПРИМЕРА КАК БЫЛО!    --%>
    <%
        Long flightId = Long.valueOf(request.getParameter("flightId"));
        List<TicketDto> tickets = TicketService.getInstance().findAllByFlightId(flightId);
        for (TicketDto ticket : tickets) {
            out.write(String.format("<li>%s - %s - %s - %s - %s</li>",
                    ticket.id(), ticket.flight_id(), ticket.passenger_name(), ticket.seat_no(), ticket.cost()));
        }
    %>
</ul>
</body>
</html>
