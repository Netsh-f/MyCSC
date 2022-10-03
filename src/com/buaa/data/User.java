package com.buaa.data;

import java.util.TreeMap;

public class User {
    private String id;
    private String firstName;//名
    private String lastName;//姓
    private String emailAddress;
    private String password;
    private String type;

    private boolean assistant = false;

    private final TreeMap<String, Course> courseTreeMap = new TreeMap<>();

    public void addCourse(Course course) {
        courseTreeMap.put(course.getId(), course);
    }

    public TreeMap<String, Course> getCourseTreeMap() {
        return courseTreeMap;
    }

    public boolean isCourseIdExist(String id) {
        return courseTreeMap.containsKey(id);
    }

    public void removeCourse(String id) {
        courseTreeMap.remove(id);
    }

    public Course getCourse(String id) {
        return courseTreeMap.get(id);
    }

    public boolean isCourseTreeMapEmpty() {
        return courseTreeMap.isEmpty();
    }

    public void setAssistant() {
        assistant = true;
    }

    public boolean isAssistant() {
        return assistant;
    }

    @Override
    public String toString() {
        return "Name: " + firstName + " " + lastName + "\n" +
                "ID: " + id + "\n" +
                "Type: " + type + "\n" +
                "Email: " + emailAddress;
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

    public User(String id, String firstName, String lastName, String emailAddress, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
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
