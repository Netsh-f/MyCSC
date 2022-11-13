package com.buaa.function;

import com.buaa.data.Student;
import com.buaa.data.VM.VirtualMachine;
import com.buaa.main.UserOperation;
import com.buaa.utils.FileHelper;

import java.util.ArrayList;

public class UploadVM extends Function {
    private static final UploadVM uploadVM = new UploadVM();

    private UploadVM() {
    }

    public static final UploadVM getInstance() {
        return uploadVM;
    }

    @Override
    public void run(ArrayList<String> parameterList) {
        if (parameterList.size() != 1) {
            System.out.println("arguments illegal");
        } else if (UserOperation.isNoUser()) {
            System.out.println("not logged in");
        } else if (UserOperation.isManager()) {
            System.out.println("permission denied");//原题此要求优先级位置未说明
        } else if (UserOperation.isNoCourse()) {
            System.out.println("no course selected");
        } else {
            String path = parameterList.get(0);
            VirtualMachine vm = ((Student) UserOperation.getCurrentUser())
                    .getVM(UserOperation.getCurrentCourse().getId());
            try {
                FileHelper.serialize(vm, path);
                System.out.println("uploadVM success");
            } catch (Exception e) {
                System.out.println("serialize Exception");
            }
        }
    }
}
