package ru.kfpu.itis.studteacher.data.models;

import java.io.Serializable;
import java.util.List;

public class Task implements Serializable {
    private int id;
    private String name;
    private Teacher teacher;
    private List<Question> questions;
    private List<Test> testQuestions;

    public Task(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Task(String name, Teacher teacher, List<Question> questions, List<Test> testQuestions) {
        this.name = name;
        this.teacher = teacher;
        this.questions = questions;
        this.testQuestions = testQuestions;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public List<Test> getTestQuestions() {
        return testQuestions;
    }

    public void setTestQuestions(List<Test> testQuestions) {
        this.testQuestions = testQuestions;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
