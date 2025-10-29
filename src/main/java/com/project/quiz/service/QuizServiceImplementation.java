package com.project.quiz.service;

import com.project.quiz.model.Question;
import com.project.quiz.model.Quiz;
import com.project.quiz.model.QuizResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class QuizServiceImplementation implements QuizService {

    @Autowired
    FileService fileService;
    public QuizServiceImplementation(){}

    @Override
    public List<Quiz> getQuizList(){
        return fileService.retrieveQuizList();
    }

    @Override
    public Quiz getById(int id) {
        return  getQuizList().stream()
                    .filter(q -> q.getId() == id)
                    .findFirst()
                    .orElseThrow(IllegalArgumentException::new);
    }
    @Override
    public QuizResult getQuizResult(List<Question> questions, List<String> answers) {
        List<Integer> correctAnswersIds = new ArrayList<>();
        List<Question> wrongAnswers = new ArrayList<>();
        String message;

        if(answers == null || answers.isEmpty()){
            wrongAnswers.addAll(questions);
            message = "Try again!";
            return new QuizResult(correctAnswersIds, wrongAnswers, message);
        }

        for(int i = 0; i < questions.size(); i++){
            if(i < answers.size() && questions.get(i).getAnswer().equals(answers.get(i))){
                correctAnswersIds.add(questions.get(i).getId());
            } else {
                wrongAnswers.add(questions.get(i));
            }
        }

        int totalQuestions = questions.size();
        if(correctAnswersIds.size() >= (totalQuestions/2)){
            message = "Great Job!";
        } else {
            message = "Try again!";
        }

        return new QuizResult(correctAnswersIds, wrongAnswers, message);
    }
}
