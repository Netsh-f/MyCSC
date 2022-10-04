package com.buaa.function;

import com.buaa.data.Course;
import com.buaa.data.Professor;
import com.buaa.data.User;
import com.buaa.main.UserOperation;

import java.util.ArrayList;

public class SelectCourse extends Function {
    private static final SelectCourse selectCourse = new SelectCourse();

    private SelectCourse() {

    }

    public static SelectCourse getInstance() {
        return selectCourse;
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
            } else if (!UserOperation.isManager()) {
                System.out.println("permission denied");
            } else if (!Course.isIdLegal(id)) {
                System.out.println("course id illegal");
            } else if (!currentUser.isCourseIdExist(id)) {
                System.out.println("course id not exist");
            } else {
                UserOperation.setCurrentCourse(currentUser.getCourse(id));//从当前用户的课程中获取课程，给当前课程
                System.out.println("select course success");
            }
        }
    }
}
