package com.koushik.codeathon.service;

import com.koushik.codeathon.constants.AnswerStatus;
import com.koushik.codeathon.constants.ContestStatus;
import com.koushik.codeathon.constants.Level;
import com.koushik.codeathon.constants.ResponseMessage;
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

    public String createContest(String name, Level level, String coderName){
        if(!coderRepository.findByUsername(coderName).isPresent())
            return "contest creation was "+ResponseMessage.FAILED;

        Coder coder = coderRepository.findByUsername(coderName).get();
        List<Long> questionsList = getQuestionsByLevel(level);

        final ContestQuestions question = ContestQuestions.builder().questions(questionsList).build();
        final Contest newContest = Contest.builder().name(name)
                                .userId(coder.getId()).status(ContestStatus.STARTED).contestQuestions(question)
                                .build();
        contestRepository.save(newContest);
        return "contest creation was "+ResponseMessage.SUCCESS;
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

    private void calculateScore(String coderName,HashMap<Long,AnswerStatus> userQuestionStatus,int scoreConstant){

        if(coderRepository.findByUsername(coderName).isPresent()){
            Coder coder = coderRepository.findByUsername(coderName).get();
            int score = 0;
            for(Long id:userQuestionStatus.keySet()) {
                AnswerStatus status = userQuestionStatus.get(id);
                if (status == AnswerStatus.SOLVED) {
                    score += (scoreConstant);
                } else if (status == AnswerStatus.PARTIALLY_SOLVED) {
                    score += (scoreConstant*0.50);
                } else
                    score += 0;
            }
            coder.setScore(coder.getScore()+score);
            coderRepository.save(coder);
        }
    }

    private List<Coder> leaderBoard(String contestName){

        if(contestRepository.findByName(contestName).isPresent()){
//            Contest contest = contestRepository.findByName(contestName).get();
            final List<Coder> coderList = coderService.getAllContestCoders(contestName);
            coderList.sort((Coder c1,Coder c2)->c2.getScore()-c1.getScore());
            return coderList;

        }
        else
            return null;
    }
}
