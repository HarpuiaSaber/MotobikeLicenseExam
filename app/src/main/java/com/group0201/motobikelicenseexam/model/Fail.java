package com.group0201.motobikelicenseexam.model;

import java.io.Serializable;

public class Fail implements Serializable {
    private long id;
    private String content;
    private int times;

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

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }
}
