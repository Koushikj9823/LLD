package com.koushik.codeathon;

import com.koushik.codeathon.constants.AnswerStatus;
import com.koushik.codeathon.constants.Level;
import com.koushik.codeathon.entity.Coder;
import com.koushik.codeathon.entity.Contest;
import com.koushik.codeathon.entity.Question;
import com.koushik.codeathon.repository.CoderRepository;
import com.koushik.codeathon.repository.QuestionRepository;
import com.koushik.codeathon.service.CoderService;
import com.koushik.codeathon.service.ContestService;
import com.koushik.codeathon.service.QuestionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Slf4j
@SpringBootApplication
@RequiredArgsConstructor
public class CodeathonApplication implements CommandLineRunner {
    @Autowired
    CoderService coderService;
    @Autowired
    ContestService contestService;
    @Autowired
    QuestionService questionService;
    @Autowired
    CoderRepository coderRepository;
    @Autowired
    QuestionRepository questionRepository;


    public static void main(String[] args) {
        SpringApplication.run(CodeathonApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        //create Coders

        final Coder ninja = Coder.builder().email("ninja@gmail.com").score(0).username("ninja@123").build();
        final Coder shockwave = Coder.builder().email("shockwave@gmail.com").score(0).username("shockwave@23").build();
        final Coder jon_snow = Coder.builder().email("jon_snow@gmail.com").score(0).username("jonKhaleesi@123").build();
        final Coder jesse = Coder.builder().email("jesse@gmail.com").score(0).username("jesse@123").build();

        coderService.createMultipleCoders(Arrays.asList(ninja,shockwave,jon_snow,jesse));

        coderRepository.findAll().forEach(System.out::println);

        //create Questions
            final Question question1 = Question.builder().name("Print binary tree in reverse").level(Level.MEDIUM).score(25).build();
            final Question question2 = Question.builder().name("reverse linked list").level(Level.MEDIUM).score(25).build();
            final Question question3 = Question.builder().name("find prime number between 1 to 10").level(Level.EASY).score(10).build();
            final Question question4 = Question.builder().name("Balance Parenthesis").level(Level.MEDIUM).score(25).build();
            final Question question5 = Question.builder().name("Find Longest Common Subsequence").level(Level.HARD).score(50).build();
            final Question question6 = Question.builder().name("Word Search").level(Level.HARD).score(50).build();
            final Question question7 = Question.builder().name("Find Median in Stream").level(Level.EASY).score(10).build();

            questionRepository.saveAll(Arrays.asList(question1,question2,question3,question4,question5,question6,question7));

            questionRepository.findAll().forEach(System.out::println);


            //create Contest

        final Contest contest1 = contestService.createContest("Codeathon Challenge",Level.MEDIUM,"ninja@123");
        final Contest contest2 = contestService.createContest("Kaboom Challenge",Level.EASY,"jonKhaleesi@123");
        final Contest contest3 = contestService.createContest("Ultimate Challenge",Level.HARD,"shockwave@23");

        //register for Contest
        coderService.joinContest(contest1.getName(),ninja.getUsername());
        coderService.joinContest(contest2.getName(),jesse.getUsername());
        coderService.joinContest(contest3.getName(),shockwave.getUsername());

        HashMap<Long, AnswerStatus> ninjaQuestionStatus = new HashMap<>();
        ninjaQuestionStatus.put(question1.getId(),AnswerStatus.PARTIALLY_SOLVED);
        ninjaQuestionStatus.put(question2.getId(),AnswerStatus.SOLVED);
        ninjaQuestionStatus.put(question4.getId(),AnswerStatus.UNSOLVED);
        contestService.calculateScore(ninja.getUsername(),ninjaQuestionStatus,25,contest1.getName());

        HashMap<Long, AnswerStatus> jesseQuestionStatus = new HashMap<>();
        jesseQuestionStatus.put(question3.getId(),AnswerStatus.PARTIALLY_SOLVED);
        jesseQuestionStatus.put(question7.getId(),AnswerStatus.SOLVED);
        contestService.calculateScore(jesse.getUsername(),jesseQuestionStatus,10,contest2.getName());

        HashMap<Long, AnswerStatus> shockwaveQuestionStatus = new HashMap<>();
        shockwaveQuestionStatus.put(question6.getId(),AnswerStatus.SOLVED);
        shockwaveQuestionStatus.put(question5.getId(),AnswerStatus.SOLVED);
        contestService.calculateScore(shockwave.getUsername(),shockwaveQuestionStatus,50,contest3.getName());

        //Leader board

        final List<Coder> coderList = coderRepository.leaderBoard();
        log.info("LeaderBoard");
        coderList.forEach(System.out::println);







    }
}
