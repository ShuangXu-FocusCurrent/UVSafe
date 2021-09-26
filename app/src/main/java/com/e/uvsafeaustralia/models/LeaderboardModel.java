package com.e.uvsafeaustralia.models;

public class LeaderboardModel {
    private String name;
    private String attemptedNumber;
    private String correctAnswerNumber;
    private String trophy;

    public LeaderboardModel(String name, String attemptedNumber, String correctAnswerNumber, String trophy) {
        this.name = name;
        this.attemptedNumber = attemptedNumber;
        this.correctAnswerNumber = correctAnswerNumber;
        this.trophy = trophy;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAttemptedNumber() {
        return attemptedNumber;
    }

    public void setAttemptedNumber(String attemptedNumber) {
        this.attemptedNumber = attemptedNumber;
    }

    public String getCorrectAnswerNumber() {
        return correctAnswerNumber;
    }

    public void setCorrectAnswerNumber(String correctAnswerNumber) {
        this.correctAnswerNumber = correctAnswerNumber;
    }

    public String getTrophy() {
        return trophy;
    }

    public void setTrophy(String trophy) {
        this.trophy = trophy;
    }
}
