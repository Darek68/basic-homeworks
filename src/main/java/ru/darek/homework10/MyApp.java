package ru.darek.homework10;

public class MyApp {
    public static void main(String[] args) {
        Box box = new Box(10, 23, 13, "серая");
        System.out.println(box.toString());
        box.color = "черная";
        System.out.println(box.toString());
        System.out.println();
        box.putItem("Предмет1");
        System.out.println();
        box.open();
        System.out.println(box.toString());
        System.out.println();
        box.putItem("Предмет1");
        System.out.println(box.toString());
        System.out.println();
        box.putItem("Предмет2");
        box.close();
        System.out.println();
        box.getItem();
        System.out.println(box.toString());
        System.out.println();
        box.open();
        box.getItem();
        box.putItem("Предмет2");
        System.out.println(box.toString());
        System.out.println();


        System.out.println("-----------------------");

        User[] users = {new User("Сидоров", "Игорь", "Петрович", getYearOfBirth(), "sidorip@mail.ru"),
                new User("Иванов", "Денис", "Сергеевич", getYearOfBirth(), "ivands@gmail.com"),
                new User("Петров", "Александ", "Александрович", getYearOfBirth(), "alek123@list.ru"),
                new User("Петров", "Сергей", "Александрович", getYearOfBirth(), "sery34@list.ru"),
                new User("Петров", "Михаил", "Александрович", getYearOfBirth(), "hero777@mail.ru"),
                new User("Петров", "Данил", "Александрович", getYearOfBirth(), "okden@list.ru"),
                new User("Петрова", "Надежда", "Александровна", getYearOfBirth(), "nperova@ya.ru"),
                new User("Попова", "Екатерина", "Павловна", getYearOfBirth(), "epavlovna@ya.ru"),
                new User("Земягин", "Полиграф", "Полиграфович", getYearOfBirth(), "bigbos@gmail.org"),
                new User("Шарикова", "Занаида", "Олеговна", getYearOfBirth(), "sharizo@umlf.de")
        };

        for (User user : users) {
            if (2003 - user.getYearOfBirth() > 40) {
                System.out.println(user.toString());
                System.out.println();
            }
        }
    }


    public static int getYearOfBirth() {
        return (int) (Math.random() * 100) + 1923;
    }
}
