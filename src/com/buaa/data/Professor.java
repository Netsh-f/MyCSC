package com.buaa.data;

public class Professor extends User {

    public Professor(String id, String firstName, String lastName, String emailAddress, String password) {
        super(id, firstName, lastName, emailAddress, password);
        setType("Professor");
    }

    public static boolean isTeacher(String id) {
        return id.matches("\\b([1-9]\\d\\d\\d\\d|0[1-9]\\d\\d\\d|00[1-9]\\d\\d|000[1-9]\\d|0000[1-9])\\b");
    }

}
