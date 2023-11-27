package ru.darek;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Start {
    public static void main(String[] args) {
        System.out.println(getList(3, 8));
        System.out.println("------------------");
        System.out.println(getSumList(Arrays.asList(1, 3, 6, 9, 4, 16)));
        System.out.println("------------------");
        List<Integer> list = Arrays.asList(1, 3, 6, null, 4, 16);
        System.out.println(list);
        setByInt(5, list);
        System.out.println(list);
        System.out.println("------------------");
        list = Arrays.asList(1, 3, 6, 77, 4, 16);
        System.out.println(list);
        addByInt(3, list);
        System.out.println(list);
        System.out.println("------------------");
        List<Employee> employees = Arrays.asList(new Employee("Вася", 32),
                new Employee("Алекс", 53),
                new Employee("Маша", 19),
                new Employee("Ольга", 41));
        System.out.println(employees);
        System.out.println("Сотрудники по именам: \n" + getNames(employees));
        System.out.println("Сотрудники старше 40: \n" + getEmployeesByAge(employees, 40));
        System.out.println("------------------");
        System.out.println(checkAverageAge(employees, 35));
        System.out.println(checkAverageAge(employees, 36));
        System.out.println("------------------");
        System.out.println("Самый молодой сотрудник: " + getYoungestEmployee(employees).toString());
    }

    public static List<Integer> getList(int min, int max) {
        List<Integer> list = new ArrayList<>(max - min + 1);
        for (int i = min; i <= max; i++) {
            list.add(i);
        }
        return list;
    }

    public static int getSumList(List<Integer> list) {
        int sum = 0;
        for (Integer element : list) {
            if (element > 5) sum += element;
        }
        return sum;
    }

    public static void setByInt(int value, List<Integer> list) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) != null) list.set(i, value);
        }
    }

    public static void addByInt(int value, List<Integer> list) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) != null) list.set(i, list.get(i) + value);
        }
    }

    private static List<String> getNames(List<Employee> employees) {
        List<String> names = new ArrayList<>(employees.size());
        for (Employee employee : employees) {
            names.add(employee.getName());
        }
        return names;
    }

    private static List<Employee> getEmployeesByAge(List<Employee> employees, int age) {
        List<Employee> resEmployees = new ArrayList<>(employees.size());
        for (Employee employee : employees) {
            if (employee.getAge() >= age) resEmployees.add(employee);
        }
        return resEmployees;
    }

    private static boolean checkAverageAge(List<Employee> employees, int avg) {
        int ages = 0;
        for (int i = 0; i < employees.size(); i++) {
            ages += employees.get(i).getAge();
        }
        return ages / employees.size() > avg;
    }

//    private static Employee getYoungestEmployee(List<Employee> employees) {
//        Employee youngestEmployee = employees.get(0);
//        int youngestAge = 100, currentAge = 0;
//        for (Employee currentEmployee : employees) {
//            currentAge = currentEmployee.getAge();
//            if (currentAge < youngestAge) {
//                youngestAge = currentAge;
//                youngestEmployee = currentEmployee;
//            }
//        }
//        return youngestEmployee;
//    }
    private static Employee getYoungestEmployee(List<Employee> employees) {
        Employee youngestEmployee = employees.get(0);
        for (Employee currentEmployee : employees) {
            if (currentEmployee.getAge() < youngestEmployee.getAge()) {
                youngestEmployee = currentEmployee;
            }
        }
        return youngestEmployee;
    }
}
