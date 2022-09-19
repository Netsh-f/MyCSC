package com.buaa.function;

import com.buaa.main.UserOperation;

import java.util.ArrayList;

public class Logout extends Function {
    public static Logout logout = new Logout();

    @Override
    public void run(ArrayList<String> parameterList) {
        if (parameterList.size() != 0) {
            System.out.println("arguments illegal");
        } else {
            if (UserOperation.getCurrentUser() == UserOperation.getNoUser()) {
                System.out.println("not logged in");
            } else {
                UserOperation.setCurrentUser(UserOperation.getNoUser());
                System.out.println("Bye~");
            }
        }
    }
}
