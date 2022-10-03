package com.buaa.function;

import com.buaa.data.Course;
import com.buaa.data.Data;
import com.buaa.data.Professor;
import com.buaa.data.User;
import com.buaa.main.UserOperation;

import java.util.ArrayList;

public class AddAdmin extends Function {
    private static final AddAdmin addAdmin = new AddAdmin();

    private AddAdmin() {

    }

    public static AddAdmin getInstance() {
        return addAdmin;
    }

    @Override
    public void run(ArrayList<String> parameterList) {
        if (parameterList.size() == 0) {
            System.out.println("arguments illegal");
        } else {
            User currentUser = UserOperation.getCurrentUser();
            if (UserOperation.isNoUser()) {
                System.out.println("not logged in");
            } else if (!(currentUser instanceof Professor)) {
                System.out.println("permission denied");
            } else if (UserOperation.isNoCourse()) {
                System.out.println("no course selected");
            } else {
                boolean legalFlag = true;
                for (String id : parameterList) {
                    if (User.isIdLegal(id)) {
                        System.out.println("user id illegal");
                        legalFlag = false;
                        break;
                    } else if (Data.isIdExist(id)) {
                        System.out.println("user id not exist");
                        legalFlag = false;
                        break;
                    }
                }
                if (legalFlag) {
                    Course currentCourse = UserOperation.getCurrentCourse();
                    for (String id : parameterList) {
                        currentCourse.addAdmin(id);
                    }
                    System.out.println("add admin success");
                }
            }
        }
    }
}
