package com.buaa.function;

import com.buaa.data.Course;
import com.buaa.main.UserOperation;

import java.util.ArrayList;

public class ClearVM extends Function {
    private static final ClearVM clearVM = new ClearVM();

    private ClearVM() {
    }

    public static final ClearVM getInstance() {
        return clearVM;
    }

    @Override
    public void run(ArrayList<String> parameterList) {
        if (parameterList.size() != 1) {
            System.out.println("arguments illegal");
        } else if (UserOperation.isNoUser()) {
            System.out.println("not logged in");
        } else if (!UserOperation.isProfessor()) {//是老师端不是助教端（？）
            System.out.println("permission denied");//原题此要求优先级位置未说明
        } else if (UserOperation.isNoCourse()) {
            System.out.println("no course selected");
        } else {
            String para = parameterList.get(0);
            if (!para.matches("\\d+")) {
                System.out.println("num illegal");//原题无此要求，原题保证参数合法
            } else {
                int num = Integer.parseInt(para);
                Course currentCourse = UserOperation.getCurrentCourse();
                if (!currentCourse.isVMNumLegal(num)) {
                    System.out.println("num out of size");//原题无此要求，原题保证参数合法
                } else {
                    currentCourse.clearVM(num);
                    System.out.println("clear " + currentCourse.getVM(num).getType() + " success");
                }
            }
        }
    }
}
