package com.buaa.function;

import com.buaa.data.*;
import com.buaa.main.UserOperation;

import java.io.File;
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
            Course currentCourse = UserOperation.getCurrentCourse();
            String id = parameterList.get(0);
            if (UserOperation.isNoUser()) {
                System.out.println("not logged in");
            } else if (!UserOperation.isManager()) {
                System.out.println("permission denied");
            } else if (UserOperation.isNoCourse()) {
                System.out.println("no course selected");
            } else if (!currentCourse.isWareIdExist(id)) {
                System.out.println("ware not found");
            } else {
                try {
                    File fileToDelete = new File(currentCourse.getWareTreeMap().get(id).getLocation());
                    if (fileToDelete.delete()) {
                        currentCourse.removeWare(id);
                        Data.removeWare(id);
                        System.out.println("remove ware success");
                    } else {
                        throw new Exception();
                    }
                } catch (Exception e) {
                    System.out.println("delete file failed");
                }
            }
        }
    }
}
