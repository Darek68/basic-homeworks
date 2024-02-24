package ru.darek;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConnectionsHelper {
    public static final Logger logger = LogManager.getLogger(ConnectionsHelper.class.getName());
    private Connection connection;
    private static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String SELECT_USERS_SQL = "SELECT u.login,u.hashpass, u.username FROM homework24.users u WHERE u.login = ?;";
    PreparedStatement preparedStatementUsers;
    private static final String SELECT_IS_ADMIN_SQL = """
               SELECT u.login FROM homework24.users u, homework24.usertorole ur,homework24.roles r 
               WHERE u.username = ? AND u.login = ur.user_id AND r.id = ur.role_id AND r.name = 'ADMIN';
            """;
    PreparedStatement preparedStatementIsAdmin;
    private static final String SELECT_USER_BY_LOGIN_SQL = "SELECT * FROM homework24.users u WHERE u.login = ?;";
    PreparedStatement preparedStatementUserByLogin;
    private static final String SELECT_USER_BY_USERNAME_SQL = "SELECT * FROM homework24.users u WHERE u.username = ?;";
    PreparedStatement preparedStatementUserByUsername;
    private static final String INSERT_USER_SQL = """   
                BEGIN TRANSACTION;        
                INSERT INTO homework24.users (login,hashpass,username) VALUES (?,?,?);
                INSERT INTO homework24.usertorole (user_id,role_id) VALUES (?,2);
                COMMIT TRANSACTION;
            """;
    PreparedStatement preparedStatementCreateNewUser;
    private static final String UPDATE_BAN_BY_USERNAME_SQL = "UPDATE homework24.users u SET ban = ? WHERE u.username = ?;";
    PreparedStatement preparedStatementBanUnban;
    private static final String UPDATE_USERNAME_BY_USERNAME_SQL = "UPDATE homework24.users u SET username = ? WHERE u.username = ?;";
    PreparedStatement preparedStatementSetNewUsername;

    public ConnectionsHelper() throws SQLException {
        connection = DriverManager.getConnection(DATABASE_URL, "postgres", "352800");
        logger.info("Успешный конект к БД");
        preparedStatementUsers = connection.prepareStatement(SELECT_USERS_SQL);
        logger.debug("Запрос preparedStatementUsers успешно подготовлен");
        preparedStatementIsAdmin = connection.prepareStatement(SELECT_IS_ADMIN_SQL);
        logger.debug("Запрос preparedStatementIsAdmin успешно подготовлен");
        preparedStatementUserByLogin = connection.prepareStatement(SELECT_USER_BY_LOGIN_SQL);
        logger.debug("Запрос preparedStatementUserByLogin успешно подготовлен");
        preparedStatementUserByUsername = connection.prepareStatement(SELECT_USER_BY_USERNAME_SQL);
        logger.debug("Запрос preparedStatementUserByUsername успешно подготовлен");
        preparedStatementCreateNewUser = connection.prepareStatement(INSERT_USER_SQL);
        logger.debug("Запрос preparedStatementCreateNewUser успешно подготовлен");
        preparedStatementBanUnban = connection.prepareStatement(UPDATE_BAN_BY_USERNAME_SQL);
        logger.debug("Запрос preparedStatementBanUnban успешно подготовлен");
        preparedStatementSetNewUsername = connection.prepareStatement(UPDATE_USERNAME_BY_USERNAME_SQL);
        logger.debug("Запрос preparedStatementSetNewUsername успешно подготовлен");
    }
}
