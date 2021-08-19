package com.koushik.codeathon.entity;

import com.koushik.codeathon.constants.Level;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "questions")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Level level;
}
