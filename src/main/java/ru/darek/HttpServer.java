package ru.darek;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.OutputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HttpServer {
    public static final Logger logger = LogManager.getLogger(HttpServer.class.getName());
    private int port;
    private Dispatcher dispatcher;
    public HttpServer(int port) {
        this.port = port;
        this.dispatcher = new Dispatcher();
    }
    public void start() {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            logger.error("Сервер запущен на порту: " + port);
            while (true) {
                try {
                    Socket socket = serverSocket.accept();
                    executorService.execute(() -> {
                        try {
                            byte[] buffer = new byte[8192];
                            int n = socket.getInputStream().read(buffer);
                            // System.out.println("Работает поток: " + Thread.currentThread().getName());
                            logger.info("Работает поток: " + Thread.currentThread().getName());
                            String rawRequest = new String(buffer, 0, n);
                            HttpRequest httpRequest = new HttpRequest(rawRequest);
                            dispatcher.execute(httpRequest, socket.getOutputStream());
                        } catch (IOException e) {
                            logger.error("Ошибка в потоке " + Thread.currentThread().getName() + " " + e);
                        }
                    });
                } catch (IOException e) {
                    logger.error("Ошибка сокета: " + e);
                }
            }
        } catch (Exception e) {
            logger.error("Исключение в ServerSocket: " + e);
        }
        executorService.shutdown();
    }
}