package com.buaa.data;

import java.util.ArrayList;

public class Data {
    private static ArrayList<User> userArrayList = new ArrayList<>();

    public static void addUser(User user) {
        userArrayList.add(user);
    }

    public static boolean isIdDuplication(String id){
        for (User registeredUser: userArrayList) {
            if(registeredUser.getId().equals(id)){
                return true;
            }
        }
        return false;
    }
}
