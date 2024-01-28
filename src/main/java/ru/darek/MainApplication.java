package ru.darek;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Array;
import java.util.Arrays;

public class MainApplication {
    public static final Logger logger = LogManager.getLogger(MainApplication.class.getName());
    // Домашнее задание:
    // - Добавить логирование
    // - Добавить обработку запросов в параллельных потоках
    public static void main(String[] args) {
        logger.info("Приложение запущено с параметрами args: " + Arrays.toString(args));
        logger.info("-Dport=" + System.getProperties().getOrDefault("port", "null"));
        HttpServer server = new HttpServer(Integer.parseInt((String) System.getProperties().getOrDefault("port", "8189")));
        server.start();
    }
}