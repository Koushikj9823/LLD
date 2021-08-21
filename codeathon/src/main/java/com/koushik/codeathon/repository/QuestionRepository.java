package com.koushik.codeathon.repository;

import com.koushik.codeathon.entity.Question;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface QuestionRepository extends BaseRepository<Question> {

    Optional<Question> findByName(String questionName);

    @Query(value = "select * from questions where level=:level",nativeQuery = true)
    List<Long> findAllQuestionsByLevel(String level);
}
