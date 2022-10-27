package com.buaa.function;

import com.buaa.data.*;
import com.buaa.main.UserOperation;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.ArrayList;

public class AddTask extends Function {
    private static final AddTask addTask = new AddTask();

    private AddTask() {
    }

    public static AddTask getInstance() {
        return addTask;
    }

    @Override
    public void run(ArrayList<String> parameterList) {
        if (parameterList.size() != 4) {
            System.out.println("arguments illegal");
        } else {
            Course currentCourse = UserOperation.getCurrentCourse();
            String taskId = parameterList.get(0);
            String location = parameterList.get(1);
            String startTime = parameterList.get(2);
            String endTime = parameterList.get(3);
            if (UserOperation.isNoUser()) {
                System.out.println("not logged in");
            } else if (!UserOperation.isManager()) {
                System.out.println("permission denied");
            } else if (UserOperation.isNoCourse()) {
                System.out.println("no course selected");
            } else if (!Task.isIdLegal(taskId, currentCourse.getId())) {
                System.out.println("task id illegal");
            } else if (!Task.isTimeLegal(startTime, endTime)) {
                System.out.println("task time illegal");
            } else {
                try {
                    File inFile = new File(location);
                    File rootOutFile = new File("./data/" + currentCourse.getId() + "/tasks/" + taskId);
                    if (!inFile.exists()) {
                        System.out.println("task file not found");
                    } else {
                        if (!rootOutFile.exists()) {
                            rootOutFile.mkdirs();
                        }
                        OutputStream out = new FileOutputStream(rootOutFile + "/" + inFile.getName());
                        Files.copy(inFile.toPath(), out);
                        Task task = new Task(taskId, inFile.getName(), startTime, endTime, rootOutFile + "/" + inFile.getName());
                        Data.addTask(task);
                        currentCourse.addTask(task);
                        System.out.println("add task success");
                    }
                } catch (Exception e) {
                    System.out.println("file operation failed");
                }
            }
        }
    }
}
