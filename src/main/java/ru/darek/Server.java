package ru.darek;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static Socket clientSocket;
    private static ServerSocket server;
    private static BufferedReader in; // поток чтения из сокета
    private static BufferedWriter out; // поток записи в сокет

    public static void main(String[] args) {
        try {
            try {
                server = new ServerSocket(8890);
                System.out.println("Сервер запущен!");
                clientSocket = server.accept();
                try {
                    in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); // прием
                    out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream())); // отправка
                    String action = getAction();
                    Integer firstNumber = getNumber("первое");
                    Integer secondNumber = getNumber("второе");
                    out.write(firstNumber + action + secondNumber + "=" + getResAction(action,firstNumber,secondNumber) + "\n");
                    out.flush();
                } finally {
                    clientSocket.close();
                    in.close();
                    out.close();
                }
            } finally {
                System.out.println("Сервер закрыт!");
                server.close();
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    private static String getAction() throws IOException {
        String action = "";
        do {
            out.write("Укажите действие: + - * /\n");
            out.flush();
            action = in.readLine();
            System.out.println(action);
        } while (!action.equals("-") && !action.equals("+") && !action.equals("*") && !action.equals("/"));
        System.out.println("Выбрано действие: " + action);
        return action;
    }

    private static Integer getNumber(String str) throws IOException {
        String stringNumber = "";
        Integer resNumber = null;
        out.write("Укажите " + str + " число: \n");
        out.flush();
        do {
            stringNumber = in.readLine();
            System.out.println("Первое число (строка): " + stringNumber);
            try {
                resNumber = Integer.parseInt(stringNumber);
            } catch (NumberFormatException e) {
                out.write("Укажите КОРРЕКТНОЕ " + str + " число: \n");
                out.flush();
            }
            if (resNumber != null) break;
        } while (true);
        System.out.println(str + " число (number): " + resNumber);
        return resNumber;
    }
    private static Integer getResAction(String action, Integer firstNumber, Integer secondNumber){
        switch (action) {
            case  ("+"):
                return (firstNumber + secondNumber);
            case ("-"):
                return (firstNumber - secondNumber);
            case ("/"):
                return (firstNumber / secondNumber);
            default:
                return (firstNumber * secondNumber);
        }
    }
}
