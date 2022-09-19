package com.buaa.function;

import com.buaa.data.Data;
import com.buaa.data.Student;
import com.buaa.data.Teacher;
import com.buaa.data.User;
import com.buaa.main.UserOperation;

import java.util.ArrayList;

public class Register extends Function {
    public static Register register = new Register();

    @Override
    public void run(ArrayList<String> parameterList) {
        //register 学工号 名 姓 邮箱 密码 确认密码
        //register 21371193 Wenjin Zhang Jing123@buaa.edu.cn zwj123456 zwj123456
        if (UserOperation.getCurrentUser() == null) {
            String id = parameterList.get(0);
            String firstName = parameterList.get(1);
            String lastName = parameterList.get(2);
            String emailAddress = parameterList.get(3);
            String password = parameterList.get(4);
            String confirmPassword = parameterList.get(5);
            if (User.isIdLegal(id) == false) {
                System.out.println("user id illegal");
            } else if (Data.isIdExit(id) == true) {
                System.out.println("user id duplication");
            } else if (User.isNameLegal(firstName) == false || User.isNameLegal(lastName) == false) {
                System.out.println("user name illegal");
            } else if (User.isEmailAddressLegal(emailAddress) == false) {
                System.out.println("email address illegal");
            } else if (User.isPasswordLegal(password) == false) {
                System.out.println("password illegal");
            } else if (password.equals(confirmPassword) == false) {
                System.out.println("passwords inconsistent");
            } else {
                System.out.println("register succes");
                if(Teacher.isTeacher(id)){
                    Data.addUser(new Teacher(id, firstName, lastName, emailAddress, password));
                }else{
                    Data.addUser(new Student(id, firstName, lastName, emailAddress, password));
                }
            }
        } else {
            System.out.println("already logged in");
        }
    }
}
