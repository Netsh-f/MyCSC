package com.buaa.data;

import java.util.HashMap;
import java.util.TreeMap;

public class Data {
    private static final HashMap<String, User> userHashMap = new HashMap<>();
    private static final HashMap<String, Course> courseHashMap = new HashMap<>();
    private static final HashMap<String, Ware> wareHashMap = new HashMap<>();

    public static Course getCourse(String id) {
        return courseHashMap.get(id);
    }

    public static void addWare(Ware ware) {
        wareHashMap.put(ware.getId(), ware);
    }

    public static boolean isWareIdExist(String id) {
        return wareHashMap.containsKey(id);
    }

    public static void addCourse(Course course) {
        courseHashMap.put(course.getId(), course);
    }

    public static void removeCourse(String id) {
        courseHashMap.remove(id);
    }

    public static void addUser(User user) {
        userHashMap.put(user.getId(), user);
    }

    public static boolean isUserIdExist(String id) {
        return userHashMap.containsKey(id);
    }

    public static User getUser(String id) {
        return userHashMap.get(id);
    }
}
