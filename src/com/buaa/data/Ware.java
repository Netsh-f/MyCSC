package com.buaa.data;

import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ware {
    private String id;
    private String name;

    public Ware(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public static void listWare(TreeMap<String, Ware> wareTreeMap) {
        wareTreeMap.forEach((id, ware) -> {
            System.out.println("[ID:" + id +
                    "] [Name:" + ware.getName() + "]");
        });
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public static boolean isNameLegal(String name) {
        return name.matches("^\\w+\\.[A-Za-z0-9]+$") && name.matches("^[\\w\\.]{6,16}$");
    }

    public static boolean isIdLegal(String id, String courseId) {
        Pattern pattern = Pattern.compile("^W(\\d{4})\\d{2}$");
        Matcher matcher = pattern.matcher(id);
        if (matcher.find()) {
            return id.matches("^W(1[7-9]|2[0-2])([1-9]\\d|0[1-9])([1-9]\\d|0[1-9])$")
                    && courseId.equals(matcher.group(1));
        }
        return false;
    }
}
