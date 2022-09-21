package com.buaa.data;

import com.buaa.main.UserOperation;

import java.util.ArrayList;
import java.util.HashMap;

public class Data {
//    private static ArrayList<User> userArrayList = new ArrayList<>();
    private static HashMap<String, User> userHashMap = new HashMap<String, User>();

    public static void addUser(User user) {
//        userArrayList.add(user);
        userHashMap.put(user.getId(), user);
    }

    public static boolean isIdExist(String id) {
//        for (User registeredUser : userArrayList) {
//            if (registeredUser.getId().equals(id)) {
//                return true;
//            }
//        }
//        return false;
        return userHashMap.containsKey(id);
    }

    public static User getUser(String id) {
//        for (User registeredUser : userArrayList) {
//            if (registeredUser.getId().equals(id)) {
//                return registeredUser;
//            }
//        }
//        return UserOperation.getNoUser();
        return userHashMap.get(id);
    }
}
