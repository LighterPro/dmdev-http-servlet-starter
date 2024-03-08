package com.dmdev.http.client;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;

import static java.net.http.HttpResponse.*;

public class HttpClientExample {

    public static void main(String[] args) throws IOException, InterruptedException {
        try (HttpClient httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .build()
        ) {
            HttpRequest getRequest = HttpRequest.newBuilder(URI.create("https://google.com"))
                    .GET()
                    .build();

            HttpRequest postRequest = HttpRequest.newBuilder(URI.create("https://google.com"))
                    .POST(HttpRequest.BodyPublishers.ofFile(Path.of("path", "to", "file")))
                    .build();

            HttpResponse<String> response = httpClient.send(getRequest, BodyHandlers.ofString());
            HttpHeaders headers = response.headers();

            System.out.println("------- headers -------");
            headers.map().forEach((k, v) -> {
                System.out.print(k + " : ");
                System.out.println(v);
            });

            System.out.println("------- body -------");
            System.out.println(response.body());
        }
    }

}
