package ru.darek;

public class Start {
    public static void main(String[] args) {
        PhoneBook phoneBook = new PhoneBook();

        System.out.println(phoneBook.add("Вася", 89034563287L));
        System.out.println(phoneBook.add("Иванов Михаил Сергеевич", 84952613288L));
        System.out.println(phoneBook.add("Иванов Михаил Сергеевич", 89268526254L));
        System.out.println(phoneBook.add("Иванов Михаил Сергеевич", 89268526254L));
        System.out.println(phoneBook.add("Женечка", 89068526201L));
        System.out.println(phoneBook.find("Иванов Михаил Сергеевич"));
        System.out.println(phoneBook.find("Иванов Сергей"));
        System.out.println(phoneBook.containsPhoneNumber(89034563287L));
        System.out.println(phoneBook.containsPhoneNumber(89034563200L));


    }
}
