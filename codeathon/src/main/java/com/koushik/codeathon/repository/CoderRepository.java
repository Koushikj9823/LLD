package com.koushik.codeathon.repository;

import com.koushik.codeathon.entity.Coder;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CoderRepository extends BaseRepository<Coder> {
    Optional<Coder>findByUsername (String username);

    @Query(value = "select * from coder order by score desc",nativeQuery = true)
    List<Coder> findAllQuestionsByLevel();
}
