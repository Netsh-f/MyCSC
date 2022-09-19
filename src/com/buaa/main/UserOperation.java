package com.buaa.main;

import com.buaa.data.User;
import com.buaa.function.*;

import java.util.ArrayList;
import java.util.HashMap;

public class UserOperation {
    private static int userState;
    private static HashMap<String, Integer> userStateMap = new HashMap<String, Integer>();
    private static HashMap<String, Function> cmdMap = new HashMap<String, Function>();

    private static User noUser = new User();
    private static User currentUser = noUser;

    public static User getNoUser() {
        return noUser;
    }

    public static void setCurrentUser(User currentUser) {
        UserOperation.currentUser = currentUser;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void userInit() {
        userStateMapInit();
        cmdMapInit();
    }

    private static void userStateMapInit() {
        userStateMap.put("notLoggedIn", 101);
        userStateMap.put("student", 102);
        userStateMap.put("teacher", 103);
        userState = userStateMap.get("notLoggedIn");
    }

    private static void cmdMapInit() {
        cmdMap.put("QUIT", Quit.quit);
        cmdMap.put("register", Register.register);
        cmdMap.put("login", Login.login);
        cmdMap.put("printInfo", PrintInfo.printInfo);
        cmdMap.put("logout", Logout.logout);
    }

    public static void command(String cmd, ArrayList<String> parameter) {
        if (cmd.matches("\\s*")) {
            //do nothing
        } else if (cmdMap.containsKey(cmd)) {
            cmdMap.get(cmd).run(parameter);
        } else {
            System.out.println("command '" + cmd + "' not found");
        }
    }
}
