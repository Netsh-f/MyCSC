package com.buaa.data;

public class Work {
    private String studentId;
    private String filePath;
    private double score;

    public Work(String studentId, String filePath) {
        this.studentId = studentId;
        this.filePath = filePath;
    }

    public Work(String studentId, String filePath, double score) {
        this.studentId = studentId;
        this.filePath = filePath;
        this.score = score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public double getScore() {
        return score;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getFilePath() {
        return filePath;
    }
}
