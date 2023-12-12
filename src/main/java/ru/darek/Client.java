package ru.darek;

import java.io.*;
import java.net.Socket;

public class Client {
    private static Socket clientSocket; //сокет для общения
    private static BufferedReader reader; // ридер читающий с консоли
    private static BufferedReader in; // поток чтения из сокета
    private static BufferedWriter out; // поток записи в сокет

    public static void main(String[] args) {
        try {
            try {
                clientSocket = new Socket("localhost", 8890);
                reader = new BufferedReader(new InputStreamReader(System.in));

                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); // читать соообщения с сервера
                out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream())); // писать на сервер

                String serverWord = in.readLine();
                System.out.println(serverWord);
                String userInput = "";
                do {
                    userInput = reader.readLine();
                    if (userInput.equals("exit")) break;
                    out.write(userInput + "\n"); // отправляем сообщение на сервер
                    out.flush();
                    serverWord = in.readLine(); // ждём, что скажет сервер
                    System.out.println("Сервер:  " + serverWord);
                }while (true);
            } finally { // всегда закрываем сокет и потоки
                clientSocket.close();
                in.close();
                out.close();
                System.out.println("Клиент был закрыт...");
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
