package com.buaa.data;

import java.util.ArrayList;

public class Data {
    private static ArrayList<User> userArrayList = new ArrayList<>();

    public static void addUser(User user) {
        userArrayList.add(user);
    }
}
