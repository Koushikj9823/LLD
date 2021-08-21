package com.koushik.codeathon.service;

import com.koushik.codeathon.constants.Level;
import com.koushik.codeathon.constants.ResponseMessage;
import com.koushik.codeathon.entity.Contest;
import com.koushik.codeathon.entity.Question;
import com.koushik.codeathon.repository.ContestRepository;
import com.koushik.codeathon.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    QuestionRepository questionRepository;
    @Autowired
    ContestRepository contestRepository;

    public String createQuestion(String questionName, Level level,int score){
        if(!validateQuestion(questionName))
            return "Question "+ ResponseMessage.ALREADY_EXISTS;
        Question question = Question.builder().name(questionName).level(level).score(score).build();
        questionRepository.save(question);
        return "Question created!";
    }

    public List<Long> getAllQuestionsByLevel(Level level){
        return questionRepository.findAllQuestionsByLevel(level.toString());
    }

    public List<Question> getAllQuestionsByContest(String contestName){
        if(!contestRepository.findByName(contestName).isPresent())
            return null;
        Contest contest = contestRepository.findByName(contestName).get();
        List<Long> questionsId = contest.getContestQuestions().getQuestions();

        List<Question> questionList = new LinkedList<>();

        for (Long id :
                questionsId) {
            questionList.add(questionRepository.getById(id));
        }
        return questionList;
    }

    private boolean validateQuestion(String question){
        return questionRepository.findByName(question).isPresent();
    }
}
