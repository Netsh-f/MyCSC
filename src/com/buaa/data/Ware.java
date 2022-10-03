package com.buaa.data;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ware {
    private String id;
    private String name;

    public Ware(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public static boolean isNameLegal(String name){
        return name.matches("^\\w+\\.[A-Za-z0-9]+$");
    }

    public static boolean isIdLegal(String id) {
        return id.matches("^W(1[7-9]|2[0-2])([1-9]\\d|0[1-9])([1-9]\\d|0[1-9])$");
    }

    public String getCourseId(String id) {
        Pattern pattern = Pattern.compile("^W(\\d{4})\\d{2}$");
        Matcher matcher = pattern.matcher(id);
        if(matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }
}
