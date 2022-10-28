package com.buaa.function;

import com.buaa.data.Course;
import com.buaa.data.Professor;
import com.buaa.data.User;
import com.buaa.data.Ware;
import com.buaa.main.UserOperation;

import java.util.ArrayList;

public class ListWare extends Function {
    private static final ListWare listWare = new ListWare();

    private ListWare() {
    }

    public static ListWare getInstance() {
        return listWare;
    }

    @Override
    public void run(ArrayList<String> parameterList) {
        if (parameterList.size() != 0) {
            System.out.println("arguments illegal");
        } else {
            User currentUser = UserOperation.getCurrentUser();
            Course currentCourse = UserOperation.getCurrentCourse();
            if (UserOperation.isNoUser()) {
                System.out.println("not logged in");
            } else if (UserOperation.isNoCourse()) {
                System.out.println("no course selected");
            } else {
                Ware.listWare(currentCourse.getWareTreeMap());
            }
        }
    }
}
