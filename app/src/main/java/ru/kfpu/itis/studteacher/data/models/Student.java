package ru.kfpu.itis.studteacher.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Student implements Serializable {
    @SerializedName("student_id")
    @Expose
    private int id;
    private String name;
    private int classNumber;
    private User user;

    public Student(String name, int classNumber, User user) {
        this.name = name;
        this.classNumber = classNumber;
        this.user = user;
    }

    public Student(int id, String name, int classNumber, User user) {
        this.id = id;
        this.name = name;
        this.classNumber = classNumber;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getClassNumber() {
        return classNumber;
    }

    public void setClassNumber(int classNumber) {
        this.classNumber = classNumber;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
