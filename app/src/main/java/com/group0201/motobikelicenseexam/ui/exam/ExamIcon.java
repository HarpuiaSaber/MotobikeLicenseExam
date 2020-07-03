package com.group0201.motobikelicenseexam.ui.exam;

public class ExamIcon {
    private int testNum;
    private int duration;

    public ExamIcon(){

    }

    public ExamIcon(int testNum, int correctCount) {
        this.testNum = testNum;
        this.duration = correctCount;
    }

    public int getTestNum() {
        return testNum;
    }

    public void setTestNum(int testNum) {
        this.testNum = testNum;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
