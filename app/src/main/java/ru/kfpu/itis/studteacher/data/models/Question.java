package ru.kfpu.itis.studteacher.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Question implements Serializable {
    @SerializedName("text")
    @Expose
    private String textOfTask;
    @SerializedName("mark")
    @Expose
    private int score;
    private String answer;
    private String recommendation;

    public Question(String textOfTask, int score, String answer, String recommendation) {
        this.textOfTask = textOfTask;
        this.score = score;
        this.answer = answer;
        this.recommendation = recommendation;
    }

    public String getTextOfTask() {
        return textOfTask;
    }

    public void setTextOfTask(String textOfTask) {
        this.textOfTask = textOfTask;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(String recommendation) {
        this.recommendation = recommendation;
    }
}

