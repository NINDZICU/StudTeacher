package ru.kfpu.itis.studteacher.data.network.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import ru.kfpu.itis.studteacher.data.models.Question;
import ru.kfpu.itis.studteacher.data.models.Test;

public class BodyAddTask implements Serializable {
    private String name;
    private String teacher_token;
    @SerializedName("tests")
    @Expose
    private List<Test> tests;
    private List<Question> questions;

    public BodyAddTask(String name, String teacher_token, List<Test> tests, List<Question> questions) {
        this.name = name;
        this.teacher_token = teacher_token;
        this.tests = tests;
        this.questions = questions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeacher_token() {
        return teacher_token;
    }

    public void setTeacher_token(String teacher_token) {
        this.teacher_token = teacher_token;
    }

    public List<Test> getTests() {
        return tests;
    }

    public void setTests(List<Test> tests) {
        this.tests = tests;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
