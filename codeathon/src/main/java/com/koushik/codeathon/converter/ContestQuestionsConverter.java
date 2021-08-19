package com.koushik.codeathon.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.koushik.codeathon.entity.ContestQuestions;
import lombok.SneakyThrows;

import javax.persistence.AttributeConverter;

public class ContestQuestionsConverter implements AttributeConverter<ContestQuestions,String> {
    private ObjectMapper objectMapper = new ObjectMapper();
    @SneakyThrows
    @Override
    public String convertToDatabaseColumn(ContestQuestions contestQuestions) {
        if(contestQuestions==null) return "";
        return objectMapper.writeValueAsString(contestQuestions);
    }

    @SneakyThrows
    @Override
    public ContestQuestions convertToEntityAttribute(String s) {
        if(s.equals("")) return new ContestQuestions();
        return objectMapper.readValue(s,ContestQuestions.class);
    }
}
