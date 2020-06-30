package com.group0201.motobikelicenseexam.ui.exam;

public class ExamIcon {
    private int testNum;
    private int failCount;
    private int correctCount;

    public ExamIcon(){

    }

    public ExamIcon(int testNum, int failCount, int correctCount) {
        this.testNum = testNum;
        this.failCount = failCount;
        this.correctCount = correctCount;
    }

    public int getTestNum() {
        return testNum;
    }

    public void setTestNum(int testNum) {
        this.testNum = testNum;
    }

    public int getFailCount() {
        return failCount;
    }

    public void setFailCount(int failCount) {
        this.failCount = failCount;
    }

    public int getCorrectCount() {
        return correctCount;
    }

    public void setCorrectCount(int correctCount) {
        this.correctCount = correctCount;
    }
}
