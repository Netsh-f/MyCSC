package com.buaa.data;

import com.buaa.main.UserOperation;

import java.util.ArrayList;

public class Data {
    private static ArrayList<User> userArrayList = new ArrayList<>();

    public static void addUser(User user) {
        userArrayList.add(user);
    }

    public static boolean isIdExist(String id) {
        for (User registeredUser : userArrayList) {
            if (registeredUser.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public static User getUser(String id) {
        for (User registeredUser : userArrayList) {
            if (registeredUser.getId().equals(id)) {
                return registeredUser;
            }
        }
        return UserOperation.getNoUser();
    }
}
