package com.project.quiz.service;

import com.project.quiz.model.Question;
import com.project.quiz.model.Quiz;
import com.project.quiz.model.QuizForm;
import com.project.quiz.model.QuizResult;

import java.util.List;

public interface QuizService {
    List<Quiz> retrieveQuizzes();
    Quiz getById(int id);
    QuizResult getQuizResult(List<Question> questions, List<String> answers);
}
