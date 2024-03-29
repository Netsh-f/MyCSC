package com.buaa.function;

import com.buaa.data.*;
import com.buaa.main.UserOperation;
import com.buaa.utils.FileHelper;

import java.util.ArrayList;

public class RemoveTask extends Function {
    private static final RemoveTask removeTask = new RemoveTask();

    private RemoveTask() {
    }

    public static RemoveTask getInstance() {
        return removeTask;
    }

    @Override
    public void run(ArrayList<String> parameterList) {
        if (parameterList.size() != 1) {
            System.out.println("arguments illegal");
        } else {
            Course currentCourse = UserOperation.getCurrentCourse();
            String taskId = parameterList.get(0);
            if (UserOperation.isNoUser()) {
                System.out.println("not logged in");
            } else if (!UserOperation.isManager()) {
                System.out.println("permission denied");
            } else if (UserOperation.isNoCourse()) {
                System.out.println("no course selected");
            } else if (!currentCourse.isTaskIdExist(taskId)) {
                System.out.println("task not found");
            } else {
                try {
                    String filePath = currentCourse.getTask(taskId).getFilePath();
                    FileHelper.deleteFile(filePath);
                    Data.removeTask(taskId);
                    currentCourse.removeTask(taskId);
                    System.out.println("remove task success");
                }catch (Exception e){
                    System.out.println("delete file failed");
                }
            }
        }
    }
}
