package com.group0201.motobikelicenseexam.model;

import java.io.Serializable;

public class Ranking implements Serializable {
    private long UserId;
    private String userName;
    private long ExamId;
    private int time;
    private int totalCorrect;
    private String dateAt;

    public Ranking() {
    }

    public Ranking(long userId, String userName, long examId, int time, int totalCorrect, String dateAt) {
        UserId = userId;
        this.userName = userName;
        ExamId = examId;
        this.time = time;
        this.totalCorrect = totalCorrect;
        this.dateAt = dateAt;
    }

    public long getUserId() {
        return UserId;
    }

    public void setUserId(long userId) {
        UserId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public long getExamId() {
        return ExamId;
    }

    public void setExamId(long examId) {
        ExamId = examId;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getTotalCorrect() {
        return totalCorrect;
    }

    public void setTotalCorrect(int totalCorrect) {
        this.totalCorrect = totalCorrect;
    }

    public String getDateAt() {
        return dateAt;
    }

    public void setDateAt(String dateAt) {
        this.dateAt = dateAt;
    }
}
