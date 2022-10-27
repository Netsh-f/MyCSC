package com.buaa.function;

import com.buaa.data.Course;
import com.buaa.data.Ware;
import com.buaa.main.UserOperation;

import java.io.*;
import java.nio.file.Files;
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
            try {
                Course currentCourse = UserOperation.getCurrentCourse();
                String id = parameterList.get(0);
                String inLocation = parameterList.get(1);
                if (UserOperation.isNoUser()) {
                    System.out.println("not logged in");
                } else if (!UserOperation.isManager()) {
                    System.out.println("permission denied");
                } else if (UserOperation.isNoCourse()) {
                    System.out.println("no course selected");
                } else if (!Ware.isIdLegal(id, currentCourse.getId())) {
                    System.out.println("ware id illegal");
                } else {
                    File inFile = new File(inLocation);
                    File rootOutFile = new File("./data/" + id + "/wares");
                    if (!inFile.exists()) {
                        System.out.println("ware file does not exist");
                    } else {
                        if (!rootOutFile.exists()) {
                            rootOutFile.mkdirs();
                        }
                        OutputStream out = new FileOutputStream(rootOutFile + "/" + inFile.getName());
                        try {
                            Files.copy(inFile.toPath(), out);
                            System.out.println("add ware success");
                        } catch (Exception e) {
                            System.out.println("ware file operation failed");
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println("unexpected error");
            }
//            } else if (Data.isWareIdExist(id)) {
//                System.out.println("ware id duplication");
//            } else if (!Ware.isNameLegal(name)) {
//                System.out.println("ware name illegal");
//            } else {
//                Ware ware = new Ware(id, name);
//                Data.addWare(ware);
//                currentCourse.addWare(ware);
//                System.out.println("add ware success");
//            }
        }
    }
}
