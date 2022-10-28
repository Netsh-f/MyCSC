package com.buaa.data;

import com.buaa.utils.FileHelper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task {
    private String id;
    private String name;
    private String startTime;
    private String endTime;
    private int receiveNum;
    private String filePath;
    private TreeMap<String, Work> workTreeMap = new TreeMap<>();
    private Work answer;

    public boolean isAnswerNull() {
        return answer == null;
    }

    public TreeMap<String, Work> getWorkTreeMap() {
        return workTreeMap;
    }

    public void setAnswer(Work answer) {
        this.answer = answer;
    }

    public double evaluateWork(Work work) {
        if (answer == null) {
            return -1;//-1即为None
        } else {
            return 100 * FileHelper.compareFiles(answer.getFilePath(), work.getFilePath());
        }
    }

    public void addWrok(Work work) {
        workTreeMap.put(work.getStudentId(), work);
    }

    public boolean isStudentWorkReceived(String id) {
        return workTreeMap.containsKey(id);
    }

    public static void managerListTask(TreeMap<String, Task> taskTreeMap, Course course) {
        if (taskTreeMap.isEmpty()) {
            System.out.println("total 0 task");
        } else {
            int studentNum = course.getStudentNum();
            for (Map.Entry<String, Task> entry : taskTreeMap.entrySet()) {
                String key = entry.getKey();
                Task task = entry.getValue();
                System.out.println("[ID:" + key +
                        "] [Name:" + task.getName() +
                        "] [SubmissionStatus:" + task.getReceiveNum() + "/" + studentNum +
                        "] [StartTime:" + task.getStartTime() +
                        "] [EndTime:" + task.getEndTime() +
                        "]");
            }
        }
    }

    public static void studentListTask(TreeMap<String, Task> taskTreeMap, Student student) {
        if (taskTreeMap.isEmpty()) {
            System.out.println("total 0 task");
        } else {
            for (Map.Entry<String, Task> entry : taskTreeMap.entrySet()) {
                String key = entry.getKey();
                Task task = entry.getValue();
                String status;
                if (task.isStudentWorkReceived(student.getId())) {
                    status = "done";
                } else {
                    status = "undone";
                }
                System.out.println("[ID:" + key +
                        "] [Name:" + task.getName() +
                        "] [SubmissionStatus:" + status +
                        "] [StartTime:" + task.getStartTime() +
                        "] [EndTime:" + task.getEndTime() +
                        "]");
            }
        }
    }

    public String getFilePath() {
        return filePath;
    }

    public String getName() {
        return name;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public int getReceiveNum() {
        return receiveNum;
    }

    public String getId() {
        return id;
    }

    public static boolean isTimeLegal(String startTime, String endTime) {
        //"^(19\d\d|[2-9]\d\d\d)"
        if (startTime.matches("^(19\\d\\d|[2-9]\\d\\d\\d)-\\d{2}-\\d{2}-\\d{2}:\\d{2}:\\d{2}$") &&
                endTime.matches("^(19\\d\\d|[2-9]\\d\\d\\d)-\\d{2}-\\d{2}-\\d{2}:\\d{2}:\\d{2}$")) {
            try {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
                format.setLenient(false);
                Date startDate = format.parse(startTime);
                Date endDate = format.parse(endTime);
                return startDate.before(endDate);
            } catch (Exception e) {
                return false;
            }
        } else {
            return false;
        }
    }

    public static boolean isNameLegal(String name) {
        return name.matches("^\\w+\\.[A-Za-z0-9]+$") && name.matches("^[\\w\\.]{6,16}$");
    }

    public static boolean isIdLegal(String id, String courseId) {
        Matcher matcher1 = Pattern.compile("^T(\\d{4})\\d{2}$").matcher(id);
        Matcher matcher2 = Pattern.compile("^C(\\d{4})$").matcher(courseId);
        if (matcher1.find() && matcher2.find()) {
            return id.matches("^T(1[7-9]|2[0-2])([1-9]\\d|0[1-9])([1-9]\\d|0[1-9])$")
                    && matcher2.group(1).equals(matcher1.group(1));
        }
        return false;
    }

    public Task(String id, String name, String startTime, String endTime, String location) {
        this.id = id;
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.filePath = location;
    }

//    public Task(String id, String name, String startTime, String endTime) {
//        this.id = id;
//        this.name = name;
//        this.startTime = startTime;
//        this.endTime = endTime;
//    }
}
