package com.project.quiz.controller;

import com.project.quiz.model.Quiz;
import com.project.quiz.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private QuizService quizService;


    @GetMapping
    public String getHomePage(Model model){
        List<Quiz> quizList = quizService.getQuizList();

        model.addAttribute("quizzes", quizList);
        return "home_page";
    }
}
