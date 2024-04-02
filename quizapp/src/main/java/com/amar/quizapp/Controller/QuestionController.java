package com.amar.quizapp.Controller;

import com.amar.quizapp.Model.Question;
import com.amar.quizapp.Service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {

    private final QuestionService questionService;
    @Autowired
    public QuestionController(QuestionService questionService){
        this.questionService=questionService;
    }

    @GetMapping("/allquestions")
    public ResponseEntity<List<Question>> getAllQuestions(){
        return questionService.getAllQuestions();
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String category){
        return questionService.getQuestionsByCategory(category);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addQuestion(@RequestBody Question question){
         return questionService.addQuestion(question);
    }

    @PutMapping("/update-question/{id}")
    public Question updateQuestionById(@PathVariable Integer id,
                                       @RequestBody Question question){
        return questionService.updateQuestionById(id,question);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteQuestionById(@PathVariable Integer id){
        return questionService.deleteQuestionById(id);
    }
}
