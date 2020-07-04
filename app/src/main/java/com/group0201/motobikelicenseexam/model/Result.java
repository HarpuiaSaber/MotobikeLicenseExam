package com.group0201.motobikelicenseexam.model;

import java.io.Serializable;

public class Result implements Serializable {
    private long id;
    private String content;
    private int time;
    private int type;
    private String dateAt;
    private int totalCorrect;

    public Result() {
    }

    public Result(long id, String content, int time, int type, String dateAt, int totalCorrect) {
        this.id = id;
        this.content = content;
        this.time = time;
        this.type = type;
        this.dateAt = dateAt;
        this.totalCorrect = totalCorrect;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getDateAt() {
        return dateAt;
    }

    public void setDateAt(String dateAt) {
        this.dateAt = dateAt;
    }

    public int getTotalCorrect() {
        return totalCorrect;
    }

    public void setTotalCorrect(int totalCorrect) {
        this.totalCorrect = totalCorrect;
    }
}