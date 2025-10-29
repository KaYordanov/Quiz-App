package com.project.quiz.service;

import com.project.quiz.model.Quiz;

import java.util.List;

public interface FileService {
    void initializeList();
    List<Quiz> retrieveQuizList();

    void shuffle(List<Quiz> quizList);

    Quiz getQuizById(int id);
}
