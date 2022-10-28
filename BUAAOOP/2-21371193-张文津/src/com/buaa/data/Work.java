package com.buaa.data;

public class Work {
    private String studentId;
    private String filePath;
    private String firstName;
    private String lastName;
    private double highestScore;

    public boolean isHighestScoreNone() {
        return highestScore == -1;
    }

    public Work(String studentId, String filePath) {
        this.studentId = studentId;
        this.filePath = filePath;
        User user = Data.getUser(studentId);
        firstName = user.getFirstName();
        lastName = user.getLastName();
        highestScore = -1;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setHighestScore(double highestScore) {
        this.highestScore = highestScore;
    }

    public double getHighestScore() {
        return highestScore;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getFilePath() {
        return filePath;
    }
}
