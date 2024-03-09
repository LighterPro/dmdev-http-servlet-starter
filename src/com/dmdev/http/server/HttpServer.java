package com.dmdev.http.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*Как мы помним, метод accept у SocketServer блокирующий, и до тех пор, пока наш веб-сервер
вновь не вызовет этот метод - все остальные HTTP запросы будут ожидать, стоя в очереди. Для того,
чтобы наш реализованный веб-сервер умел параллельно обрабатывать HTTP запросы, необходимо добавить
пул соединений и отправлять обработку каждого HTTP запроса этому пулу.*/

public class HttpServer {
    private final int port;
    private final ExecutorService pool;
    private boolean stopped;

    public HttpServer(int port, int poolSize) {
        this.port = port;
        this.pool = Executors.newFixedThreadPool(poolSize);
    }

    public void setStopped(boolean stopped) {
        this.stopped = stopped;
    }

    public void run() {
        try {
            ServerSocket server = new ServerSocket(port);
            while (!stopped) {
                Socket socket = server.accept();
                System.out.println("Socket accepted");
                pool.submit(() -> processSocket(socket));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void processSocket(Socket socket) {
        try (socket;
             DataInputStream inputStream = new DataInputStream(socket.getInputStream());
             DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream())
        ) {
            // step 1 - handle request
            System.out.println("Request: " + new String(inputStream.readNBytes(400)));

            Thread.sleep(10_000);
            // step 2 - handle response
            byte[] body = Files.readAllBytes(Path.of("resources", "example.html"));
            byte[] headers = """
                    HTTP/1.1 200 OK
                    content-type: text/html
                    content-length: %s
                    """.formatted(body.length).getBytes();

            outputStream.write(headers);
            outputStream.write(System.lineSeparator().getBytes());
            outputStream.write(body);
        } catch (IOException | InterruptedException e) {
            // TODO: log errors
        }
    }
}
