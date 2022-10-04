package com.buaa.function;

import com.buaa.data.*;
import com.buaa.main.UserOperation;

import java.util.ArrayList;

public class AddAdmin extends Function {
    private static final AddAdmin addAdmin = new AddAdmin();

    private AddAdmin() {

    }

    public static AddAdmin getInstance() {
        return addAdmin;
    }

    @Override
    public void run(ArrayList<String> parameterList) {
        if (parameterList.size() == 0) {
            System.out.println("arguments illegal");
        } else {
            if (UserOperation.isNoUser()) {
                System.out.println("not logged in");
            } else if (!UserOperation.isProfessor()) {
                System.out.println("permission denied");
            } else if (UserOperation.isNoCourse()) {
                System.out.println("no course selected");
            } else {
                boolean legalFlag = true;
                for (String id : parameterList) {//遍历参数是否全部合法
                    if (!User.isIdLegal(id)) {
                        System.out.println("user id illegal");
                        legalFlag = false;
                        break;
                    } else if (!Data.isUserIdExist(id)) {
                        System.out.println("user id not exist");
                        legalFlag = false;
                        break;
                    }
                }
                if (legalFlag) {
                    Course currentCourse = UserOperation.getCurrentCourse();
                    for (String id : parameterList) {//遍历参数逐个添加
                        User user = Data.getUser(id);
                        currentCourse.addAdmin(id);//给当前选择的课程添加管理员
                        user.addManagerCourse(currentCourse);//给这个人添加管理的课程

                        if (user instanceof Student) {//如果这个人是学生，设置为助教
                            user.setAssistant(true);
                        }
                    }
                    System.out.println("add admin success");
                }
            }
        }
    }
}
