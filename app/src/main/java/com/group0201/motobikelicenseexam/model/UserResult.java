package com.group0201.motobikelicenseexam.model;

import java.io.Serializable;
import java.util.Map;

public class UserResult implements Serializable {

    private long userId;
    private long examId;
    private int time;
    private int totalCorrect;
    private AnswerResult[] answers;

    public UserResult(){

    }

    public UserResult(long userId, long examId, int time, int totalCorrect, AnswerResult[] answers) {
        this.userId = userId;
        this.examId = examId;
        this.time = time;
        this.totalCorrect = totalCorrect;
        this.answers = answers;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getExamId() {
        return examId;
    }

    public void setExamId(long examId) {
        this.examId = examId;
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

    public AnswerResult[] getAnswers() {
        return answers;
    }

    public void setAnswers(AnswerResult[] answers) {
        this.answers = answers;
    }
}
