package com.buaa.function;

import com.buaa.data.*;
import com.buaa.main.UserOperation;

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
        if(parameterList.size()!=1){
            System.out.println("arguments illegal");
        }else{
            User currentUser = UserOperation.getCurrentUser();
            Course currentCourse = UserOperation.getCurrentCourse();
            String id = parameterList.get(0);
            if(UserOperation.isNoUser()){
                System.out.println("not logged in");
            }else if(!UserOperation.isManager()){
                System.out.println("permission denied");
            }else if(UserOperation.isNoCourse()){
                System.out.println("no course selected");
            }else if(!Task.isIdLegal(id, currentCourse.getId())){
                System.out.println("task id illegal");
            }else if(!currentCourse.isTaskIdExist(id)){
                System.out.println("task id not exist");
            }else{
                Data.removeTask(id);
                currentCourse.removeTask(id);
                System.out.println("remove task success");
            }
        }
    }
}
