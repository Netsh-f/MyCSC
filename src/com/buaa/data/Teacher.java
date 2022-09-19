package com.buaa.data;

public class Teacher extends User{
    public Teacher(String id, String firstName, String lastName, String emailAddress, String password) {
        super(id, firstName, lastName, emailAddress, password);
    }

    public static boolean isTeacher(String id){
        return id.matches("\\b\\d{5}\\b");
    }
}
