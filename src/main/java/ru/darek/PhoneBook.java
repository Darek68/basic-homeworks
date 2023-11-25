package ru.darek;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PhoneBook {
    private Map<String,List<Long>> nameMap=new HashMap<String,List<Long>>(); //для поиска по ФИО
    private Map<Long,String> phoneMap=new HashMap<Long,String>(); //для поиска по телефону
    public PhoneBook() {
    }
    public String add(String name, Long phone){
        if (name==null||phone==null) return "Переданы не верные данные!";
        if (phoneMap.containsKey(phone)) return ("Номер " + phone + " уже закреплен за абонентом: " + phoneMap.get(phone));

        //справочник ФИО
        if (! nameMap.containsKey(name)) nameMap.put(name, new ArrayList<Long>());
        List<Long> phones = nameMap.get(name);
        phones.add(phone);
        nameMap.put(name,phones);

        //справочник номеров
        phoneMap.put(phone,name);

        return "В книгу добалено: " + name + " с номером " +  phone;
    }
    public String find(String name){
        if (nameMap.containsKey(name)){
            return name  + ": " +nameMap.get(name).toString();
        }
        return name + " - нет такой записи в книжке";
    }
    public String containsPhoneNumber(Long phone){
        if (phoneMap.containsKey(phone)){
            return "Номер " + phone + " закреплен за абонентом:  " + phoneMap.get(phone).toString();
        }
        return "Номер " + phone + " отсутствует в справочнике";
    }
}
