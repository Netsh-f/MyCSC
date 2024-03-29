package com.buaa.function;

import com.buaa.data.Student;
import com.buaa.data.User;
import com.buaa.main.UserOperation;

import java.util.ArrayList;

public class ChangeRole extends Function {
    private static final ChangeRole changeRole = new ChangeRole();

    private ChangeRole() {
    }

    public static ChangeRole getInstance() {
        return changeRole;
    }

    @Override
    public void run(ArrayList<String> parameterList) {
        if (parameterList.size() != 0) {
            System.out.println("arguments illegal");
        } else {
            User currentUser = UserOperation.getCurrentUser();
            if (UserOperation.isNoUser()) {
                System.out.println("not logged in");
            } else if (!(currentUser instanceof Student)) {
                System.out.println("permission denied");
            } else if (!((Student) currentUser).isAssistant()) {
                System.out.println("permission denied");
            } else {
                if (UserOperation.isAssistantRole()) {
                    System.out.println("change into Student success");
                } else {
                    System.out.println("change into Assistant success");
                }
                UserOperation.setCurrentCourse(UserOperation.getNoCourse());
                UserOperation.changeRole();
            }
        }
    }
}
