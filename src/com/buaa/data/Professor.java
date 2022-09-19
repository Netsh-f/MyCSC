package com.buaa.data;

public class Professor extends User{
    public Professor(String id, String firstName, String lastName, String emailAddress, String password) {
        super(id, firstName, lastName, emailAddress, password);
        setType("Professor");
    }

    public static boolean isTeacher(String id){
        return id.matches("\\b\\d{5}\\b");
    }
}
