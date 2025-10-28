package com.project.quiz.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.quiz.model.Question;
import com.project.quiz.model.Quiz;
import com.project.quiz.model.QuizResult;
import org.springframework.stereotype.Service;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class QuizServiceImplementation implements QuizService {

    //@Autowired
    // FileService fileService;
    private File file = new File("src/main/resources/static/quiz_file.json");
    private final List<Quiz> quizList;

    public QuizServiceImplementation(){
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            quizList = objectMapper.readValue(file, new TypeReference<List<Quiz>>(){});
            shuffle();
        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("Failed to retrieve the data from the json file 'quiz_file.json'");
        }
    }

    @Override
    public void shuffle(){
        for(var quiz : quizList){
            Collections.shuffle(quiz.getQuestions());

            for(var question : quiz.getQuestions()){
                Collections.shuffle(question.getOptions());
            }
        }
    }

    @Override
    public List<Quiz> getQuizList(){
        return quizList;
    }

    @Override
    public Quiz getById(int id) {
        return  quizList.stream()
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

        int totalQuestions = answers.size();
        if(correctAnswersIds.size() >= (totalQuestions/2)){
            message = "Great Job!";
        } else {
            message = "Try again!";
        }

        return new QuizResult(correctAnswersIds, wrongAnswers, message);
    }
}
