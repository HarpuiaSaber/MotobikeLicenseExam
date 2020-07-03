package com.group0201.motobikelicenseexam.model;

import java.io.Serializable;

public class Answer implements Serializable {
    private long id;
    private String content;
    private boolean isCorrect;

    public Answer(){

    }

    public Answer(long id, String content, boolean isCorrect) {
        this.id = id;
        this.content = content;
        this.isCorrect = isCorrect;
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

    public boolean getIsCorrect() {
        return isCorrect;
    }

    public void setIsCorrect(boolean correct) {
        isCorrect = correct;
    }
}
