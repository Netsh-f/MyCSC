package com.buaa.data;

import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ware {
    private String id;
    private String name;
    private String location;

    public String getLocation() {
        return location;
    }

    public static String wareIdToCourseId(String wareId) {
        Matcher matcher = Pattern.compile("^W(\\d{4})\\d{2}$").matcher(wareId);
        if (matcher.find()) {
            return "C" + matcher.group(1);
        }
        return null;
    }

    public Ware(String id, String name, String location) {
        this.id = id;
        this.name = name;
        this.location = location;
    }

//    public Ware(String id, String name) {
//        this.id = id;
//        this.name = name;
//    }

    public static void listWare(TreeMap<String, Ware> wareTreeMap) {
        if (wareTreeMap.isEmpty()) {
            System.out.println("total 0 ware");
        } else {
            wareTreeMap.forEach((id, ware) -> {
                System.out.println("[ID:" + id +
                        "] [Name:" + ware.getName() + "]");
            });
        }
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

    public static boolean isIdLegal(String wareId, String courseId) {
        Matcher matcher1 = Pattern.compile("^W(\\d{4})\\d{2}$").matcher(wareId);
        Matcher matcher2 = Pattern.compile("^C(\\d{4})$").matcher(courseId);
        if (matcher1.find() && matcher2.find()) {
            return wareId.matches("^W(1[7-9]|2[0-2])([1-9]\\d|0[1-9])([1-9]\\d|0[1-9])$")
                    && matcher2.group(1).equals(matcher1.group(1));
        }
        return false;
    }
}
