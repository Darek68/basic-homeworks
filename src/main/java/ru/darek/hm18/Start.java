package ru.darek.hm18;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Start {
    public static void main(String[] args) {
        File catalog = new File(".");
        System.out.println("Список файлов в текущей папке:");
        for (File item : catalog.listFiles()) {
            if (item.isFile()) System.out.println(item.getName());
        }
        System.out.println("Укажите файл с которым будем работать:");
        Scanner scanner = new Scanner(System.in);
        String path = scanner.next();
        String txt = null;

        do {
            fileToConsole(path);
            System.out.println("\nЧто дописать в файл? (0-выход)");
            txt = scanner.next();
            if (!txt.equals("0")) strToFile(path, txt + "\n");
        } while (!txt.equals("0"));
    }

    private static void strToFile(String path, String txt) {
        try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(path, true))) {
            byte[] buffer = txt.getBytes(StandardCharsets.UTF_8);
            for (int i = 0; i < buffer.length; i++) {
                out.write(buffer[i]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void fileToConsole(String path) {
        InputStreamReader reader = null;
        int n = 0;
        try (FileInputStream inStream = new FileInputStream(path)) {
            reader = new InputStreamReader(inStream, "UTF-8");
            System.out.println("Содержимое файла " + path + " :");
            while ((n = reader.read()) != -1) System.out.print((char) n);
            System.out.println("<конец файла>\n");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
