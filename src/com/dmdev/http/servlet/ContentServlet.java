package com.dmdev.http.servlet;

import com.dmdev.http.dto.FlightDto;
import com.dmdev.http.service.FlightService;
import com.dmdev.http.util.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

import static java.util.stream.Collectors.toMap;

@WebServlet("/content")
public class ContentServlet extends HttpServlet {

    private final FlightService flightService = FlightService.getInstance();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<FlightDto> flightsDtos = flightService.findAll();

        req.setAttribute("flights", flightsDtos);
        HttpSession session = req.getSession();
        session.setAttribute("flightsMap", flightsDtos.stream()
                .collect(toMap(FlightDto::getId, FlightDto::getDescription)));

        req.getRequestDispatcher(JspHelper.getPath("content"))
                .forward(req, resp);
    }
}
