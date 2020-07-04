package com.group0201.motobikelicenseexam.model;

import java.io.Serializable;

public class AnswerResult implements Serializable {
    private long questionId;
    private boolean isRight;

    public AnswerResult(){

    }

    public AnswerResult(long questionId, boolean isRight) {
        this.questionId = questionId;
        this.isRight = isRight;
    }

    public long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(long questionId) {
        this.questionId = questionId;
    }

    public boolean isRight() {
        return isRight;
    }

    public void setRight(boolean right) {
        isRight = right;
    }
}
