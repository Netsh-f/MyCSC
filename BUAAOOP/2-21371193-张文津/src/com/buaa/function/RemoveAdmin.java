package com.buaa.function;

import com.buaa.data.Course;
import com.buaa.data.Data;
import com.buaa.data.Student;
import com.buaa.data.User;
import com.buaa.main.UserOperation;

import java.util.ArrayList;

public class RemoveAdmin extends Function {
    private static final RemoveAdmin removeAdmin = new RemoveAdmin();

    private RemoveAdmin() {
    }

    public static RemoveAdmin getInstance() {
        return removeAdmin;
    }

    @Override
    public void run(ArrayList<String> parameterList) {
        if (parameterList.size() != 1) {
            System.out.println("arguments illegal");
        } else {
            User currentUser = UserOperation.getCurrentUser();
            Course currentCourse = UserOperation.getCurrentCourse();
            String id = parameterList.get(0);
            if (UserOperation.isNoUser()) {
                System.out.println("not logged in");
            } else if (!UserOperation.isProfessor()) {
                System.out.println("permission denied");
            } else if (UserOperation.isNoCourse()) {
                System.out.println("no course selected");
            } else if (!User.isIdLegal(id)) {
                System.out.println("user id illegal");
            } else if (!currentCourse.isAdminIdExist(id)) {
                System.out.println("user id not exist");
            } else {
                User user = Data.getUser(id);
                currentCourse.removeAdmin(id);//从当前课程中删除管理员
                user.removeManagerCourse(currentCourse.getId());//从这个人的管理课表中删除课程
                //如果这个人是学生（助教）且没有别的管理的课程，解除助教身份
                if (user.getManagerCourseTreeMap().size() == 0 && user instanceof Student) {
                    user.setAssistant(false);
                }
                System.out.println("remove admin success");
            }
        }
    }
}
