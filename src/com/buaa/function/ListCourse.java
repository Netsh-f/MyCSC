package com.buaa.function;

import com.buaa.data.Course;
import com.buaa.data.Professor;
import com.buaa.data.Student;
import com.buaa.data.User;
import com.buaa.main.UserOperation;

import java.util.ArrayList;
import java.util.TreeMap;

public class ListCourse extends Function {
    private static final ListCourse listCourse = new ListCourse();

    private ListCourse() {
    }

    public static ListCourse getInstance() {
        return listCourse;
    }

    @Override
    public void run(ArrayList<String> parameterList) {
        if (parameterList.size() != 0) {
            System.out.println("arguments illegal");
        } else {
            User currentUser = UserOperation.getCurrentUser();
            if (UserOperation.isNoUser()) {
                System.out.println("not logged in");
            } else {
                TreeMap treeMap;
                if (UserOperation.isManager()) {
                    treeMap = currentUser.getManagerCourseTreeMap();
                } else {
                    treeMap = ((Student) currentUser).getStudentCourseTreeMap();
                }
                if (treeMap.isEmpty()) {
                    System.out.println("course not exist");
                } else {
                    Course.listCourse(treeMap);
                }
            }
        }
    }
}
