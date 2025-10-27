package com.project.quiz.model;

import java.util.List;

public class QuizForm {
    private int quizId;
    private List<String> answers;

    public QuizForm(){}

    public QuizForm(int quizId, List<String> answers) {
        this.quizId = quizId;
        this.answers = answers;
    }

    public int getQuizId() {
        return quizId;
    }

    public void setQuizId(int quizId) {
        this.quizId = quizId;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }
}
