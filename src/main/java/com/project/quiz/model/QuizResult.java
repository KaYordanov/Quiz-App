package com.project.quiz.model;

import java.util.List;

public class QuizResult {
    private List<Integer> correctAnswersIds;
    private List<Question> wrongAnswers;
    private String message;

    public QuizResult(List<Integer> correctAnswersIds, List<Question> wrongAnswers, String message) {
        this.correctAnswersIds = correctAnswersIds;
        this.wrongAnswers = wrongAnswers;
        this.message = message;
    }

    public int getCorrectAnswersCount(){
        return correctAnswersIds.size();
    }

    public void setMessage(String m){
        message = m;
    }

    public String getMessage(){
        return message;
    }

    public List<Integer> getCorrectAnswersIds() {
        return correctAnswersIds;
    }

    public void setCorrectAnswersIds(List<Integer> correctAnswersIds) {
        this.correctAnswersIds = correctAnswersIds;
    }

    public List<Question> getWrongAnswers() {
        return wrongAnswers;
    }

    public void setWrongAnswers(List<Question> wrongAnswers) {
        this.wrongAnswers = wrongAnswers;
    }
}
