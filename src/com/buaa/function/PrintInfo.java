package com.buaa.function;

import com.buaa.data.Data;
import com.buaa.data.Professor;
import com.buaa.data.User;
import com.buaa.main.UserOperation;

import java.util.ArrayList;

public class PrintInfo extends Function {
    private static final PrintInfo printInfo = new PrintInfo();

    private PrintInfo() {
    }

    public static PrintInfo getInstance() {
        return printInfo;
    }

    @Override
    public void run(ArrayList<String> parameterList) {
        if (!(parameterList.size() == 0 || parameterList.size() == 1)) {
            System.out.println("arguments illegal");
        } else {
            if (UserOperation.isNoUser()) {
                System.out.println("login first");
            } else {
                User currentUser = UserOperation.getCurrentUser();
                if (parameterList.size() == 0) {
                    System.out.println(currentUser.toString());
                } else {
                    String id = parameterList.get(0);
                    if (!(currentUser instanceof Professor)) {
                        System.out.println("permission denied");
                    } else if (!User.isIdLegal(id)) {
                        System.out.println("user id illegal");
                    } else if (!Data.isUserIdExist(id)) {
                        System.out.println("user id not exist");
                    } else {
                        System.out.println(Data.getUser(id).toString());
                    }
                }
            }
        }
    }
}
