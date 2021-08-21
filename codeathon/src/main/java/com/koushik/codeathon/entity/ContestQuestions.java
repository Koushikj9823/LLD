package com.koushik.codeathon.entity;

import lombok.*;

import java.util.LinkedList;
import java.util.List;

@Builder
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContestQuestions {
   private List<Long> questions;
}
