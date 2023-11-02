package ru.darek.homework10;

public class User {
    private String surname;
    private String name;
    private String patronymic;
    private int yearOfBirth;
    private String email;

    public User(String surname, String name, String patronymic, int yearOfBirth, String email) {
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.yearOfBirth = yearOfBirth;
        this.email = email;
    }

    @Override
    public String toString() {
        return "ФИО: " + this.surname + " " + this.name + " " + this.patronymic + "\n" +
                "Год рождения: " + this.yearOfBirth + "\n" +
                "e-mail: " + this.email;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }
}
