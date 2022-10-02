package com.buaa.data;

import java.util.HashMap;

public class Data {
    private static HashMap<String, User> userHashMap = new HashMap<String, User>();

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
