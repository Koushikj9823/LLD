package com.koushik.codeathon.service;

import com.koushik.codeathon.constants.ResponseMessage;
import com.koushik.codeathon.entity.Coder;
import com.koushik.codeathon.entity.Contest;
import com.koushik.codeathon.repository.CoderRepository;
import com.koushik.codeathon.repository.ContestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CoderService {

    @Autowired
    CoderRepository coderRepository;

    @Autowired
    ContestRepository contestRepository;
    public Coder getCoderDetails(String coderName){
        if(validateCoder(coderName))
            return null;
        return coderRepository.findByUsername(coderName).get();
    }

    public ResponseMessage createCoder(Coder coder){
        if(coderRepository.findByUsername(coder.getUsername()).isPresent())
            return ResponseMessage.ALREADY_EXISTS;

        coderRepository.save(coder);
        return ResponseMessage.SUCCESS;
    }

    public String joinContest(String contestName, String username){
        if(validateCoder(username))
            return "coder "+ResponseMessage.NOT_FOUND;
        if(validateContest(contestName))
            return "contest "+ ResponseMessage.NOT_FOUND;

        Contest contest = contestRepository.findByName(contestName).get();
        Coder coder= coderRepository.findByUsername(username).get();

        List<Long> questionList = contest.getContestQuestions().getQuestions();
        Map<String,List<Long>> fetchedQuestions = coder.getCoderContestQuestions().getContestToQuestionsMap();
        fetchedQuestions.putIfAbsent(contestName,questionList);

        Coder updatedCoder = coderRepository.findByUsername(username).get();
        updatedCoder.getCoderContestQuestions().setContestToQuestionsMap(fetchedQuestions);
        coderRepository.save(updatedCoder);

        return "Contest Joined";
    }
    public String withdrawContest(String contestName, String username){

        if(validateCoder(username))
            return "coder "+ResponseMessage.NOT_FOUND;
        if(validateContest(contestName))
            return "contest "+ ResponseMessage.NOT_FOUND;


        Coder updatedCoder = coderRepository.findByUsername(username).get();
        Map<String,List<Long>> fetchedQuestions = updatedCoder.getCoderContestQuestions().getContestToQuestionsMap();
        fetchedQuestions.remove(contestName);
        updatedCoder.getCoderContestQuestions().setContestToQuestionsMap(fetchedQuestions);
        coderRepository.save(updatedCoder);

        return "Contest withdrawn";
    }

    private boolean validateContest(String contest){
        return !contestRepository.findByName(contest).isPresent();
    }
    private boolean validateCoder(String coder){
        return !coderRepository.findByUsername(coder).isPresent();
    }
}
