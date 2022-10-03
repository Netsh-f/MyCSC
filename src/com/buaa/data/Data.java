package com.buaa.data;

import java.util.HashMap;
import java.util.TreeMap;

public class Data {
    private static final HashMap<String, User> userHashMap = new HashMap<>();
    private static final TreeMap<String, Course> courseTreeMap = new TreeMap<>();

    public static void addCourse(Course course) {
        courseTreeMap.put(course.getId(), course);
    }

    public static void removeCourse(String id) {
        courseTreeMap.remove(id);
    }

    public static void addUser(User user) {
        userHashMap.put(user.getId(), user);
    }

    public static boolean isIdExist(String id) {
        return userHashMap.containsKey(id);
    }

    public static User getUser(String id) {
        return userHashMap.get(id);
    }
}
