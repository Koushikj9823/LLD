package com.koushik.codeathon.entity;

import com.koushik.codeathon.converter.CoderContestQuestionsConverter;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "coder")
public class Coder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String email;
    private String username;
    private int score;

    @Convert(converter = CoderContestQuestionsConverter.class)
    CoderContestQuestions coderContestQuestions = new CoderContestQuestions();
}
