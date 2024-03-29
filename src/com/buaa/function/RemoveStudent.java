package com.buaa.function;

import com.buaa.data.*;
import com.buaa.main.UserOperation;

import java.util.ArrayList;

public class RemoveStudent extends Function {
    private static final RemoveStudent removeStudent = new RemoveStudent();

    private RemoveStudent() {
    }

    public static RemoveStudent getInstance() {
        return removeStudent;
    }

    @Override
    public void run(ArrayList<String> parameterList) {
        if (parameterList.size() != 1) {
            System.out.println("arguments illegal");
        } else {
            Course currentCourse = UserOperation.getCurrentCourse();
            String id = parameterList.get(0);
            if (UserOperation.isNoUser()) {
                System.out.println("not logged in");
            } else if (!UserOperation.isManager()) {
                System.out.println("permission denied");
            } else if (UserOperation.isNoCourse()) {
                System.out.println("no course selected");
            } else if (!User.isIdLegal(id)) {
                System.out.println("user id illegal");
            } else if (!currentCourse.isStudentIdExist(id)) {
                System.out.println("user id not exist");
            } else {
                currentCourse.removeStudent(id);
                ((Student) Data.getUser(id)).removeStudentCourse(currentCourse.getId());
                System.out.println("remove student success");
            }
        }
    }
}
