package com.buaa.function;

import com.buaa.data.*;
import com.buaa.main.UserOperation;
import com.buaa.utils.FileHelper;

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
            String wareId = parameterList.get(0);
            if (UserOperation.isNoUser()) {
                System.out.println("not logged in");
            } else if (!UserOperation.isManager()) {
                System.out.println("permission denied");
            } else if (UserOperation.isNoCourse()) {
                System.out.println("no course selected");
            } else if (!currentCourse.isWareIdExist(wareId)) {
                System.out.println("ware not found");
            } else {
                try {
                    String filePath = currentCourse.getWare(wareId).getFilePath();
                    FileHelper.deleteFile(filePath);
                    Data.removeWare(wareId);
                    currentCourse.removeWare(wareId);
                    System.out.println("remove ware success");
                } catch (Exception e) {
                    System.out.println("delete file failed");
                }
            }
        }
    }
}
