package com.buaa.function;

import com.buaa.data.*;
import com.buaa.main.UserOperation;
import com.buaa.utils.FileHelper;

import java.util.ArrayList;
import java.util.Scanner;

public class SubmitTask extends Function {
    public static SubmitTask submitTask = new SubmitTask();

    private SubmitTask() {
    }

    public static final SubmitTask getInstance() {
        return submitTask;
    }

    @Override
    public void run(ArrayList<String> parameterList) {
        String sourcePath = null;
        String taskId;
        boolean redirectFlag = false;
        String redirectPath = null;
        int redirectPos = 0;

        Course currentCourse = UserOperation.getCurrentCourse();
        User currentUser = UserOperation.getCurrentUser();

        for (int i = 0; i < parameterList.size(); i++) {
            if (parameterList.get(i).matches("^<$")) {
                redirectFlag = true;
                redirectPos = i;
                break;
            }
        }

        if (redirectFlag) {
            if (redirectPos + 1 >= parameterList.size()) {
                System.out.println("please input the path to redirect the file");
                return;
            } else {
                redirectPath = parameterList.get(redirectPos);
                parameterList.remove(redirectPos);
                parameterList.remove(redirectPos);//把重定向符号和后面的路径删除
            }
        }

        if (!((parameterList.size() == 1 && redirectFlag) || (parameterList.size() == 2))) {
            System.out.println("arguments illegal");
        } else {
            if (parameterList.size() == 1) {
                taskId = parameterList.get(0);
            } else {
                sourcePath = parameterList.get(0);
                taskId = parameterList.get(1);
            }
            if (UserOperation.isNoUser()) {
                System.out.println("not logged in");
            } else if (UserOperation.isManager()) {//如果不是学生（是老师或助教端）
                System.out.println("operation not allowed");
            } else if (UserOperation.isNoCourse()) {
                System.out.println("no course selected");
            } else if (!currentCourse.isTaskIdExist(taskId)) {
                System.out.println("task not found");
            } else {
                try {
                    if (parameterList.size() == 1) {//用了重定向（且可选参数1没有使用）
                        sourcePath = redirectPath;
                    }
                    //包含了重定向但被覆盖
                    if (FileHelper.isFileExist(sourcePath)) {
                        Scanner scanner = new Scanner(System.in);
                        System.out.println("task already exists, do you want to overwrite it? (y/n)");
                        String input = scanner.nextLine();
                        if (!input.matches("^[yY]$")) {
                            System.out.println("submit canceled");
                            return;
                        }
                    }

                    String destPath = "./data/" + currentCourse.getId() + "/tasks/" + taskId + "/" +
                            currentUser.getId() + ".task";
                    FileHelper.copyFile(sourcePath, destPath);

                    Work work = new Work(currentUser.getId(), destPath);
                    Task currentTask = currentCourse.getTask(taskId);
                    currentTask.addWrok(work);
                    Data.addWork(work);

                    double score = currentCourse.getTask(taskId).evaluateWork(work);
                    work.setScore(score);
                    String scoreString;
                    if (score == -1) {
                        scoreString = "None";
                    } else {
                        scoreString = String.format("%.1f", score);
                    }
                    System.out.println("submit success\n" +
                            "your score is: " + scoreString);
                } catch (Exception e) {
                    System.out.println("file operation failed");
                }
            }
        }
    }
}
