package com.koushik.codeathon.service;

import com.koushik.codeathon.constants.AnswerStatus;
import com.koushik.codeathon.constants.ContestStatus;
import com.koushik.codeathon.constants.Level;
import com.koushik.codeathon.entity.Coder;
import com.koushik.codeathon.entity.Contest;
import com.koushik.codeathon.entity.ContestQuestions;
import com.koushik.codeathon.repository.CoderRepository;
import com.koushik.codeathon.repository.ContestRepository;
import com.koushik.codeathon.repository.QuestionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class ContestService {


    @Autowired
    ContestRepository contestRepository;
    @Autowired
    QuestionRepository questionRepository;
    @Autowired
    CoderRepository coderRepository;
    @Autowired
    CoderService coderService;

    public Contest createContest(String name, Level level, String coderName){
        if(!coderRepository.findByUsername(coderName).isPresent())
            return null;

        Coder coder = coderRepository.findByUsername(coderName).get();
        List<Long> questionsList = getQuestionsByLevel(level);

        final ContestQuestions question = ContestQuestions.builder().questions(questionsList).build();
        final Contest newContest = Contest.builder().name(name)
                                .userId(coder.getId()).status(ContestStatus.STARTED).contestQuestions(question)
                                .build();
        contestRepository.save(newContest);
        return newContest;
    }

    private List<Long> getQuestionsByLevel(Level level){
        return questionRepository.findAllQuestionsByLevel(level.toString());
    }

    private List<Long> contestWiseAllQuestions(String contestName){
        if(contestRepository.findByName(contestName).isPresent()) {
            Contest contest = contestRepository.findByName(contestName).get();
            return contest.getContestQuestions().getQuestions();
        }
        return null;
    }

    public void calculateScore(String coderName, HashMap<Long, AnswerStatus> coderQuestionStatus, int scoreConstant, String contestName){

        if(coderRepository.findByUsername(coderName).isPresent()){
            Coder coder = coderRepository.findByUsername(coderName).get();
            int score = 0;
            for(Long id:coderQuestionStatus.keySet()) {
                AnswerStatus status = coderQuestionStatus.get(id);
                if (status == AnswerStatus.SOLVED) {
                    score += (scoreConstant);
                } else if (status == AnswerStatus.PARTIALLY_SOLVED) {
                    score += (scoreConstant*0.50);
                } else
                    score += 0;
            }
            coder.setScore(coder.getScore()+score);

            Map<String, HashMap<Long, AnswerStatus>> coderContestList = coder.getCoderContestQuestions().getContestToQuestionsMap();
            coderContestList.put(contestName,coderQuestionStatus);
            coder.getCoderContestQuestions().setContestToQuestionsMap(coderContestList);

            coderRepository.save(coder);
        }
    }

}
