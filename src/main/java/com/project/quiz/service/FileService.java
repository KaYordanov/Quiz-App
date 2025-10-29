package com.project.quiz.service;

import com.project.quiz.model.Quiz;

import java.util.List;

public interface FileService {
    void retrieveList();
    List<Quiz> retrieveQuizList();

    void shuffle(List<Quiz> quizList);
}
