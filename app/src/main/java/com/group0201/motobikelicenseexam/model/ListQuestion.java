package com.group0201.motobikelicenseexam.model;

import java.io.Serializable;
import java.util.List;

public class ListQuestion implements Serializable {
    private long id;
    private List<Question> questions;

    public ListQuestion(){

    }

    public ListQuestion(long id, List<Question> questions) {
        this.id = id;
        this.questions = questions;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
