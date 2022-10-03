package com.buaa.function;

import com.buaa.data.Course;
import com.buaa.data.Professor;
import com.buaa.data.User;
import com.buaa.main.UserOperation;

import java.util.ArrayList;

public class AddCourse extends Function {
    private static final AddCourse addCourse = new AddCourse();

    private AddCourse() {
    }

    public static AddCourse getInstance(){
        return addCourse;
    }

    @Override
    public void run(ArrayList<String> parameterList) {
        if (parameterList.size() != 2) {
            System.out.println("arguments illegal");
        } else {
            User currentUser = UserOperation.getCurrentUser();
            String id = parameterList.get(0);
            String name = parameterList.get(1);
            if (currentUser == UserOperation.getNoUser()) {
                System.out.println("not logged in");
            } else if (!(currentUser instanceof Professor)) {
                System.out.println("permission denied");
            } else if (!Course.isIdLegal(id)) {
                System.out.println("course id illegal");
            } else if (((Professor) currentUser).isCourseIdExist(id)) {
                System.out.println("course id duplication");
            } else if (Course.isNameLegal(name)) {
                System.out.println("course name illegal");
            } else {
                System.out.println("add course success");
                ((Professor) currentUser).addCourse(new Course(id, name));
            }
        }
    }
}
