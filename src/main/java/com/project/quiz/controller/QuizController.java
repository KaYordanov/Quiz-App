package com.project.quiz.controller;

import com.project.quiz.model.Question;
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

    /*

    @GetMapping("/{id}")
    public String getQuizPage(@PathVariable ("id") int id,  Model model){
        Quiz quiz = quizService.getById(id);
        QuizForm form = new QuizForm();
        form.setQuizId(quiz.getId());

        model.addAttribute("quiz", quiz);
        model.addAttribute("form", form);

        return "quiz_page";
    }

     */

    @GetMapping("/{id}")
    public String getFirstQuizPage(@PathVariable ("id") int quizId,  Model model){
        Quiz q = quizService.getById(quizId);
        QuizForm form = new QuizForm(quizId, q.getQuestions().size());

        return getNextQuizPage(-1, "next", form,  model);
    }

    @PostMapping("/page/{pageNumber}")
    public String getNextQuizPage(@PathVariable ("pageNumber") int index,
                                  @RequestParam ("action") String action,
                                  @ModelAttribute ("form") QuizForm form,
                                  Model model){

        List<Question> questions = quizService.getById(form.getQuizId()).getQuestions();
        int totalPages = questions.size();
        List<String> a = form.getAnswers();


        if(action.equals("next")){
            index++;
        } else if (action.equals("previous")) {
            index--;
        } else if (action.equals("submit")){
            return "forward:/quizzes/submit";
        }
        Question questionToDisplay = questions.get(index);

        model.addAttribute("questionToDisplay", questionToDisplay);
        model.addAttribute("index", index);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("form", form);

        return "quiz-page-v2";
    }

    @PostMapping("/submit")
    public String getQuizResultPage(@ModelAttribute("form") QuizForm form, Model model){
        Quiz quiz = quizService.getById(form.getQuizId());
        List<String> answers = form.getAnswers();
        QuizResult result = quizService.getQuizResult(quiz.getQuestions(), answers);

        model.addAttribute("result", result);

        return "result_page";
    }
}
