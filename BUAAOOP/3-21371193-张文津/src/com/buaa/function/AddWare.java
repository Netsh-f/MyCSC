package com.buaa.function;

import com.buaa.data.Course;
import com.buaa.data.Data;
import com.buaa.data.Ware;
import com.buaa.main.UserOperation;
import com.buaa.utils.FileHelper;

import java.util.ArrayList;

public class AddWare extends Function {
    private static final AddWare addWare = new AddWare();

    private AddWare() {
    }

    public static AddWare getInstance() {
        return addWare;
    }

    @Override
    public void run(ArrayList<String> parameterList) {
        if (parameterList.size() != 2) {
            System.out.println("arguments illegal");
        } else {
            Course currentCourse = UserOperation.getCurrentCourse();
            String wareId = parameterList.get(0);
            String sourcePath = parameterList.get(1);
            if (UserOperation.isNoUser()) {
                System.out.println("not logged in");
            } else if (!UserOperation.isManager()) {
                System.out.println("permission denied");
            } else if (UserOperation.isNoCourse()) {
                System.out.println("no course selected");
            } else if (!Ware.isIdLegal(wareId, currentCourse.getId())) {
                System.out.println("ware id illegal");
            } else {
                if (!FileHelper.isFileExist(sourcePath)) {
                    System.out.println("ware file does not exist");
                } else {
                    try {
                        String sourceFileName = FileHelper.getFileName(sourcePath);
                        String destPath = "./data/" + Ware.wareIdToCourseId(wareId) + "/wares/" +
                                wareId + "_" + sourceFileName;
                        try {
                            FileHelper.copyFile(sourcePath, destPath);
                        } catch (Exception e) {
                            System.out.println("ware file operation failed");
                            return;
                        }
                        Ware ware = new Ware(wareId, sourceFileName, destPath);
                        Data.addWare(ware);
                        currentCourse.addWare(ware);
                        System.out.println("add ware success");
                    } catch (Exception e) {
                        System.out.println("unexpected error");
                    }
                }
            }
        }
    }
}
