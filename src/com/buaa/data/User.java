package com.buaa.data;

public class User {
    private String id;
    private String firstName;//名
    private String lastName;//姓
    private String name;//姓名
    private String emailAddress;
    private String password;

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

        this.name = this.lastName + this.firstName;
    }

    public static boolean isIdLegal(String id) {
        //19375030 19375168 BY2215201 SY2021118
        return Teacher.isTeacher(id) || Student.isStudent(id);
    }

    public static boolean isNameLegal(String id) {
        return id.matches("\\b[A-Z][a-z]{0,19}\\b");
    }

    public static boolean isEmailAddressLegal(String id){
        return id.matches("\\w+@\\w+(\\.\\w+)+");
    }

    public static boolean isPasswordLegal(String id){
        return id.matches("([A-Z]|[a-z])\\w{7,15}");
    }
}
