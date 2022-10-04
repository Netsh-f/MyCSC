package com.buaa.function;

import com.buaa.data.Course;
import com.buaa.data.Professor;
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
            if (UserOperation.isNoUser()) {
                System.out.println("not logged in");
            } else if (!UserOperation.isManager()) {
                System.out.println("permission denied");
            } else if (UserOperation.isNoCourse()) {
                System.out.println("no course selected");
            } else {
                Task.listTask(currentCourse.getTaskTreeMap());
            }
        }
    }
}
