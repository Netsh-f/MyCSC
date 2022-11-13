package com.buaa.data;

import com.buaa.data.VM.VirtualMachine;
import com.buaa.main.UserOperation;

import java.util.TreeMap;

public class Student extends User {
    private final TreeMap<String, Course> studentCourseTreeMap = new TreeMap<>();
    private final TreeMap<String, VirtualMachine> VMTreeMap = new TreeMap<>();

    public VirtualMachine getVM(String courseId) {
        return VMTreeMap.get(courseId);
    }

    public boolean isVMExist(String courseId) {
        return VMTreeMap.containsKey(courseId);
    }

    public void setVM(String courseId, VirtualMachine vm) {
        VMTreeMap.put(courseId, vm);
    }

    public void setAssistant(boolean flag) {
        if (flag) {
            super.setType("Assistant");
        } else {
            super.setType("Student");
            //如果解除助教身份的时候，正好是该学生正在使用助教端，那么强制返回到学生端
            if (this == UserOperation.getCurrentUser() && UserOperation.isAssistantRole()) {
                UserOperation.changeRole();
            }
        }
    }

    public TreeMap<String, Course> getStudentCourseTreeMap() {
        return studentCourseTreeMap;
    }

    public void removeStudentCourse(String id) {
        studentCourseTreeMap.remove(id);
    }

    public void addStudentCourse(Course course) {
        studentCourseTreeMap.put(course.getId(), course);
    }

    public Student(String id, String firstName, String lastName, String emailAddress, String password) {
        super(id, firstName, lastName, emailAddress, password);
        setType("Student");
    }

    public static boolean isStudent(String id) {
        return isUndergraduate(id) || isPostgraduate(id) || isPhD(id);
    }

    public static boolean isUndergraduate(String id) {//本科生
        return id.matches("\\b(1[7-9]|2[0-2])(0[1-9]|[1-3]\\d|4[0-3])[1-6]([1-9]\\d\\d|0[1-9]\\d|00[1-9])\\b");
    }

    public static boolean isPostgraduate(String id) {//硕士研究生
        return id.matches("\\b[SZ]Y(19|2[0-2])(0[1-9]|[1-3]\\d|4[0-3])[1-6]([1-9]\\d|0[1-9])\\b");
    }

    public static boolean isPhD(String id) {//博士研究生
        return id.matches("\\bBY(1[7-9]|2[0-2])(0[1-9]|[1-3]\\d|4[0-3])[1-6]([1-9]\\d|0[1-9])\\b");
    }
}
