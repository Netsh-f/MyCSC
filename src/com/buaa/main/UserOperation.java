package com.buaa.main;

import com.buaa.data.Course;
import com.buaa.data.Professor;
import com.buaa.data.User;
import com.buaa.function.*;

import java.util.ArrayList;
import java.util.HashMap;

public class UserOperation {
    private static final HashMap<String, Function> cmdMap = new HashMap<>();

    private static final User noUser = new User();
    private static User currentUser = noUser;
    private static final Course noCourse = new Course();
    private static Course currentCourse = noCourse;
    private static boolean currentRole = false;//false为学生 true为助教

    public static boolean isManager() {
        return currentUser instanceof Professor || isAssistantRole();
    }

    public static boolean isProfessor() {
        return currentUser instanceof Professor;
    }

    public static void initRole() {
        currentRole = false;
    }

    public static void changeRole() {
        currentRole = !currentRole;
    }

    public static boolean isAssistantRole() {
        return currentRole;
    }

    public static Course getCurrentCourse() {
        return currentCourse;
    }

    public static User getNoUser() {
        return noUser;
    }

    public static Course getNoCourse() {
        return noCourse;
    }

    public static boolean isNoUser() {
        return currentUser == noUser;
    }

    public static boolean isNoCourse() {
        return currentCourse == noCourse;
    }

    public static void setCurrentCourse(Course course) {
        UserOperation.currentCourse = course;
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
        cmdMap.put("removeCourse", RemoveCourse.getInstance());
        cmdMap.put("listCourse", ListCourse.getInstance());
        cmdMap.put("selectCourse", SelectCourse.getInstance());
        cmdMap.put("addAdmin", AddAdmin.getInstance());
        cmdMap.put("removeAdmin", RemoveAdmin.getInstance());
        cmdMap.put("listAdmin", ListAdmin.getInstance());
        cmdMap.put("changeRole", ChangeRole.getInstance());
        cmdMap.put("addWare", AddWare.getInstance());
        cmdMap.put("removeWare", RemoveWare.getInstance());
        cmdMap.put("listWare", ListWare.getInstance());
        cmdMap.put("addTask", AddTask.getInstance());
        cmdMap.put("removeTask", RemoveTask.getInstance());
        cmdMap.put("listTask", ListTask.getInstance());
        cmdMap.put("addStudent", AddStudent.getInstance());
        cmdMap.put("removeStudent", RemoveStudent.getInstance());
        cmdMap.put("listStudent", ListStudent.getInstance());
        cmdMap.put("downloadFile", DownloadFile.getInstance());
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
