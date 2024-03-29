package com.buaa.function;

import com.buaa.data.Course;
import com.buaa.data.Student;
import com.buaa.data.Task;
import com.buaa.data.User;
import com.buaa.main.UserOperation;

import java.util.ArrayList;

public class ListTask extends Function {
    private static final ListTask listTask = new ListTask();

    private ListTask() {
    }

    public static ListTask getInstance() {
        return listTask;
    }

    @Override
    public void run(ArrayList<String> parameterList) {
        if (parameterList.size() != 0) {
            System.out.println("arguments illegal");
        } else {
            Course currentCourse = UserOperation.getCurrentCourse();
            User currentUser = UserOperation.getCurrentUser();
            if (UserOperation.isNoUser()) {
                System.out.println("not logged in");
            } else if (UserOperation.isNoCourse()) {
                System.out.println("no course selected");
            } else {
                if (UserOperation.isManager()) {
                    Task.managerListTask(currentCourse.getTaskTreeMap(), currentCourse);
                } else {
                    Task.studentListTask(currentCourse.getTaskTreeMap(), (Student) currentUser);
                }
            }
        }
    }
}
