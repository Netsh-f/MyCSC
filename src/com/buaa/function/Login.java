package com.buaa.function;

import com.buaa.data.Data;
import com.buaa.data.Student;
import com.buaa.data.User;
import com.buaa.main.UserOperation;

import java.util.ArrayList;

public class Login extends Function {
    private static final Login login = new Login();

    private Login() {
    }

    public static Login getInstance(){
        return login;
    }

    @Override
    public void run(ArrayList<String> parameterList) {
        //login 学工号 密码
        if (parameterList.size() != 2) {
            System.out.println("arguments illegal");
        } else {
            if (UserOperation.isNoUser()) {
                System.out.println("already logged in");
            } else {
                String id = parameterList.get(0);
                String password = parameterList.get(1);
                if (!User.isIdLegal(id)) {
                    System.out.println("user id illegal");
                } else if (!Data.isUserIdExist(id)) {
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
            }
        }
    }
}
