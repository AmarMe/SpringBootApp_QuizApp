package com.amar.quizapp.Service;

import com.amar.quizapp.Model.Question;
import com.amar.quizapp.Repository.QuestionDAO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    private final QuestionDAO questionDAO;
    @Autowired
    public QuestionService(QuestionDAO questionDAO){
        this.questionDAO=questionDAO;
    }


    public ResponseEntity<List<Question>> getAllQuestions() {
        try{
            return new ResponseEntity<>(questionDAO.findAll(),HttpStatus.OK);
        }catch(DataAccessException e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
        try{
            return new ResponseEntity<>(questionDAO.findByCategory(category),HttpStatus.OK);
        }catch(DataAccessException e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity<String> addQuestion(Question question) {
         questionDAO.save(question);
         return new ResponseEntity<>("added Successfully",HttpStatus.CREATED);
    }

    @Transactional
    public Question updateQuestionById(Integer id, Question question) {
        Question question1 =questionDAO.findById(id)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Question ID"+id+" Not exitsts"));
        if(question.getDifficultyLevel()!=null || !question1.getDifficultyLevel().isEmpty()){
            if(question.getDifficultyLevel().equals(question1.getDifficultyLevel()))
                throw new IllegalStateException("Updated and Old values are same");
            question1.setDifficultyLevel(question.getDifficultyLevel());
        }else {
            throw new IllegalStateException("Category should not be null OR empty");
        }
        return question1;
    }

    public ResponseEntity<String> deleteQuestionById(Integer id) {
        boolean IdExists=questionDAO.existsById(id);
        if(!IdExists){
            throw new IllegalStateException("Id "+id+" not exists");
        }
        questionDAO.deleteById(id);
        return new ResponseEntity<>("Deleted "+id+"'th question successfully",HttpStatus.OK);
    }


}
