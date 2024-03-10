package com.dmdev.http.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.Map;
import java.util.stream.Stream;

/*
 * Мы уже начинали работать с телом запроса HTTP Body, когда возвращали HTML страницу
 * из нашего первого сервлета в HttpServletResponse. Теперь давайте более подробно
 * остановимся на этой теме и детальнее разберем в видео как не только возвращать,
 * но и отправлять самим в HTTP запросах.
 */

@WebServlet("/first")
public class FirstServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String paramValue = req.getParameter("param");
        Map<String, String[]> parameterMap = req.getParameterMap();
        System.out.println();

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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (BufferedReader reader = req.getReader();
             Stream<String> lines = reader.lines()
        ) {
            System.out.println("--- Headers ---");
            Enumeration<String> headerNames = req.getHeaderNames();
            while (headerNames.hasMoreElements()) {
                String headerName = headerNames.nextElement();
                String headerValue = req.getHeader(headerName);

                // Выводим имя и значение заголовка
                System.out.println(headerName + ": " + headerValue);
            }

            System.out.println("\n--- Body ---");
            lines.forEach(System.out::println);
        }
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
