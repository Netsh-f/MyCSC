package com.buaa.function;

import com.buaa.data.Data;
import com.buaa.data.Professor;
import com.buaa.data.User;
import com.buaa.data.Ware;
import com.buaa.main.UserOperation;

import java.util.ArrayList;

public class AddWare extends Function {
    private static final AddWare addWare = new AddWare();

    private AddWare() {
    }

    public static AddWare getInstance() {
        return addWare;
    }

    @Override
    public void run(ArrayList<String> parameterList) {
        if (parameterList.size() != 2) {
            System.out.println("arguments illegal");
        } else {
            User currentUser = UserOperation.getCurrentUser();
            String id = parameterList.get(0);
            String name = parameterList.get(1);
            if (UserOperation.isNoUser()) {
                System.out.println("not logged in");
            } else if (!(currentUser instanceof Professor)) {
                System.out.println("permission denied");
            } else if (UserOperation.isNoCourse()) {
                System.out.println("no course selected");
            } else if (!Ware.isIdLegal(id)) {
                System.out.println("ware id illegal");
            } else if (Data.isWareIdExist(id)) {
                System.out.println("ware id duplication");
            } else if (!Ware.isNameLegal(name)) {
                System.out.println("ware name illegal");
            } else {
                Ware ware = new Ware(id, name);
                Data.addWare(ware);
                Data.getCourse(id).addWare(ware);
                System.out.println("add ware success");
            }
        }
    }
}
