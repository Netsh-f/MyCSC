package com.buaa.function;

import com.buaa.data.Course;
import com.buaa.main.UserOperation;

import java.util.ArrayList;

public class QueryScore extends Function {
    private static QueryScore queryScore = new QueryScore();

    private QueryScore() {
    }

    public static final QueryScore getInstance() {
        return queryScore;
    }

    @Override
    public void run(ArrayList<String> parameterList) {
        if (!(parameterList.size() == 1 || parameterList.size() == 2)) {
            System.out.println("arguments illegal");
        } else {
            String taskId = null;
            String studentId = null;
            Course currentCourse = UserOperation.getCurrentCourse();
            if (parameterList.size() == 2) {
                taskId = parameterList.get(0);
                studentId = parameterList.get(1);
            } else {
                if (parameterList.get(0).matches("^T\\w*$")) {
                    taskId = parameterList.get(0);
                } else {
                    studentId = parameterList.get(0);
                }
            }

            if (UserOperation.isNoUser()) {
                System.out.println("not logged in");
            } else if (UserOperation.isNoCourse()) {
                System.out.println("no course selected");
            } else if ((!UserOperation.isManager()) && studentId != null) {
                System.out.println("permission denied");
            } else if (taskId != null && !currentCourse.isTaskIdExist(taskId)) {
                System.out.println("task not found");
            } else if (studentId != null && !currentCourse.isStudentIdExist(studentId)) {
                System.out.println("student not found");
            } else {
                int num = Course.queryScoreNum(currentCourse.getTaskTreeMap(), taskId, studentId);
                if (num <= 1) {
                    System.out.println("total " + num + " result");
                } else {
                    System.out.println("total " + num + " results");
                }
                Course.queryScore(currentCourse.getTaskTreeMap(), taskId, studentId);
            }
        }
    }
}
