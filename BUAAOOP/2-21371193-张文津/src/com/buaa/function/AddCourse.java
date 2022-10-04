package com.buaa.function;

import com.buaa.data.Course;
import com.buaa.data.Data;
import com.buaa.data.Professor;
import com.buaa.data.User;
import com.buaa.main.UserOperation;

import java.util.ArrayList;

public class AddCourse extends Function {
    private static final AddCourse addCourse = new AddCourse();

    private AddCourse() {
    }

    public static AddCourse getInstance() {
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
            if (UserOperation.isNoUser()) {
                System.out.println("not logged in");
            } else if (!UserOperation.isProfessor()) {
                System.out.println("permission denied");
            } else if (!(Course.isIdLegal(id))) {
                System.out.println("course id illegal");
            } else if (((Professor) currentUser).isCourseIdExist(id)) {
                System.out.println("course id duplication");
            } else if (!(Course.isNameLegal(name))) {
                System.out.println("course name illegal");
            } else {
                Course course = new Course(id, name);
                ((Professor) currentUser).addCourse(course);
                course.addAdmin(currentUser.getId());
                Data.addCourse(course);
                System.out.println("add course success");
            }
        }
    }
}
//register 10000 Ma Te 10000@buaa.cn a1111111 a1111111
//login 10000 a1111111
//addCourse C2121 aaaaaa
//addCourse C2122 aaaaaa
//addCourse C2021 aaaaaa
//addCourse C1721 aaaaaa
//register 21371193 Wenjin Zhang 21371193@buaa.cn a1111111 a1111111
//login 21371193 a1111111
//addCourse C2121 abc