package ru.darek;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String path = getFile();
        if (path.equals("0")) return;

        String word = getWord();
        if (word.equals("0")) return;

        List<String> strings = getStrings(path);
        if (strings.size()==0){
            System.out.println("Файл пустой");
            return;
        }

        int numberOfOccurrences = 0;
        for (int i = 0; i < strings.size(); i++) {
            numberOfOccurrences += (strings.get(i) + "\0").split(word).length - 1;
        }
        System.out.format("Количество вхождении '%s' в файле '%s' ровно %d", word, path, numberOfOccurrences);
    }

    private static String getFile() {
        File file;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Укажите файл с которым будем работать:");
        String path = scanner.next();
        file = new File(path);
        while (!file.exists()) {
            System.out.printf("Файл %s не существует. Укажите существующий файл. (0 - выход) \n", path);
            path = scanner.next();
            if (path.equals("0")) break;
            file = new File(path);
        }
        return path;
    }

    private static String getWord() {
        String word;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.printf("Укажите строку вхождения. (0 - выход) \n");
            word = scanner.next();
            if (word.equals("0")) break;
        } while (word == "" && word == null);
        return word;
    }

    private static List<String> getStrings(String path) {
        List<String> strings = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                strings.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return strings;
    }
}

