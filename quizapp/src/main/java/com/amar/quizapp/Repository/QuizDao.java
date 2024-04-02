package com.amar.quizapp.Repository;

import com.amar.quizapp.Model.Question;
import com.amar.quizapp.Model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizDao extends JpaRepository<Quiz,Integer> {


}
