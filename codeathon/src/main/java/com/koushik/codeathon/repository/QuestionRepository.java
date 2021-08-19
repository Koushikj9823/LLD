package com.koushik.codeathon.repository;

import com.koushik.codeathon.entity.Question;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuestionRepository extends BaseRepository<Question> {
    @Query(value = "select * from questions where level=:level",nativeQuery = true)
    List<Question> findAllQuestionsByLevel(String level);
}
