package com.dmdev.http.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class SocketServerRunner {
    public static void main(String[] args) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(7777);
             Socket socket = serverSocket.accept(); // <- после этой команды процесс перейдет в ожидание,
             // пока к нему кто-то не подключится
             DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
             DataInputStream inputStream = new DataInputStream(socket.getInputStream());
             Scanner scanner = new Scanner(System.in)
        ) {
            String messageFromClient = inputStream.readUTF();
            while (!"stop".equals(messageFromClient)) {
                System.out.println("Client: " + messageFromClient);
                String responseFromServer = scanner.nextLine();
                outputStream.writeUTF(responseFromServer);
                messageFromClient = inputStream.readUTF();
            }
        }
    }
}
