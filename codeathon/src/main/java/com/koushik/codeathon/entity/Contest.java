package com.koushik.codeathon.entity;

import com.koushik.codeathon.constants.ContestStatus;
import com.koushik.codeathon.constants.Level;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

@Entity
@Data
public class Contest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Level level;
    private ContestStatus status;
    private List<Question> questionList;
    private List<Coder> coderList;

}
