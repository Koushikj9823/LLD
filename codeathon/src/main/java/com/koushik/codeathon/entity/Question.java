package com.koushik.codeathon.entity;

import com.koushik.codeathon.constants.Level;
import lombok.Data;

import javax.persistence.Entity;

@Entity
@Data
public class Question {
    private Long id;
    private String name;
    private Level level;
}
