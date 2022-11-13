package com.buaa.function;

import com.buaa.data.Course;
import com.buaa.data.Student;
import com.buaa.data.VM.VirtualMachine;
import com.buaa.main.MyScs;
import com.buaa.main.UserOperation;

import java.util.ArrayList;
import java.util.Scanner;

public class StartVM extends Function {
    private static final StartVM startVM = new StartVM();

    private StartVM() {
    }

    public static final StartVM getInstance() {
        return startVM;
    }

    @Override
    public void run(ArrayList<String> parameterList) {
        if (parameterList.size() != 0) {
            System.out.println("arguments illegal");
        } else if (UserOperation.isNoUser()) {
            System.out.println("not logged in");
        } else if (UserOperation.isManager()) {
            System.out.println("permission denied");//原题此要求优先级位置未说明
        } else if (UserOperation.isNoCourse()) {
            System.out.println("no course selected");
        } else {
            Student currentUser = (Student) UserOperation.getCurrentUser();
            Course currentCourse = UserOperation.getCurrentCourse();
            if (!currentUser.isVMExist(currentCourse.getId())) {
                System.out.println("no VM");
            } else {
                VirtualMachine currentVM = currentUser.getVM(currentCourse.getId());
                Scanner scanner = MyScs.getScanner();

                System.out.println("welcome to " + currentVM.getType());
                String cmd = scanner.nextLine();
                while (!cmd.equals("EOF")) {
                    currentVM.addCmd(cmd);
                }
                System.out.println("quit " + currentVM.getType());
            }
        }
    }
}
