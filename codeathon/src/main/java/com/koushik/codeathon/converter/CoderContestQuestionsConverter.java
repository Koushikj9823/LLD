package com.koushik.codeathon.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.koushik.codeathon.entity.CoderContestQuestions;
import lombok.SneakyThrows;

import javax.persistence.AttributeConverter;

public class CoderContestQuestionsConverter implements AttributeConverter<CoderContestQuestions,String> {
    private ObjectMapper objectMapper = new ObjectMapper();
    @SneakyThrows
    @Override
    public String convertToDatabaseColumn(CoderContestQuestions coderContestQuestions) {
        if(coderContestQuestions==null) return "";
        return objectMapper.writeValueAsString(coderContestQuestions);
    }

    @SneakyThrows
    @Override
    public CoderContestQuestions convertToEntityAttribute(String s) {
        if(s.equals("")) return new CoderContestQuestions();
        return objectMapper.readValue(s,CoderContestQuestions.class);
    }
}
