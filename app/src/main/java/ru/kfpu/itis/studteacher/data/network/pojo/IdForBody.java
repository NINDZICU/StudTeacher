package ru.kfpu.itis.studteacher.data.network.pojo;

import java.io.Serializable;

public class IdForBody implements Serializable {
    private int id;

    public IdForBody(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
