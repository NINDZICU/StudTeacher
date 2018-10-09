package ru.kfpu.itis.studteacher.data.network.pojo;

import java.io.Serializable;

public class BodyIdTask implements Serializable {
    private int id;

    public BodyIdTask(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
