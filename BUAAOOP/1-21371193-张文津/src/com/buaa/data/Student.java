package com.buaa.data;

public class Student extends User {
    public Student(String id, String firstName, String lastName, String emailAddress, String password) {
        super(id, firstName, lastName, emailAddress, password);
        setType("Student");
    }

    public static boolean isStudent(String id) {
        return isUndergraduate(id) || isPostgraduate(id) || isPhD(id);
    }

    public static boolean isUndergraduate(String id) {//本科生
        return id.matches("\\b((1[7-9])|(2[0-2]))((0[1-9])|([1-3]\\d)|(4[0-3]))[1-6]\\d{3}\\b");
    }

    public static boolean isPostgraduate(String id) {//硕士研究生
        return id.matches("\\b[SZ]Y((19)|(2[0-2]))((0[1-9])|([1-3]\\d)|(4[0-3]))[1-6]\\d{2}\\b");
    }

    public static boolean isPhD(String id) {//博士研究生
        return id.matches("\\bBY\\b((1[7-9])|(2[0-2]))((0[1-9])|([1-3]\\d)|(4[0-3]))[1-6]\\d{2}");
    }
}
