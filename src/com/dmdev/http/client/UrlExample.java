package com.dmdev.http.client;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

/* На замену класса URL нам пришел новый класс HttpClient, начиная с версии Java 11 */

public class UrlExample {
    public static void main(String[] args) throws IOException {
        URL url = new URL("file:/Users/dos/Study/http-servlets-starter/src/com/dmdev/http/socket/DatagramRunner.java ");
        URLConnection urlConnection = url.openConnection();

        System.out.println(new String(urlConnection.getInputStream().readAllBytes()));
    }

    private static void checkGoogle() throws IOException {
        // так уже не делается, это просто для примера
        URL url = new URL("https://www.google.com");
        URLConnection urlConnection = url.openConnection();
        urlConnection.setDoOutput(true);

        try (var outputStream = urlConnection.getOutputStream()) {

        }

        System.out.println();
    }
}
