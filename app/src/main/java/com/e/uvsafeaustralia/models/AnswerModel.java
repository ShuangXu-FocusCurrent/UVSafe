package com.e.uvsafeaustralia.models;

public class AnswerModel {
    private int id;
    private int userId;
    private int questionId;
    private String selected;
    private int status;

    public AnswerModel(int id, UserModel userModel, QuestionModel questionModel, String selected, int status) {
        this.id = id;
        this.userId = userModel.getUserId();
        this.questionId = questionModel.getqId();
        this.selected = selected;
        this.status = status;
    }

    @Override
    public String toString() {
        return "AnswerModel{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", questionId='" + questionId + '\'' +
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(UserModel userModel) {
        this.userId = userModel.getUserId();
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(QuestionModel questionModel) {
        this.questionId = questionModel.getqId();
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
