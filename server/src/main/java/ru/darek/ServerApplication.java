package ru.darek;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Properties;

public class ServerApplication {
    public static final Logger logger = LogManager.getLogger(ServerApplication.class.getName());

    private static int SOME_INT_VALUE = 1;
    private static String SOME_STRING_VALUE;
    private static int[] SOME_INT_ARRAY;
    private static double SOME_DOUBLE_VALUE;

  /*  static{
        Properties prop = new Properties();
        String workingDir = System.getProperty("user.dir");
        System.out.println("Current working directory : " + workingDir);
        try {
            File root = new File(Thread.currentThread().getContextClassLoader().getResource("").toURI());
            System.out.println("root " + root); // C:\JavaBasicProject\basic-homeworks\server\target\classes
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        } // C:\JavaBasicProject\basic-homeworks\server\src\main\resources
        File file = new File("server/config.ini"); // C:\JavaBasicProject\basic-homeworks\server\src\main\java\ru\darek\config
        try (InputStream input = ConnectionsHelper.class.getClassLoader().getResourceAsStream("config.ini")) {
          //  prop.load(new FileInputStream(new File("properties/config.ini")));  // C:\JavaBasicProject\basic-homeworks
            //load a properties file from class path, inside static method
            prop.load(input);
            //get the property value and print it out
            SOME_INT_VALUE = Integer.valueOf(prop.getProperty("SOME_INT_VALUE", "1"));
            SOME_STRING_VALUE = prop.getProperty("SOME_STRING_VALUE");
            SOME_DOUBLE_VALUE = Double.valueOf(prop.getProperty("SOME_DOUBLE_VALUE", "1.0"));
            System.out.println(SOME_INT_VALUE);
            System.out.println(SOME_STRING_VALUE);
            System.out.println(SOME_DOUBLE_VALUE);

            // Предположим, что в настройках находится список целых через точку с запятой
            String[] parts = prop.getProperty("SOME_INT_ARRAY").split(";");
            SOME_INT_ARRAY = new int[parts.length];
            for (int i = 0; i < parts.length; ++i)
            {
                SOME_INT_ARRAY[i] = Integer.valueOf(parts[i]);
            }
            System.out.println(Arrays.toString(SOME_INT_ARRAY));

//            System.out.println(prop.getProperty("database"));
//            System.out.println(prop.getProperty("dbuser"));
//            System.out.println(prop.getProperty("dbpassword"));
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    } */
    public static void main(String[] args) {
        Server server = new Server(8189);
        logger.info("Сервер запущен с параметрами args: " + Arrays.toString(args));
        logger.info("-Dport=" + System.getProperties().getOrDefault("port", "null"));
        server.start();
        logger.info("Сервер закрывается.");
    }
}