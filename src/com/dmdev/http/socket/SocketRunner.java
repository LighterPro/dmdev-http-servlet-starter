package com.dmdev.http.socket;

import java.io.*;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.Socket;

public class SocketRunner {
    public static void main(String[] args) throws IOException {

        InetAddress inetAddress = Inet4Address.getByName("ya.ru");
        int port = 80;

        try (Socket socket = new Socket(inetAddress, port);
             DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
             DataInputStream inputStream = new DataInputStream(socket.getInputStream())
        ) {
            outputStream.writeUTF("Hello world!");
            byte[] response = inputStream.readAllBytes();
            System.out.println(response.length);
        }
    }
}
