package com.buaa.main;

import com.buaa.data.User;
import com.buaa.function.*;

import java.util.ArrayList;
import java.util.HashMap;

public class UserOperation {
    private static final HashMap<String, Function> cmdMap = new HashMap<>();

    private static final User noUser = new User();
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

    static {
        cmdMapInit();
    }

    private static void cmdMapInit() {
        cmdMap.put("QUIT", Quit.getInstance());
        cmdMap.put("register", Register.getInstance());
        cmdMap.put("login", Login.getInstance());
        cmdMap.put("printInfo", PrintInfo.getInstance());
        cmdMap.put("logout", Logout.getInstance());
        cmdMap.put("addCourse", AddCourse.getInstance());
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
