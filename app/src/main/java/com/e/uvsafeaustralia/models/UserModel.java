package com.e.uvsafeaustralia.models;

public class UserModel {
    private int id;
    private String nickName;

    public UserModel(int id, String nickName) {
        this.id = id;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "id=" + id +
                ", nickName='" + nickName + '\'' +
                '}';
    }

    public int getUserId() {
        return id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setUserId(int id) {
        this.id = id;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

}
