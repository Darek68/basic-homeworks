package ru.darek;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PhoneBook {
    private Map<String,List<String>> nameMap=new HashMap<String,List<String>>(); //для поиска по ФИО
    private Map<String,String> phoneMap=new HashMap<String,String>(); //для поиска по телефону
    public PhoneBook() {
    }
    public String add(String name, String phone){
        if (name==null||phone==null) return "Переданы не верные данные!";
        if (phoneMap.containsKey(phone)) return ("Номер " + phone + " уже закреплен за абонентом: " + phoneMap.get(phone));

        //справочник ФИО
        if (! nameMap.containsKey(name)) nameMap.put(name, new ArrayList<String>());
        List<String> phones = nameMap.get(name);
        phones.add(phone);
        nameMap.put(name,phones);

        //справочник номеров
        phoneMap.put(phone,name);

        return "В книгу добалено: " + name + " с номером " +  phone;
    }
    public String find(String name){
        if (nameMap.containsKey(name)){
            return nameMap.get(name).toString();
        }
        return null;
    }
    public boolean containsPhoneNumber(String phone){
        if (phoneMap.containsKey(phone)){
            return true;
        }
        return false;
    }
}
