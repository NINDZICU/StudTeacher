package ru.kfpu.itis.studteacher.data.models;

import java.io.Serializable;
import java.util.List;

public class Test implements Serializable {
    private String text;
    private String recommendation;
    private int mark;
    private List<TestAnswer> answers;

    public Test(String text, String recommendation, int mark, List<TestAnswer> answers) {
        this.text = text;
        this.recommendation = recommendation;
        this.mark = mark;
        this.answers = answers;
    }

    public Test(String text, List<TestAnswer> testAnswers) {
        this.text = text;
        this.answers = testAnswers;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<TestAnswer> getTestAnswers() {
        return answers;
    }

    public void setTestAnswers(List<TestAnswer> testAnswers) {
        this.answers = testAnswers;
    }

    public String getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(String recommendation) {
        this.recommendation = recommendation;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public List<TestAnswer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<TestAnswer> answers) {
        this.answers = answers;
    }
}
