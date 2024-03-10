package com.dmdev.http.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;

/*
* Мы уже прекрасно знаем как выглядят HTTP заголовки и зачем они нужны,
* но теперь нам необходимо разобраться с тем, как их использовать в наших сервлетах.
* Нам нужно знать как они получаются из HTTP запроса, а также как их устанавливать в HTTP ответах,
* чем и будем заниматься в этом видео.
* */

@WebServlet("/first")
public class FirstServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Enumeration<String> headerNames = req.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String header = headerNames.nextElement();
            System.out.println(req.getHeader(header));
        }


        resp.setContentType("text/html");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        resp.setHeader("token", "12345");

        try (PrintWriter writer = resp.getWriter()) {
            writer.write("<h2>Hello from FirstServletZZ!!!! Привет!! </h2>");
        }
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
