package com.koushik.codeathon.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.LinkedList;
import java.util.List;

@Builder
@ToString
@Getter
@Setter
public class ContestQuestions {
   private List<Long> questions;
}
