package ru.kfpu.itis.studteacher.data.models;

import java.io.Serializable;
import java.util.List;

public class StudClass implements Serializable {
    private int id;
    private String classNumber;
    private List<Student> students;

    public StudClass(String classNumber, List<Student> students) {
        this.classNumber = classNumber;
        this.students = students;
    }

    public StudClass(int id, String classNumber, List<Student> students) {
        this.id = id;
        this.classNumber = classNumber;
        this.students = students;
    }

    public String getClassNumber() {
        return classNumber;
    }

    public void setClassNumber(String classNumber) {
        this.classNumber = classNumber;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
