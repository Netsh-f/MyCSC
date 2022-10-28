package com.buaa.data;

import java.util.TreeMap;

public class Course {
    private String id;
    private String name;
    private TreeMap<String, Ware> wareTreeMap = new TreeMap<>();
    private TreeMap<String, User> adminTreeMap = new TreeMap<>();
    private TreeMap<String, User> teacherTreeMap = new TreeMap<>();
    private TreeMap<String, User> assistantTreeMap = new TreeMap<>();
    private TreeMap<String, Task> taskTreeMap = new TreeMap<>();
    private TreeMap<String, User> studentTreeMap = new TreeMap<>();

    public TreeMap<String, User> getStudentTreeMap() {
        return studentTreeMap;
    }

    public Ware getWare(String id) {
        return wareTreeMap.get(id);
    }

    public Task getTask(String id) {
        return taskTreeMap.get(id);
    }

    public void removeStudent(String id) {
        studentTreeMap.remove(id);
    }

    public boolean isStudentIdExist(String id) {
        return studentTreeMap.containsKey(id);
    }

    public void addStudent(User student) {
        studentTreeMap.put(student.getId(), student);
    }

    public TreeMap<String, Task> getTaskTreeMap() {
        return taskTreeMap;
    }

    public void removeTask(String id) {
        taskTreeMap.remove(id);
    }

    public boolean isTaskIdExist(String id) {
        return taskTreeMap.containsKey(id);
    }

    public void addTask(Task task) {
        taskTreeMap.put(task.getId(), task);
    }

    public TreeMap<String, Ware> getWareTreeMap() {
        return wareTreeMap;
    }

    public void removeWare(String id) {
        wareTreeMap.remove(id);
    }

    public boolean isWareIdExist(String id) {
        return wareTreeMap.containsKey(id);
    }

    public void addWare(Ware ware) {
        wareTreeMap.put(ware.getId(), ware);
    }

    public TreeMap<String, User> getAdminTreeMap() {
        return adminTreeMap;
    }

    public void addAdmin(String id) {
        User user = Data.getUser(id);
        adminTreeMap.put(id, user);
        if (user instanceof Professor) {
            teacherTreeMap.put(id, user);
        } else {
            assistantTreeMap.put(id, user);
        }
    }

    public boolean isAdminIdExist(String id) {
        return adminTreeMap.containsKey(id);
    }

    public void removeAdmin(String id) {
        adminTreeMap.remove(id);
        if (Professor.isTeacher(id)) {
            teacherTreeMap.remove(id);
        } else {
            assistantTreeMap.remove(id);
        }
    }

    public int getStudentNum() {
        return studentTreeMap.size();
    }

    public int getAssistantNum() {
        return assistantTreeMap.size();
    }

    public int getTeacherNum() {
        return teacherTreeMap.size();
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

    public static void studentListAdmin(TreeMap<String, User> adminTreeMap) {
        adminTreeMap.forEach((id, user) -> {
            System.out.println("[Name:" + user.getLastName() +
                    " " + user.getFirstName() +
                    "] [Type:" + user.getType() +
                    "] [Email:" + user.getEmail() +
                    "]");
        });
    }

    public static void managerListAdmin(TreeMap<String, User> adminTreeMap) {
        adminTreeMap.forEach((id, user) -> {
            System.out.println("[ID:" + user.getId() +
                    "] [Name:" + user.getLastName() +
                    " " + user.getFirstName() +
                    "] [Type:" + user.getType() +
                    "] [Email:" + user.getEmail() +
                    "]");
        });
    }

    public static void listStudent(TreeMap<String, User> studentTreeMap) {
        studentTreeMap.forEach((id, student) -> {
            System.out.println("[ID:" + student.getId() +
                    "] [Name:" + student.getLastName() +
                    " " + student.getFirstName() +
                    "] [Email:" + student.getEmail() +
                    "]");
        });
    }
}
