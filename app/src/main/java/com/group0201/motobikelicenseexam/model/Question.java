package com.group0201.motobikelicenseexam.model;

import java.io.Serializable;

public class Question implements Serializable {

    private long id;
    private String content;
    private String explanation;
    private int type;
    private String image;
    private Answer[] answers;

    public Question (){

    }

    public Question(long id, String content, String image, Answer[] answers) {
        this.id = id;
        this.content = content;
        this.image = image;
        this.answers = answers;
    }

    public Question(long id, String content, String explanation, int type, String image, Answer[] answers) {
        this.id = id;
        this.content = content;
        this.explanation = explanation;
        this.type = type;
        this.image = image;
        this.answers = answers;
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

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Answer[] getAnswers() {
        return answers;
    }

    public void setAnswers(Answer[] answers) {
        this.answers = answers;
    }
}
