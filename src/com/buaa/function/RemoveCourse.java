package com.buaa.function;

import com.buaa.data.Course;
import com.buaa.data.Data;
import com.buaa.data.Professor;
import com.buaa.data.User;
import com.buaa.main.UserOperation;

import java.util.ArrayList;

public class RemoveCourse extends Function {
    private static final RemoveCourse removeCourse = new RemoveCourse();

    private RemoveCourse() {
    }

    public static RemoveCourse getInstance() {
        return removeCourse;
    }

    @Override
    public void run(ArrayList<String> parameterList) {
        if (parameterList.size() != 1) {
            System.out.println("arguments illegal");
        } else {
            User currentUser = UserOperation.getCurrentUser();
            String id = parameterList.get(0);
            if (UserOperation.isNoUser()) {
                System.out.println("not logged in");
            } else if (!UserOperation.isProfessor()) {
                System.out.println("permission denied");
            } else if (!Course.isIdLegal(id)) {
                System.out.println("course id illegal");
            } else if (!((Professor) currentUser).isCourseIdExist(id)) {
                System.out.println("course id not exist");
            } else {
                ((Professor) currentUser).removeManagerCourse(id);
                Data.removeCourse(id);
                if(id.equals(UserOperation.getCurrentCourse().getId())){
                    UserOperation.setCurrentCourse(UserOperation.getNoCourse());
                }
                System.out.println("remove course success");
            }
        }
    }
}
