package com.group0201.motobikelicenseexam.model;

public class BaiTest{
    private long id;
    private String content;
    private int time;
    private int type;
    private String createDate;
    private int userResult;

    public BaiTest() {
    }

    public BaiTest(long id, String content, int time, int type, String createDate, int userResult) {
        this.id = id;
        this.content = content;
        this.time = time;
        this.type = type;
        this.createDate = createDate;
        this.userResult = userResult;
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

    public int getUserResult() {
        return userResult;
    }

    public void setUserResult(int userResult) {
        this.userResult = userResult;
    }
}
