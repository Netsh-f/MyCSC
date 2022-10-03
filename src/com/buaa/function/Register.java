package com.buaa.function;

import com.buaa.data.Data;
import com.buaa.data.Student;
import com.buaa.data.Professor;
import com.buaa.data.User;
import com.buaa.main.UserOperation;

import java.util.ArrayList;

public class Register extends Function {
    private static final Register register = new Register();

    private Register() {
    }

    public static Register getInstance() {
        return register;
    }

    @Override
    public void run(ArrayList<String> parameterList) {
        //register 学工号 名 姓 邮箱 密码 确认密码
        //register 21371193 Wenjin Zhang Jing123@buaa.edu.cn zwj123456 zwj123456
        if (parameterList.size() != 6) {
            System.out.println("arguments illegal");
        } else {
            if (UserOperation.isNoUser()) {
                String id = parameterList.get(0);
                String firstName = parameterList.get(1);
                String lastName = parameterList.get(2);
                String emailAddress = parameterList.get(3);
                String password = parameterList.get(4);
                String confirmPassword = parameterList.get(5);
                if (!User.isIdLegal(id)) {
                    System.out.println("user id illegal");
                } else if (Data.isIdExist(id)) {
                    System.out.println("user id duplication");
                } else if (!User.isNameLegal(firstName) || !User.isNameLegal(lastName)) {
                    System.out.println("user name illegal");
                } else if (!User.isEmailAddressLegal(emailAddress)) {
                    System.out.println("email address illegal");
                } else if (!User.isPasswordLegal(password)) {
                    System.out.println("password illegal");
                } else if (!password.equals(confirmPassword)) {
                    System.out.println("passwords inconsistent");
                } else {
                    System.out.println("register success");
                    if (Professor.isTeacher(id)) {
                        Data.addUser(new Professor(id, firstName, lastName, emailAddress, password));
                    } else {
                        Data.addUser(new Student(id, firstName, lastName, emailAddress, password));
                    }
                }
            } else {
                System.out.println("already logged in");
            }
        }
    }
}
