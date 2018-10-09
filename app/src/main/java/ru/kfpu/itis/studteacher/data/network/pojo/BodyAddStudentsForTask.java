package ru.kfpu.itis.studteacher.data.network.pojo;

import java.io.Serializable;
import java.util.List;

public class BodyAddStudentsForTask implements Serializable {
    private int task_id;
    private List<IdForBody> students;

    public BodyAddStudentsForTask(int task_id, List<IdForBody> students) {
        this.task_id = task_id;
        this.students = students;
    }

    public int getTask_id() {
        return task_id;
    }

    public void setTask_id(int task_id) {
        this.task_id = task_id;
    }

    public List<IdForBody> getStudents() {
        return students;
    }

    public void setStudents(List<IdForBody> students) {
        this.students = students;
    }

}
