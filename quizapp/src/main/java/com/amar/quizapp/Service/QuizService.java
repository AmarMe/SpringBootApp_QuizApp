package com.amar.quizapp.Service;

import com.amar.quizapp.Model.Question;
import com.amar.quizapp.Model.Quiz;
import com.amar.quizapp.Model.QuizWrapper;
import com.amar.quizapp.Model.SubmitResponse;
import com.amar.quizapp.Repository.QuestionDAO;
import com.amar.quizapp.Repository.QuizDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {
    private final QuizDao quizDao;
    private final QuestionDAO questionDAO;
    @Autowired
    public QuizService(QuizDao quizDao, QuestionDAO questionDAO){
        this.quizDao = quizDao;
        this.questionDAO=questionDAO;
    }


    public ResponseEntity<String> createQuiz(String title, int numQuiz, String category) {
        List<Question> questionList=questionDAO.findRandomQuestionsBycategory(category,numQuiz);
        Quiz quiz=new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questionList);
        quizDao.save(quiz);
        return new ResponseEntity<>("Quiz created successfully", HttpStatus.CREATED);
    }


    public ResponseEntity<List<QuizWrapper>> getQuizQuestions(Integer id) {
        Optional<Quiz> quiz1=quizDao.findById(id);
        List<Question> quizQuestions=quiz1.get().getQuestions();
        List<QuizWrapper> questionsForUser=new ArrayList<>();
        for(Question q:quizQuestions){
            QuizWrapper quizWrapper=new QuizWrapper(
                    q.getId(),q.getQuestionTitle(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4()
            );
            questionsForUser.add(quizWrapper);
        }
        return new ResponseEntity<>( questionsForUser,HttpStatus.OK);
    }

    public ResponseEntity<Integer> getSubmitScore(Integer id, List<SubmitResponse> submitResponses) {
        Optional<Quiz> quiz=quizDao.findById(id);
        List<Question> questionList=quiz.get().getQuestions();
        int score=0;
        int i=0;
        for(SubmitResponse response:submitResponses){
            if(response.getResponse().equals(questionList.get(i).getRightAnswer())){
                score++;
            }
            i++;
        }
        return new ResponseEntity<>(score,HttpStatus.OK);
    }
}
