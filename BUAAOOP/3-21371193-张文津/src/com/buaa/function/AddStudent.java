package com.buaa.function;

import com.buaa.data.*;
import com.buaa.main.UserOperation;

import java.util.ArrayList;

public class AddStudent extends Function {
    private static final AddStudent addStudent = new AddStudent();

    private AddStudent() {
    }

    public static AddStudent getInstance() {
        return addStudent;
    }

    @Override
    public void run(ArrayList<String> parameterList) {
        if (parameterList.size() == 0) {
            System.out.println("arguments illegal");
        } else {
            User currentUser = UserOperation.getCurrentUser();
            Course currentCourse = UserOperation.getCurrentCourse();
            if (UserOperation.isNoUser()) {
                System.out.println("not logged in");
            } else if (!UserOperation.isManager()) {
                System.out.println("permission denied");
            } else if (UserOperation.isNoCourse()) {
                System.out.println("no course selected");
            } else {
                boolean legalFlag = true;
                for (String id : parameterList) {
                    if (!User.isIdLegal(id)) {
                        System.out.println("user id illegal");
                        legalFlag = false;
                        break;
                    } else if (!Data.isUserIdExist(id)) {
                        System.out.println("user id not exist");
                        legalFlag = false;
                        break;
                    } else if (Professor.isTeacher(id)) {
                        System.out.println("I'm professor rather than student!");
                        legalFlag = false;
                        break;
                    }
                }
                if (legalFlag) {
                    for (String id : parameterList) {
                        currentCourse.addStudent(Data.getUser(id));
                        ((Student) Data.getUser(id)).addStudentCourse(currentCourse);
                    }
                    System.out.println("add student success");
                }
            }
        }
    }
}
