package com.buaa.data;

import java.util.TreeMap;

public class Course {
    private String id;
    private String name;
    private int teacherNum;
    private int AssistantNum;
    private int studentNum;

    private TreeMap<String, User> adminTreeMap = new TreeMap<>();

    public void addAdmin(String id) {
        adminTreeMap.put(id, Data.getUser(id));
    }

    public int getStudentNum() {
        return studentNum;
    }

    public int getAssistantNum() {
        return AssistantNum;
    }

    public int getTeacherNum() {
        return teacherNum;
    }

    public void teacherNumPlusOne() {
        teacherNum++;
    }

    public void teacherNumMinusOne() {
        teacherNum--;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Course(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Course() {

    }

    public static boolean isIdLegal(String id) {
        return id.matches("C(1[7-9]|2[0-2])([1-9]\\d|0[1-9])");
    }

    public static boolean isNameLegal(String name) {
        return name.matches("\\w{6,16}");
    }

    public static void listCourse(TreeMap<String, Course> courseTreeMap) {
        courseTreeMap.forEach((id, course) -> {
            System.out.println("[ID:" + id +
                    "] [Name:" + course.getName() +
                    "] [TeacherNum:" + course.getTeacherNum() +
                    "] [AssistantNum:" + course.getAssistantNum() +
                    "] [StudentNum:" + course.getStudentNum() +
                    "]");
        });
    }
}
