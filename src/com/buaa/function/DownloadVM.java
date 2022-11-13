package com.buaa.function;

import com.buaa.data.Course;
import com.buaa.data.Student;
import com.buaa.data.VM.VirtualMachine;
import com.buaa.main.UserOperation;
import com.buaa.utils.FileHelper;

import java.util.ArrayList;

public class DownloadVM extends Function {
    private static final DownloadVM downloadVM = new DownloadVM();

    private DownloadVM() {
    }

    public static final DownloadVM getInstance() {
        return downloadVM;
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
            Student currentUser = (Student) UserOperation.getCurrentUser();
            Course currentCourse = UserOperation.getCurrentCourse();
            try {
                VirtualMachine vm = (VirtualMachine) FileHelper.deserialize(path);
                currentCourse.addVM(vm);
                currentUser.setVM(currentCourse.getId(), vm);
                System.out.println("downloadVM success");
            } catch (Exception e) {
                System.out.println("deserialize Exception");
            }
        }
    }
}
