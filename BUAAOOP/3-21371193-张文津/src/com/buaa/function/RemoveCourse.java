package com.buaa.function;

import com.buaa.data.Course;
import com.buaa.data.Data;
import com.buaa.data.User;
import com.buaa.main.UserOperation;

import java.util.ArrayList;

public class RemoveCourse extends Function {
    private static final RemoveCourse removeCourse = new RemoveCourse();

    private RemoveCourse() {
    }

    public static RemoveCourse getInstance() {
        return removeCourse;
    }

    @Override
    public void run(ArrayList<String> parameterList) {
        if (parameterList.size() != 1) {
            System.out.println("arguments illegal");
        } else {
            User currentUser = UserOperation.getCurrentUser();
            String id = parameterList.get(0);
            if (UserOperation.isNoUser()) {
                System.out.println("not logged in");
            } else if (!UserOperation.isProfessor()) {
                System.out.println("permission denied");
            } else if (!Course.isIdLegal(id)) {
                System.out.println("course id illegal");
            } else if (!currentUser.isManagerCourseIdExist(id)) {
                System.out.println("course id not exist");
            } else {
                Course course = Data.getCourse(id);
                currentUser.removeManagerCourse(id);//从当前用户名下移除课程
                course.removeAdmin(currentUser.getId());//从课程的管理员列表中移除该用户
                if(course.getAdminTreeMap().size()==0){//如果没有管理员了，彻底删除课程
                    Data.removeCourse(id);
                }
                if(id.equals(UserOperation.getCurrentCourse().getId())){//如果删除的是当前选择的课程，当前选择课程清空
                    UserOperation.setCurrentCourse(UserOperation.getNoCourse());
                }
                System.out.println("remove course success");
            }
        }
    }
}
