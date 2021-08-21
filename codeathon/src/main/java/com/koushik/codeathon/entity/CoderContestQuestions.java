package com.koushik.codeathon.entity;

import com.koushik.codeathon.constants.AnswerStatus;
import lombok.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ToString
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CoderContestQuestions {
    Map<String, HashMap<Long, AnswerStatus>> contestToQuestionsMap = new HashMap<>();
}
