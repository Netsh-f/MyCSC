package com.buaa.function;

import com.buaa.data.Course;
import com.buaa.data.Professor;
import com.buaa.data.User;
import com.buaa.main.UserOperation;

import java.util.ArrayList;

public class ListAdmin extends Function {
    private static final ListAdmin listAdmin = new ListAdmin();

    private ListAdmin() {
    }

    public static ListAdmin getInstance() {
        return listAdmin;
    }

    @Override
    public void run(ArrayList<String> parameterList) {
        if (parameterList.size() != 0) {
            System.out.println("arguments illegal");
        } else {
            User currentUser = UserOperation.getCurrentUser();
            if (UserOperation.isNoUser()) {
                System.out.println("not logged in");
            } else if (!(currentUser instanceof Professor || UserOperation.isAssistant())) {
                System.out.println("permission denied");
            } else if (UserOperation.isNoCourse()) {
                System.out.println("no course selected");
            } else {
                Course.listAdmin(UserOperation.getCurrentCourse().getAdminTreeMap());
            }
        }
    }
}
