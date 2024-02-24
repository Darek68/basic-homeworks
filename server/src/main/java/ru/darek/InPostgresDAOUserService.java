package ru.darek;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;

public class InPostgresDAOUserService implements UserService {
    public static final Logger logger = LogManager.getLogger(InPostgresDAOUserService.class.getName());
    private static ConnectionsHelper connectionsHelper;

    static {
        try {
            connectionsHelper = new ConnectionsHelper();
        } catch (SQLException e) {
            logger.error("Попытка коннекта к БД вызвала исключение: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public String getUsernameByLoginAndPassword(String login, String password) {
        try {
            logger.info("login: " + login + " password: " + password);
            connectionsHelper.preparedStatementUsers.setString(1, login);
            try (ResultSet resultSet = connectionsHelper.preparedStatementUsers.executeQuery()) {
                while (resultSet.next()) {
                    logger.debug(resultSet.getString(1) + " " + resultSet.getString(2) + " " + resultSet.getString(3));
                    if (BCrypt.checkpw(password, resultSet.getString(2))) {
                        String username = resultSet.getString(3);
                        return username;
                    }
                }
            }
        } catch (SQLException e) {
            logger.error("Поиск пользователя в БД вызвал исключение: " + e.getMessage());
            e.printStackTrace();
        }
        logger.debug("Не нашли пользователя " + login);
        return null;
    }

    @Override
    public boolean getIsAdminByUsername(String username) {
        try {
            connectionsHelper.preparedStatementIsAdmin.setString(1, username);
            try (ResultSet resultSet = connectionsHelper.preparedStatementIsAdmin.executeQuery()) {
                if (resultSet.next()) {
                    logger.info("InPostgresUserService: пользователь " + username + " имеет роль ADMIN");
                    return true;
                }
            }

        } catch (SQLException e) {
            logger.error("Запрос наличия админских прав вызвал исключение: " + e.getMessage());
            e.printStackTrace();
        }
        logger.info("Y пользователя " + username + " нет роли ADMIN");
        return false;
    }

    @Override
    public void createNewUser(String login, String password, String username) {
        String salt = BCrypt.gensalt();
        String hashedPassword = BCrypt.hashpw(password, salt);
        PreparedStatement preparedStatement = connectionsHelper.preparedStatementCreateNewUser;
        try {
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, hashedPassword);
            preparedStatement.setString(3, username);
            preparedStatement.setString(4, login);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Запрос на создание пользователя вызвал исключение: " + e.getMessage());
            e.printStackTrace();
        }
        logger.info("Пользователь " + username + "(" + login + ") успешно создан c ролю manager");
    }

    @Override
    public boolean isLoginAlreadyExist(String login) {
        try {
            connectionsHelper.preparedStatementUserByLogin.setString(1, login);
            try (ResultSet resultSet = connectionsHelper.preparedStatementUserByLogin.executeQuery()) {
                if (resultSet.next()) {
                    logger.debug("логин " + login + " уже занят пользователем: " + resultSet.getString(3));
                    return true;
                }
            }
        } catch (SQLException e) {
            logger.error("Запрос свободности логина вызвал исключение: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean isUsernameAlreadyExist(String username) {
        try {
            connectionsHelper.preparedStatementUserByUsername.setString(1, username);
            try (ResultSet resultSet = connectionsHelper.preparedStatementUserByUsername.executeQuery()) {
                if (resultSet.next()) {
                    String login = resultSet.getString(1);
                    logger.debug("Пользователь " + username + " уже занят логином: " + login);
                    return true;
                }
            }
        } catch (SQLException e) {
            logger.error("Ошибка запроса проверки username " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void setBanByUsername(String username, boolean banUnban) {
        try {
            connectionsHelper.preparedStatementBanUnban.setBoolean(1, banUnban);
            connectionsHelper.preparedStatementBanUnban.setString(2, username);
            connectionsHelper.preparedStatementBanUnban.executeUpdate();
        } catch (SQLException e) {
            logger.error("Ошибка запроса бана " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public boolean isBanByUsername(String username) {
        try {
            connectionsHelper.preparedStatementUserByUsername.setString(1, username);
            try (ResultSet resultSet = connectionsHelper.preparedStatementUserByUsername.executeQuery()) {
                while (resultSet.next()) {
                    return resultSet.getBoolean(4);
                }
            }
        } catch (SQLException e) {
            logger.error("Запрос наличия бана вызвал исключение: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void setNewUsername(String currentUserName, String newUsername) {
        try {
            connectionsHelper.preparedStatementSetNewUsername.setString(1, newUsername);
            connectionsHelper.preparedStatementSetNewUsername.setString(2, currentUserName);
            connectionsHelper.preparedStatementSetNewUsername.executeUpdate();
        } catch (SQLException e) {
            logger.error("Запрос смены имени вызвал исключение: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
