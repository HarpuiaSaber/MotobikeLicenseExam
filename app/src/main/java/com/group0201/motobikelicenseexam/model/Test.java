package com.group0201.motobikelicenseexam.model;

import java.io.Serializable;

public class Test implements Serializable {
    private long id;
    private String content;
    private int time;
    private int type;
    private String createDate;

    public Test() {
    }

    public Test(long id, String content, int time, int type, String createDate) {
        this.id = id;
        this.content = content;
        this.time = time;
        this.type = type;
        this.createDate = createDate;
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

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

}