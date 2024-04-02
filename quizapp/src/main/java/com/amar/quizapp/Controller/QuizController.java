package com.amar.quizapp.Controller;

import com.amar.quizapp.Model.Question;
import com.amar.quizapp.Model.QuizWrapper;
import com.amar.quizapp.Model.SubmitResponse;
import com.amar.quizapp.Service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {
    private final QuizService quizService;
    @Autowired
    public QuizController(QuizService quizService){
        this.quizService=quizService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createQuiz(@RequestParam String title,@RequestParam int numQuiz,@RequestParam String category){
        return quizService.createQuiz(title,numQuiz,category);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<List<QuizWrapper>> getQuizQuestions(@PathVariable Integer id){
        return quizService.getQuizQuestions(id);
    }

    @PostMapping("/submit/{id}")
    public ResponseEntity<Integer> getSubmitScore(@PathVariable Integer id,@RequestBody List<SubmitResponse> submitResponses){
        return quizService.getSubmitScore(id,submitResponses);
    }

}
