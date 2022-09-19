package com.buaa.main;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {
    public static void main(String[] args) {
        UserOperation.userInit();
        Scanner scanner = new Scanner(System.in);
        String command;
        String commandPattern = "[\\S]+";
        Pattern pattern = Pattern.compile(commandPattern);
        Matcher matcher;
        String cmd = "";
        ArrayList<String> parameterList = new ArrayList<>();
        while (true) {
            command = scanner.nextLine();//读取新的命令
            parameterList.removeAll(parameterList);//清空参数
            matcher = pattern.matcher(command);//匹配
            int i = 0;
            while (matcher.find()) {
                if (i == 0) {
                    cmd = matcher.group();//提取cmd
                } else {
                    parameterList.add(matcher.group());//提取参数
                }
                i++;
            }
            UserOperation.command(cmd, parameterList);//执行
        }
    }
}
