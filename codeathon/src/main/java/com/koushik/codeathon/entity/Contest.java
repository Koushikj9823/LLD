package com.koushik.codeathon.entity;

import com.koushik.codeathon.constants.ContestStatus;
import com.koushik.codeathon.constants.Level;
import com.koushik.codeathon.converter.ContestQuestionsConverter;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Builder
@Table(name = "contest")
public class Contest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long userId;
    private String name;
    @Enumerated(EnumType.STRING)
    private Level level;
    @Enumerated(EnumType.STRING)
    private ContestStatus status;

    @Convert(converter = ContestQuestionsConverter.class)
    private ContestQuestions contestQuestions;

}
