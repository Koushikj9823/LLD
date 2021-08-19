package com.koushik.codeathon.repository;

import com.koushik.codeathon.entity.Contest;

import java.util.Optional;

public interface ContestRepository extends BaseRepository<Contest> {

    Optional<Contest> findByName(String contestName);
}
