package com.koushik.codeathon.entity;

import com.koushik.codeathon.constants.Level;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "questions")
@Builder
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private Level level;
    private int score;
}
