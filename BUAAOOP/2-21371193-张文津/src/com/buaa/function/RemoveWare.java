package com.buaa.function;

import com.buaa.data.*;
import com.buaa.main.UserOperation;

import java.util.ArrayList;

public class RemoveWare extends Function {
    private static final RemoveWare removeWare = new RemoveWare();

    private RemoveWare() {
    }

    public static RemoveWare getInstance() {
        return removeWare;
    }

    @Override
    public void run(ArrayList<String> parameterList) {
        if (parameterList.size() != 1) {
            System.out.println("arguments illegal");
        } else {
            User currentUser = UserOperation.getCurrentUser();
            Course currentCourse = UserOperation.getCurrentCourse();
            String id = parameterList.get(0);
            if (UserOperation.isNoUser()) {
                System.out.println("not logged in");
            } else if (!UserOperation.isProfessor()) {
                System.out.println("permission denied");
            } else if (UserOperation.isNoCourse()) {
                System.out.println("no course selected");
            } else if (!Ware.isIdLegal(id, currentCourse.getId())) {
                System.out.println("ware id illegal");
            } else if (!currentCourse.isWareIdExist(id)) {
                System.out.println("ware id not exist");
            } else {
                currentCourse.removeWare(id);
                Data.removeWare(id);
                System.out.println("remove ware success");
            }
        }
    }
}
