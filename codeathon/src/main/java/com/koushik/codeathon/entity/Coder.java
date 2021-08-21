package com.koushik.codeathon.entity;

import com.koushik.codeathon.converter.CoderContestQuestionsConverter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table(name = "coder")
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
