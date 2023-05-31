package com.example.quizapp;

public class Question {
    private final int answerResId;
    private final boolean answerTrue;
    public Question(int answerResId, boolean answerTrue) {
        this.answerResId = answerResId;
        this.answerTrue = answerTrue;
    }
    public int getAnswerResId() {
        return answerResId;
    }
    public boolean isAnswerTrue() {
        return answerTrue;
    }
}
