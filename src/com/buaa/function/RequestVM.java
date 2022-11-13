package com.buaa.function;

import com.buaa.data.Course;
import com.buaa.data.Student;
import com.buaa.data.VM.VirtualMachine;
import com.buaa.data.VM.VMFactory;
import com.buaa.main.UserOperation;

import java.util.ArrayList;

public class RequestVM extends Function {
    private static final RequestVM requestVM = new RequestVM();

    private RequestVM() {

    }

    public static final RequestVM getInstance() {
        return requestVM;
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
            String type = parameterList.get(0);
            if(!VMFactory.isTypeLegal(type)){
                System.out.println("type illegal");//原题无此要求，原题保证了数据合法
            }else{
                Student currentUser = (Student) UserOperation.getCurrentUser();
                Course currentCourse = UserOperation.getCurrentCourse();

                VirtualMachine newVM = VMFactory.createVM(type);
                currentCourse.addVM(newVM);
                currentUser.setVM(currentCourse.getId(), newVM);
                System.out.println("requestVM success");
            }
        }
    }
}
