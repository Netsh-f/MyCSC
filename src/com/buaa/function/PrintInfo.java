package com.buaa.function;

import com.buaa.data.Data;
import com.buaa.data.Student;
import com.buaa.data.User;
import com.buaa.main.UserOperation;

import java.util.ArrayList;

public class PrintInfo extends Function {
    public static PrintInfo printInfo = new PrintInfo();

    @Override
    public void run(ArrayList<String> parameterList) {
        if (!(parameterList.size() == 0 || parameterList.size() == 1)) {
            System.out.println("arguments illegal");
        } else {
            User currentUser = UserOperation.getCurrentUser();
            if (currentUser == UserOperation.getNoUser()) {
                System.out.println("login first");
            } else {
                if (parameterList.size() == 0) {
                    System.out.println(currentUser.toString());
                } else if (parameterList.size() == 1) {
                    String id = parameterList.get(0);
                    if (currentUser instanceof Student) {
                        System.out.println("permission denied");
                    } else if (User.isIdLegal(id) == false) {
                        System.out.println("user id illegal");
                    } else if (Data.isIdExist(id) == false) {
                        System.out.println("user id not exist");
                    } else {
                        System.out.println(Data.getUser(id).toString());
                    }
                }
            }
        }
    }
}
