package com.buaa.function;

import com.buaa.data.Course;
import com.buaa.data.Student;
import com.buaa.data.User;
import com.buaa.data.VM.VirtualMachine;
import com.buaa.main.UserOperation;

import java.util.ArrayList;

public class LogVM extends Function {
    private static final LogVM logVM = new LogVM();

    private LogVM() {
    }

    public static final LogVM getInstance() {
        return logVM;
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
            Course currentCourse = UserOperation.getCurrentCourse();
            Student currentUser = (Student) UserOperation.getCurrentUser();
            if (!currentUser.isVMExist(currentCourse.getId())) {
                System.out.println("no log");
            } else {
                VirtualMachine vm = currentUser.getVM(currentCourse.getId());
                if (vm.isCmdListEmpty()) {
                    System.out.println("no log");
                } else {
                    vm.printCmdList();
                }
            }
        }
    }
}
