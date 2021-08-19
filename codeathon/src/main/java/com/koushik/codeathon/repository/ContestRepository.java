package com.koushik.codeathon.repository;

import com.koushik.codeathon.entity.Contest;
import com.koushik.codeathon.entity.Question;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ContestRepository extends BaseRepository<Contest> {

    Optional<Contest> findByName(String contestName);

    @Query(value = "select * from contest where level=:level",nativeQuery = true)
    List<Contest> findAllContestByLevel(String level);
}
