package ru.darek;

public class Start {
    public static void main(String[] args) {
        PhoneBook phoneBook = new PhoneBook();

        System.out.println(phoneBook.add("Вася", "+79034563287"));
        System.out.println(phoneBook.add("Иванов Михаил Сергеевич", "+74952613288"));
        System.out.println(phoneBook.add("Иванов Михаил Сергеевич", "+79268526254"));
        System.out.println(phoneBook.add("Иванов Михаил Сергеевич", "+79268526254"));
        System.out.println(phoneBook.add("Женечка", "+79068526201"));
        System.out.println("Иванов Михаил Сергеевич: \n" + phoneBook.find("Иванов Михаил Сергеевич"));
        System.out.println("Иванов Сергей: \n" + phoneBook.find("Иванов Сергей"));
        System.out.println("Есть ли в тель книге номер +79034563287? " + phoneBook.containsPhoneNumber("+79034563287"));
        System.out.println("Есть ли в тель книге номер +79034563200? " + phoneBook.containsPhoneNumber("+79034563200"));


    }
}
