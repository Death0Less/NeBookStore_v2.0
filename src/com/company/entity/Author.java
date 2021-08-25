package com.company.entity;

import java.io.Serializable;

public class Author implements Serializable {
    private int id;
    private String nickname;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Author(int id, String nickname) {
        this.id = id;
        this.nickname = nickname;
    }

    public Author(String nickname) {
        this.nickname = nickname;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
