package com.project.quiz.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.quiz.model.Quiz;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Collections;
import java.util.List;

@Service
public class FileServiceImplementation implements FileService{
    private File file = new File("src/main/resources/static/quiz_file.json");
    private List<Quiz> quizList;

    public FileServiceImplementation(){
        initializeList();
    }

    @Override
    public void initializeList(){
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            quizList = objectMapper.readValue(file, new TypeReference<List<Quiz>>(){});

        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("Failed to retrieve the data from the json file 'quiz_file.json'");
        }
    }

    @Override
    public List<Quiz> retrieveQuizList(){
        shuffle(quizList);
        return quizList;
    }

    @Override
    public void shuffle(List<Quiz> quizList){
        for(var quiz : quizList){
            Collections.shuffle(quiz.getQuestions());

            for(var question : quiz.getQuestions()){
                Collections.shuffle(question.getOptions());
            }
        }
    }

    @Override
    public Quiz getQuizById(int id) {
        return quizList.stream()
                .filter(q -> q.getId() == id)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
