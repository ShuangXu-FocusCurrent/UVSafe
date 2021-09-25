package com.e.uvsafeaustralia.models;

public class AnswerModel {
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
}
