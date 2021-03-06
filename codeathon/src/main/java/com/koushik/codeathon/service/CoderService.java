package com.koushik.codeathon.service;

import com.koushik.codeathon.constants.AnswerStatus;
import com.koushik.codeathon.constants.ResponseMessage;
import com.koushik.codeathon.entity.Coder;
import com.koushik.codeathon.entity.Contest;
import com.koushik.codeathon.repository.CoderRepository;
import com.koushik.codeathon.repository.ContestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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

    public String createMultipleCoders(final List<Coder> coders){
        coders.stream().filter(Objects::nonNull).forEach(coder -> coder.setScore(0));
        coderRepository.saveAll(coders);
        return "Success";
    }

    public String joinContest(String contestName, String username){
        if(validateCoder(username))
            return "coder "+ResponseMessage.NOT_FOUND;
        if(validateContest(contestName))
            return "contest "+ ResponseMessage.NOT_FOUND;

        Contest contest = contestRepository.findByName(contestName).get();
        Coder coder= coderRepository.findByUsername(username).get();

        List<Long> questionList = contest.getContestQuestions().getQuestions();

        Map<String, HashMap<Long, AnswerStatus>> fetchedQuestions = coder.getCoderContestQuestions().getContestToQuestionsMap();
        fetchedQuestions.putIfAbsent(contestName, initializeAnswerStatus(questionList));

        Coder updatedCoder = coderRepository.findByUsername(username).get();
        updatedCoder.getCoderContestQuestions().setContestToQuestionsMap(fetchedQuestions);
        coderRepository.save(updatedCoder);

        return "Contest Joined";
    }
    private HashMap<Long, AnswerStatus> initializeAnswerStatus(List<Long> questionsList){
        HashMap<Long, AnswerStatus> questionMap = new HashMap<>();
        for (Long questionId :
                questionsList) {
            questionMap.put(questionId,AnswerStatus.UNSOLVED);
        }
        return questionMap;
    }
    public String withdrawContest(String contestName, String username){

        if(validateCoder(username))
            return "coder "+ResponseMessage.NOT_FOUND;
        if(validateContest(contestName))
            return "contest "+ ResponseMessage.NOT_FOUND;


        Coder updatedCoder = coderRepository.findByUsername(username).get();
        Map<String, HashMap<Long, AnswerStatus>> fetchedQuestions = updatedCoder.getCoderContestQuestions().getContestToQuestionsMap();
        fetchedQuestions.remove(contestName);
        updatedCoder.getCoderContestQuestions().setContestToQuestionsMap(fetchedQuestions);
        coderRepository.save(updatedCoder);

        return "Contest withdrawn";
    }

    public List<Coder> getAllContestCoders(final String contestName){
        final List<Coder> coderList = new LinkedList<>();
        coderRepository.findAll().forEach(coder -> {
            final Map<String, HashMap<Long, AnswerStatus>> contestQuestions = coder.getCoderContestQuestions().getContestToQuestionsMap();
            if(contestQuestions.containsKey(contestName)){
                coderList.add(coder);
            }
        });
        return coderList;
    }

    private boolean validateContest(String contest){
        return !contestRepository.findByName(contest).isPresent();
    }
    private boolean validateCoder(String coder){
        return !coderRepository.findByUsername(coder).isPresent();
    }
}
