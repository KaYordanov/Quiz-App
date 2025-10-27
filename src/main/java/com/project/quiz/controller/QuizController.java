package com.project.quiz.controller;

import com.project.quiz.model.Quiz;
import com.project.quiz.model.QuizForm;
import com.project.quiz.model.QuizResult;
import com.project.quiz.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/quizzes")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @GetMapping("/{id}")
    public String getQuizPage(@PathVariable ("id") int id,  Model model){
        Quiz quiz = quizService.getById(id);
        QuizForm form = new QuizForm();
        form.setQuizId(quiz.getId());

        model.addAttribute("quiz", quiz);
        model.addAttribute("form", form);

        return "quiz_page";
    }

    @PostMapping("/submit")
    public String getQuizResultPage(@ModelAttribute("form") QuizForm form, Model model){
        Quiz quiz = quizService.getById(form.getQuizId());
        List<String> answers = form.getAnswers();
        QuizResult result = quizService.getQuizResult(quiz.getQuestions(), answers);

        model.addAttribute("result", result);

        //model.addAttribute("correctAnswersCount", correctAnswersIds.size());
        //model.addAttribute("incorrectAnswers", wrongAnswers);

        return "result_page";
    }
}
