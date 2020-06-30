package com.group0201.motobikelicenseexam.model;

import java.io.Serializable;

public class Law implements Serializable {
    private long Id;
    private String Content;
    private int Type;
    private String Punishment;

    public Law(long id, String content, int type, String punishment) {
        Id = id;
        Content = content;
        Type = type;
        Punishment = punishment;
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public int getType() {
        return Type;
    }

    public void setType(int type) {
        Type = type;
    }

    public String getPunishment() {
        return Punishment;
    }

    public void setPunishment(String punishment) {
        Punishment = punishment;
    }
    public String toString(){
        return this.Id + " - " + this.Content;
    }
}
