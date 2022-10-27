package com.buaa.function;

import com.buaa.data.Course;
import com.buaa.data.Student;
import com.buaa.data.User;
import com.buaa.main.UserOperation;

import java.util.ArrayList;
import java.util.TreeMap;

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
            } else if (!Course.isIdLegal(id)) {
                System.out.println("course id illegal");
            } else {
                TreeMap<String, Course> courseTreeMap;
                if (UserOperation.isManager()) {
                    courseTreeMap = currentUser.getManagerCourseTreeMap();
                } else {
                    courseTreeMap = ((Student) currentUser).getStudentCourseTreeMap();
                }
                if (courseTreeMap.isEmpty()) {
                    System.out.println("course id not exist");
                } else {
                    UserOperation.setCurrentCourse(courseTreeMap.get(id));
                    System.out.println("select course success");
                }
            }
        }
    }
}
