package com.buaa.data;

public class Course {
    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Course(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public static boolean isIdLegal(String id) {
        return id.matches("C(1[7-9])|(2[0-2])([1-9]\\d)|(0[1-9])");
    }

    public static boolean isNameLegal(String name) {
        return name.matches("\\w{6,16}");
    }
}
