package ru.darek;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;

public class ServerApplication {
    public static final Logger logger = LogManager.getLogger(ServerApplication.class.getName());

    /**
     * Запуск java -jar -Dport=8189 server.jar abc def
     * -Dport=8189   это параметры JVM
     * abc def   это параметры server.jar
     * chcp 65001 >nul   для перевода терминалки на utf-8
     * Смена кодировки вывода в Powershell   [Console]::OutputEncoding = [System.Text.Encoding]::GetEncoding("utf-8")
     */
    public static void main(String[] args) {
     //   System.out.println("-Dport = " + System.getProperties().getOrDefault("port", "null"));
        int port = Integer.parseInt((String) System.getProperties().getOrDefault("port", "0"));
        if (port == 0) {
            try {
                port = Integer.parseInt(args[0].trim());
            } catch (NumberFormatException nfe) {
                port = 8189;
            }
        }
        Server server = new Server(port);
        //  Server server = new Server(8189);
        logger.info("Приложение запущено с параметрами args: " + Arrays.toString(args));
        logger.info("-Dport=" + System.getProperties().getOrDefault("port", "null"));
        System.out.println("Сервер стартовал!!");
        server.start();
        logger.info("Сервер закрывается.");
    }
}