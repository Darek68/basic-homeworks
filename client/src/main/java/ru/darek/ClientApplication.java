package ru.darek;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;
import java.util.Scanner;
/**
 * Запуск java -jar client-jar-with-dependencies.jar localhost 8180
 * localhost 8180   это параметры client-jar-with-dependencies.jar
 * chcp 65001 >nul   для перевода терминалки на utf-8
 * Смена кодировки вывода в Powershell   [Console]::OutputEncoding = [System.Text.Encoding]::GetEncoding("utf-8")
 */

public class ClientApplication {
    public static final Logger logger = LogManager.getLogger(ClientApplication.class.getName());
    private static String username;

    private static Socket socket;
    private static DataInputStream in;
    private static DataOutputStream out;
    public static void main(String[] args) {
        logger.info("Клиент стартовал с параметрами args: " + Arrays.toString(args));
        String ipAddr = "localhost";
        int port = 8189;
        if (args.length > 0) ipAddr = args[0];
        if (args.length > 1) {
            try {
                port = Integer.parseInt(args[1].trim());
            } catch (NumberFormatException nfe) {
                port = 8189;
            }
        }

        try {
        //    socket = new Socket("localhost", 8189);
            logger.info("ipAddr = " + ipAddr + "  port " + port);
            socket = new Socket(ipAddr, port);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            System.out.println("Подключились к серверу. Наберите /help для справки по меню.");
            Scanner scanner = new Scanner(System.in);
            new Thread(() -> {
                try {
                    while (true) {
                        String message = in.readUTF();
                        if (message.startsWith("/")) {
                            if (message.startsWith("/authok ")) {
                                username = message.split(" ")[1];
                                break;
                            }
                        }
                        System.out.println(message);
                    }
                    while (true) {
                        String message = in.readUTF();
                        if (message.startsWith("/exit_confirmed")) break;
                        if (message.startsWith("/exit_kick")) {
                            out.writeUTF("/exit");
                            break;
                        }
                        System.out.println(message);
                    }
                } catch (EOFException e) {
                    logger.info("Поток закрылся - читать нельзя!");
                    e.printStackTrace();
                } catch (IOException e) {
                    logger.info("Потеряна связь с сокетом!");
                    e.printStackTrace();
                } finally {
                    disconnect();
                }
            }).start();
            while (true) {
                String message = scanner.nextLine();
                out.writeUTF(message);
                if (message.equals("/exit")) {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void disconnect() {
        logger.debug("Вызван disconnect");
        try {
            if (in != null) {
                in.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            if (out != null) {
                out.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}