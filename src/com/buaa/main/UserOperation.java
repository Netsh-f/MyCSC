package com.buaa.main;

import com.buaa.function.Function;
import com.buaa.function.Quit;
import com.buaa.function.Register;

import java.util.ArrayList;
import java.util.HashMap;

public class UserOperation {
    private static int userState;
    private static HashMap<String, Integer> userStateMap = new HashMap<String, Integer>();
    private static HashMap<String, Function> cmdMap = new HashMap<String, Function>();

    public static void userInit(){
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
//        cmdMap.put("login", 202);
//        cmdMap.put("printinfo", 203);
//        cmdMap.put("logout", 204);
    }

    public static void command(String cmd, ArrayList<String> parameter) {
        cmdMap.get(cmd).run(parameter);
    }
}
