package com.buaa.function;

import com.buaa.data.*;
import com.buaa.main.UserOperation;

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
        if(parameterList.size()!=4){
            System.out.println("arguments illegal");
        }else{
            User currentUser = UserOperation.getCurrentUser();
            Course currentCourse = UserOperation.getCurrentCourse();
            String id = parameterList.get(0);
            String name = parameterList.get(1);
            String startTime = parameterList.get(2);
            String endTime = parameterList.get(3);
            if(UserOperation.isNoUser()){
                System.out.println("not logged in");
            }else if(!UserOperation.isManager()){
                System.out.println("permission denied");
            }else if(UserOperation.isNoCourse()){
                System.out.println("no course selected");
            }else if(!Task.isIdLegal(id, currentCourse.getId())){
                System.out.println("task id illegal");
            }else if(Data.isTaskIdExist(id)){
                System.out.println("task id duplication");
            }else if(!Task.isNameLegal(name)){
                System.out.println("task name illegal");
            }else if(!Task.isTimeLegal(startTime, endTime)){
                System.out.println("task time illegal");
            }else{
                Task task = new Task(id, name, startTime, endTime);
                Data.addTask(task);
                currentCourse.addTask(task);
                System.out.println("add task success");
            }
        }
    }
}
