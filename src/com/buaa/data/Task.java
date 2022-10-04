package com.buaa.data;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task {
    private String id;
    private String name;
    private String startTime;
    private String endTime;
    private int receiveNum;

    public static void listTask(TreeMap<String, Task> taskTreeMap) {
        taskTreeMap.forEach((id, task) -> {
            System.out.println("[ID:" + id +
                    "] [Name:" + task.getName() +
                    "] [ReceiveNum:" + task.getReceiveNum() +
                    "] [StartTime:" + task.getStartTime() +
                    "] [EndTime:" + task.getEndTime() +
                    "]");
        });
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
        if (startTime.matches("^\\d{4}-\\d{2}-\\d{2}-\\d{2}:\\d{2}:\\d{2}$") &&
                endTime.matches("^\\d{4}-\\d{2}-\\d{2}-\\d{2}:\\d{2}:\\d{2}$")) {
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
            System.out.println("m1group(1):"+matcher1.group(1)+" m2group(1):"+matcher2.group(1));
            return id.matches("^T(1[7-9]|2[0-2])([1-9]\\d|0[1-9])([1-9]\\d|0[1-9])$")
                    && matcher2.group(1).equals(matcher1.group(1));
        }
        return false;
    }

    public Task(String id, String name, String startTime, String endTime) {
        this.id = id;
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
