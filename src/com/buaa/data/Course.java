package com.buaa.data;

import java.util.*;

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

    /***
     * 在输出前调用此方法，可以更新成绩为None且助教已经提交答案的作业的成绩
     * @param taskTreeMap 查找的表
     * @param taskId 同下
     * @param studentId 同下
     * @return 查找到的目标个数
     */
    public static int queryScoreNum(TreeMap<String, Task> taskTreeMap, String taskId, String studentId) {
        int cnt = 0;
        for (Map.Entry<String, Task> taskEntry : taskTreeMap.entrySet()) {
            if (taskId == null || taskEntry.getKey().equals(taskId)) {
                Task task = taskEntry.getValue();
                for (Map.Entry<String, Work> workEntry : taskEntry.getValue().getWorkTreeMap().entrySet()) {
                    if (studentId == null || workEntry.getKey().equals(studentId)) {
                        cnt++;
                        if (workEntry.getValue().isHighestScoreNone() && !taskEntry.getValue().isAnswerNull()) {
                            Work work = workEntry.getValue();
                            double score = task.evaluateWork(work);
                            work.setHighestScore(score);
                        }
                    }
                }
            }
        }
        return cnt;
    }

    /***
     * 打印查询成绩的结果
     * @param taskTreeMap 查询的表
     * @param taskId null空即为无限制
     * @param studentId null空即为无限制
     */
    public static void queryScore(TreeMap<String, Task> taskTreeMap, String taskId, String studentId) {
        int cnt = 0;
        for (Map.Entry<String, Task> taskEntry : taskTreeMap.entrySet()) {
            if (taskId == null || taskEntry.getKey().equals(taskId)) {
                List<Map.Entry<String, Work>> entryList = new ArrayList<>(taskEntry.getValue().getWorkTreeMap().entrySet());
                Collections.sort(entryList, new Comparator<Map.Entry<String, Work>>() {
                    @Override
                    public int compare(Map.Entry<String, Work> o1, Map.Entry<String, Work> o2) {
                        Work work1 = o1.getValue();
                        Work work2 = o2.getValue();
                        if (work1.getHighestScore() == work2.getHighestScore()) {
                            return work1.getStudentId().compareTo(work2.getStudentId());
                        } else {
                            return work1.getHighestScore() - work2.getHighestScore() > 0 ? 1 : -1;
                        }
                    }
                });

                for (Map.Entry<String, Work> workEntry : taskEntry.getValue().getWorkTreeMap().entrySet()) {
                    if (studentId == null || workEntry.getKey().equals(studentId)) {
                        cnt++;
                        Work work = workEntry.getValue();
                        System.out.println("[" + cnt +
                                "] [ID:" + work.getStudentId() +
                                "] [Name:" + work.getLastName() +
                                " " + work.getFirstName() +
                                "] [Task_ID:" + taskEntry.getValue().getId() +
                                "] [Score:" + work.getHighestScore() +
                                "]");
                    }
                }
            }
        }
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
