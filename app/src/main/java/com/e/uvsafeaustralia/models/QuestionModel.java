package com.e.uvsafeaustralia.models;

public class QuestionModel {
    private int id;
    private int qNumber;
    private String question;
    private String answerOption1;
    private String answerOption2;
    private String answerOption3;
    private String answerOption4;
    private String correct;
    private String answerExplain;
    private EnumQCategory qCategory;
    public enum EnumQCategory{
        CATEGORY1,
        CATEGORY2,
        CATEGORY3,
        CATEGORY4
    };

    public QuestionModel(int id, EnumQCategory qCategory, int qNumber, String question, String answerOption1, String answerOption2, String answerOption3, String answerOption4, String correct, String answerExplain) {
        this.id = id;
        this.qCategory = qCategory;
        this.qNumber = qNumber;
        this.question = question;
        this.answerOption1 = answerOption1;
        this.answerOption2 = answerOption2;
        this.answerOption3 = answerOption3;
        this.answerOption4 = answerOption4;
        this.correct = correct;
        this.answerExplain = answerExplain;
    }

    public QuestionModel() {}

    @Override
    public String toString() {
        return "QuestionModel{" +
                "id=" + id +
                ", qCategory='" + qCategory + '\'' +
                ", qNumber='" + qNumber + '\'' +
                ", question='" + question + '\'' +
                ", answerOption1='" + answerOption1 + '\'' +
                ", answerOption2='" + answerOption2 + '\'' +
                ", answerOption3='" + answerOption3 + '\'' +
                ", answerOption4='" + answerOption4 + '\'' +
                ", correct='" + correct + '\'' +
                ", answerExplain='" + answerExplain + '\'' +
                '}';
    }

    public int getqId() {
        return id;
    }

    public void setqId(int id) {
        this.id = id;
    }

    public EnumQCategory getqCategory() {
        return qCategory;
    }

    public void setqCategory(EnumQCategory qCategory) {
        this.qCategory = qCategory;
    }

    public int getqNumber() {
        return qNumber;
    }

    public void setqNumber(int qNumber) {
        this.qNumber = qNumber;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswerOption1() {
        return answerOption1;
    }

    public void setAnswerOption1(String answerOption1) {
        this.answerOption1 = answerOption1;
    }

    public String getAnswerOption2() {
        return answerOption2;
    }

    public void setAnswerOption2(String answerOption2) {
        this.answerOption2 = answerOption2;
    }

    public String getAnswerOption3() {
        return answerOption3;
    }

    public void setAnswerOption3(String answerOption3) {
        this.answerOption3 = answerOption3;
    }

    public String getAnswerOption4() {
        return answerOption4;
    }

    public void setAnswerOption4(String answerOption4) {
        this.answerOption4 = answerOption4;
    }

    public String getCorrect() {
        return correct;
    }

    public void setCorrect(String correct) {
        this.correct = correct;
    }

    public String getAnswerExplain() {
        return answerExplain;
    }

    public void setAnswerExplain(String answerExplain) {
        this.answerExplain = answerExplain;
    }






}
