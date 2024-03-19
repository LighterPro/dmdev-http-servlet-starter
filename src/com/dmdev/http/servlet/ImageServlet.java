package com.dmdev.http.servlet;

import com.dmdev.http.service.ImageService;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.io.IOException;
import java.io.InputStream;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@WebServlet("/images/*")
class ImageServlet extends HttpServlet {
    private static final ImageServlet INSTANCE = new ImageServlet();

    static ImageServlet getInstance() {
        return INSTANCE;
    }

    private final ImageService imageService = ImageService.getInstance();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestURI = req.getRequestURI();
        String imagePath = requestURI.replace("/images", "");

        imageService.get(imagePath)
                .ifPresentOrElse(image -> {
                            resp.setContentType("application/octet-stream");
                            writeImage(image, resp);
                        }, () -> resp.setStatus(404)
                );

    }

    @SneakyThrows
    private void writeImage(InputStream image, HttpServletResponse resp) {
        try (image;
             ServletOutputStream outputStream = resp.getOutputStream()
        ) {
            int currentByte;
            while ((currentByte = image.read()) != -1) {
                outputStream.write(currentByte);
            }
        }
    }
}