package com.buaa.data;

import com.buaa.main.UserOperation;

import java.util.TreeMap;

public class User {
    private String id;
    private String firstName;//名
    private String lastName;//姓
    private String email;
    private String password;
    private String type;
    private final TreeMap<String, Course> managerCourseTreeMap = new TreeMap<>();
//    private boolean assistant = false;
//    private final TreeMap<String, Course> studentCourseTreeMap = new TreeMap<>();

//    public void removeStudentCourse(String id) {
//        studentCourseTreeMap.remove(id);
//    }
//
//    public void addStudentCourse(Course course) {
//        studentCourseTreeMap.put(course.getId(), course);
//    }

    public boolean isAssistant(){
        return type.equals("Assistant");
    }

    public String getEmail() {
        return email;
    }

    public void addManagerCourse(Course course) {
        managerCourseTreeMap.put(course.getId(), course);
    }

    public TreeMap<String, Course> getManagerCourseTreeMap() {
        return managerCourseTreeMap;
    }

    public boolean isManagerCourseIdExist(String id) {
        return managerCourseTreeMap.containsKey(id);
    }

    public void removeManagerCourse(String id) {
        managerCourseTreeMap.remove(id);
    }

    public Course getManagerCourse(String id) {
        return managerCourseTreeMap.get(id);
    }

    public boolean isManagerCourseTreeMapEmpty() {
        return managerCourseTreeMap.isEmpty();
    }

//    public void setAssistant(boolean flag) {
//        assistant = flag;
//        if (flag) {
//            type = "Assistant";
//        } else {
//            type = "Student";
//            //如果解除助教身份的时候，正好是该学生正在使用助教端，那么强制返回到学生端
//            if (this == UserOperation.getCurrentUser() && UserOperation.isAssistantRole()) {
//                UserOperation.changeRole();
//            }
//        }
//    }
//
//    public boolean isAssistant() {
//        return assistant;
//    }

    @Override
    public String toString() {
        return "Name: " + firstName + " " + lastName + "\n" +
                "ID: " + id + "\n" +
                "Type: " + type + "\n" +
                "Email: " + email;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public User(String id, String firstName, String lastName, String email, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public User() {
    }

    public static boolean isIdLegal(String id) {
        //19375030 19375168 BY2215201 SY2021118
        return Professor.isTeacher(id) || Student.isStudent(id);
    }

    public static boolean isNameLegal(String id) {
        return id.matches("\\b[A-Z][a-z]{0,19}\\b");
    }

    public static boolean isEmailAddressLegal(String id) {
        return id.matches("\\w+@\\w+(\\.\\w+)+");
    }

    public static boolean isPasswordLegal(String id) {
        return id.matches("([A-Z]|[a-z])\\w{7,15}");
    }
}
