package com.buaa.function;

import com.buaa.data.Data;
import com.buaa.data.User;

import java.util.ArrayList;

public class Register extends Function {
    public static Register register = new Register();

    @Override
    public void run(ArrayList<String> parameterList) {
        //register 学工号 名 姓 邮箱 密码 确认密码
        //register 21371193 文津 张 JingQian123@buaa.edu.cn 8765 8765
        Data.addUser(new User(parameterList.get(0),
                parameterList.get(1),
                parameterList.get(2),
                parameterList.get(3),
                parameterList.get(4)));
    }
}
