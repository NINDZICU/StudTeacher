package ru.kfpu.itis.studteacher.data.models;

import java.io.Serializable;

public class Teacher implements Serializable {
    private User user;

    public Teacher(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
