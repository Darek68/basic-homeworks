package ru.darek;

import java.util.*;

public class PhoneBook {
    private Map<String, HashSet<String>> nameMap=new HashMap<String,HashSet<String>>(); //для поиска по ФИО
    private Map<String,String> phoneMap=new HashMap<String,String>(); //для поиска по телефону
    public PhoneBook() {
    }
    public String add(String name, String phone){
        if (name==null||phone==null) return "Переданы не верные данные!";
        if (phoneMap.containsKey(phone)) return ("Номер " + phone + " уже закреплен за абонентом: " + phoneMap.get(phone));

        //справочник ФИО
        if (! nameMap.containsKey(name)) nameMap.put(name, new HashSet<String>());
        Set<String> phones = nameMap.get(name);
        phones.add(phone);

        //справочник номеров
        phoneMap.put(phone,name);

        return "В книгу добалено: " + name + " с номером " +  phone;
    }
    public HashSet<String> find(String name){
        if (nameMap.containsKey(name)){
            return nameMap.get(name);
        }
        return null;
    }
    public boolean containsPhoneNumber(String phone){
        return (phoneMap.containsKey(phone));
    }
}
