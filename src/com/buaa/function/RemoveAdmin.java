package com.buaa.function;

import com.buaa.data.Course;
import com.buaa.data.Professor;
import com.buaa.data.User;
import com.buaa.main.UserOperation;

import java.util.ArrayList;

public class RemoveAdmin extends Function {
    private static final RemoveAdmin removeAdmin = new RemoveAdmin();

    private RemoveAdmin() {
    }

    public static RemoveAdmin getInstance() {
        return removeAdmin;
    }

    @Override
    public void run(ArrayList<String> parameterList) {
        if (parameterList.size() != 1) {
            System.out.println("arguments illegal");
        } else {
            User currentUser = UserOperation.getCurrentUser();
            Course currentCourse = UserOperation.getCurrentCourse();
            String id = parameterList.get(0);
            if (UserOperation.isNoUser()) {
                System.out.println("not logged in");
            } else if (!(currentUser instanceof Professor)) {
                System.out.println("permission denied");
            } else if (UserOperation.isNoCourse()) {
                System.out.println("no course selected");
            } else if (!Course.isIdLegal(id)) {
                System.out.println("user id illegal");
            } else if (!currentCourse.isAdminIdExist(id)) {
                System.out.println("user id not exist");
            } else {
                currentCourse.removeAdmin(id);
                System.out.println("remove admin success");
            }
        }
    }
}
