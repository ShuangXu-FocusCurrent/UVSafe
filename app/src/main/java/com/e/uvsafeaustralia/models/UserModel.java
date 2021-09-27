package com.e.uvsafeaustralia.models;

import android.os.Parcel;
import android.os.Parcelable;

public class UserModel implements Parcelable {
    private int id;
    private String nickName;

    public UserModel(int id, String nickName) {
        this.id = id;
        this.nickName = nickName;
    }

    public UserModel() { }

    protected UserModel(Parcel in) {
        id = in.readInt();
        nickName = in.readString();
    }

    public static final Creator<UserModel> CREATOR = new Creator<UserModel>() {
        @Override
        public UserModel createFromParcel(Parcel in) {
            return new UserModel(in);
        }

        @Override
        public UserModel[] newArray(int size) {
            return new UserModel[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(nickName);
    }
}
