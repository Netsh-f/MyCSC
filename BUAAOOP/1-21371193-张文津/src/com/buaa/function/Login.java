package com.buaa.function;

import com.buaa.data.Data;
import com.buaa.data.Student;
import com.buaa.data.User;
import com.buaa.main.UserOperation;

import java.util.ArrayList;

public class Login extends Function {
    public static Login login = new Login();

    @Override
    public void run(ArrayList<String> parameterList) {
        //login 学工号 密码
        if (UserOperation.getCurrentUser() == null) {
            String id = parameterList.get(0);
            String password = parameterList.get(1);
            if (User.isIdLegal(id) == false) {
                System.out.println("user id illegal");
            } else if (Data.isIdExit(id) == false) {
                System.out.println("user id not exist");
            } else {
                User userToBeLoggedIn = Data.getUser(id);
                if (userToBeLoggedIn.getPassword().equals(password)) {

                    UserOperation.setCurrentUser(userToBeLoggedIn);

                    if (userToBeLoggedIn instanceof Student) {
                        System.out.println("Hello " + userToBeLoggedIn.getFirstName() + "~");
                    } else {
                        System.out.println("Hello Professor " + userToBeLoggedIn.getLastName() + "~");
                    }
                } else {
                    System.out.println("wrong password");
                }
            }
        } else {
            System.out.println("already logged in");
        }
    }
}
