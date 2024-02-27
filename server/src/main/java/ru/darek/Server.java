package ru.darek;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Server {
    public static final Logger logger = LogManager.getLogger(Server.class.getName());
    private int port;
    private List<ClientHandler> clients;
    private UserService userService;

    private boolean toClose;
    private List<String> messages = new LinkedList<>();
    private StringBuilder activeUsers;
    public UserService getUserService() {
        return userService;
    }
    public StringBuilder getActiveUsers() {
        return activeUsers;
    }

    public Server(int port) {
        this.port = port;
        this.clients = new ArrayList<>();
    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            logger.info("Сервер запущен на порту " + port + " Ожидание подключения клиентов.");
            // todo допилить выбор UserService в зависимости от параметра запуска
         //   userService = new InPostgresDAOUserService();
            userService = new InMemoryUserService();
            System.out.println("Запущен сервис для работы с пользователями");
            while (true) {
                Socket socket = serverSocket.accept();
                logger.info("Подключен новый сокет " + System.currentTimeMillis());
                if (toClose) serverSocket.close();
                try {
                    new ClientHandler(this, socket);
                } catch (IOException e) {
                    logger.error("Не удалось подключить клиента");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
//        } catch (SQLException e) {
//            logger.error("Попытка подключения к БД вызвала исключение: " + e.getMessage());
//            e.printStackTrace();
//        }
    }

    public synchronized void broadcastMessage(String message) {
        logger.debug("message: " + message);
        for (ClientHandler clientHandler : clients) {
            clientHandler.sendMessage(message);
        }
        writeToMessages(message);
    }

    private void writeToMessages(String message) {
        if (messages.size() > 10) messages.remove(0);
        messages.add(message);
    }

    public synchronized void subscribe(ClientHandler clientHandler) {
        broadcastMessage("Подключился новый клиент " + clientHandler.getUsername());
        clients.add(clientHandler);
        changeActiveList();
    }

    public synchronized void unsubscribe(ClientHandler clientHandler) {
        clients.remove(clientHandler);
        broadcastMessage("Отключился клиент " + clientHandler.getUsername());
        changeActiveList();
    }

    public synchronized boolean isUserBusy(String username) {
        for (ClientHandler c : clients) {
            if (c.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    public synchronized void sendPrivateMessage(ClientHandler sender, String receiverUsername, String message) {
        logger.debug("отправитель: " + sender.getUsername() + " получатель: " + receiverUsername + " " + message);
        for (ClientHandler clientHandler : clients) {
            System.out.println(receiverUsername + " " + clientHandler.getUsername());
            if (clientHandler.getUsername().equals(receiverUsername)) {
                clientHandler.sendMessage("<private> " + sender.getUsername() + ": " + message);
                sender.sendMessage("<private> " + sender.getUsername() + ": " + message);
                return;
            }
        }
        sender.sendMessage("<private> Не найден пользователь: " + receiverUsername);
    }

    public void kick(String kickUsername, ClientHandler admin) {
        logger.debug("kick " + kickUsername);
        ClientHandler kickHandler = null;
        for (ClientHandler clientHandler : clients) {
            logger.debug(kickUsername + " " + clientHandler.getUsername());
            if (clientHandler.getUsername().equals(kickUsername)) {
                kickHandler = clientHandler;
                break;
            }
        }
        if (kickHandler != null) {
            kickHandler.sendMessage("/exit_kick");
        } else {
            admin.sendMessage("<private> Не найден пользователь: " + kickUsername);
        }
    }

    public void shutdown() throws IOException {
        broadcastMessage("/exit_kick");
        toClose = true;
        Socket socket = new Socket("localhost", 8189);
    }

    public void ban(String banUsername, ClientHandler admin, boolean banUnban) {
        if (!userService.isUsernameAlreadyExist(banUsername)) {
            admin.sendMessage("<private> Пользователь: " + banUsername + " не существует!");
            return;
        }
        logger.info(banUnban + " " + banUsername);
        ClientHandler banHandler = null;
        for (ClientHandler clientHandler : clients) {
            logger.debug(banUsername + " " + clientHandler.getUsername());
            if (clientHandler.getUsername().equals(banUsername)) {
                banHandler = clientHandler;
                break;
            }
        }
        boolean action = userService.isBanByUsername(banUsername);
        if (action == banUnban) {
            admin.sendMessage("<private> Пользователь: " + banUsername + " уже имеет требуемый статус!");
            return;
        }
        if (banUnban) {
            broadcastMessage("Пользователь " + banUsername + "  получил бан!");
            logger.debug("banHandler " + banHandler);
            if (banHandler != null) banHandler.sendMessage("/exit_kick");
        } else {
            broadcastMessage("Пользователь " + banUsername + "  был разбанен!");
        }
        userService.setBanByUsername(banUsername, banUnban);
    }

    public void changeActiveList() {
        activeUsers = new StringBuilder();
        activeUsers.append("Активные пользователи:");
        for (ClientHandler clientHandler : clients) {
            activeUsers.append("\n").append(clientHandler.getUsername());
        }
    }

    public void changeNick(ClientHandler user, String newUsername) {
        logger.debug(user.getUsername() + " >>> " + newUsername);
        String currentUserName = user.getUsername();
        if (currentUserName.equals(newUsername)) {
            user.sendMessage("У вас уже имя: " + newUsername);
            return;
        }
        if (userService.isUsernameAlreadyExist(newUsername)) {
            user.sendMessage("Имя " + newUsername + " уже занято!");
            return;
        }
        userService.setNewUsername(currentUserName, newUsername);
        user.sendMessage("Ваш ник был успешно изменен на: " + newUsername + ". Перезайдите!");
        user.sendMessage("/exit_kick");
    }

    public String getHistory() {
        StringBuilder result = new StringBuilder("--history:\n");
        for (int i = 0; i < messages.size(); i++) {
            result.append (messages.get(i)).append("\n");
        }
        result.append ("--end history.");
        return result.toString();
    }
}