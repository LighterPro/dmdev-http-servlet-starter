package com.dmdev.http.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/dispatcher")
public class DispatcherServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/flights");
//        req.setAttribute("1", "234");
        requestDispatcher.include(req, resp);
        PrintWriter writer = resp.getWriter();
        writer.write("Hello 2");

        System.out.println();
    }
}
