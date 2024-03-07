package com.dmdev.http.socket;

import java.io.IOException;
import java.net.*;


/*Для реализации сетевого взаимодействия по протоколу UDP в Java
из пакета java.net есть класс DatagramSocket. Он используется
в качестве реализации как клиента, так и сервера, в отличие от TCP протокола*/


public class DatagramRunner {
    public static void main(String[] args) throws IOException {
        try (DatagramSocket datagramSocket = new DatagramSocket()) {
            // ---кто-то пишет в буфер----> [buffer] <----кто-то читает из буфера----
            byte[] bytes = "Hello from UDP client".getBytes();

            InetAddress address = InetAddress.getByName("localhost");
            int port = 7777;

            DatagramPacket packet = new DatagramPacket(bytes, bytes.length, address, port);
            datagramSocket.send(packet);
        }
    }
}
