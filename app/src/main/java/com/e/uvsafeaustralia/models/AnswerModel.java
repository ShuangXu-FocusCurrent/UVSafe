package com.e.uvsafeaustralia.models;

import android.os.Parcel;
import android.os.Parcelable;

public class AnswerModel implements Parcelable {
    private int id;
    private UserModel user;
    private QuestionModel question;
    private String selected;
    private int status;

    public AnswerModel(int id, UserModel userModel, QuestionModel questionModel, String selected, int status) {
        this.id = id;
        this.user = userModel;
        this.question = questionModel;
        this.selected = selected;
        this.status = status;
    }

    public AnswerModel(){}

    protected AnswerModel(Parcel in) {
        id = in.readInt();
        user = in.readParcelable(UserModel.class.getClassLoader());
        question = in.readParcelable(QuestionModel.class.getClassLoader());
        selected = in.readString();
        status = in.readInt();
    }

    public static final Creator<AnswerModel> CREATOR = new Creator<AnswerModel>() {
        @Override
        public AnswerModel createFromParcel(Parcel in) {
            return new AnswerModel(in);
        }

        @Override
        public AnswerModel[] newArray(int size) {
            return new AnswerModel[size];
        }
    };

    @Override
    public String toString() {
        return "AnswerModel{" +
                "id=" + id +
                ", user='" + user + '\'' +
                ", question='" + question + '\'' +
                ", selected='" + selected + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel userModel) {
        this.user = userModel;
    }

    public QuestionModel getQuestion() { return question; }

    public void setQuestion(QuestionModel questionModel) {
        this.question = questionModel;
    }

    public String getSelected() {
        return selected;
    }

    public void setSelected(String selected) {
        this.selected = selected;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeParcelable(user, flags);
        dest.writeParcelable(question, flags);
        dest.writeString(selected);
        dest.writeInt(status);
    }
}
