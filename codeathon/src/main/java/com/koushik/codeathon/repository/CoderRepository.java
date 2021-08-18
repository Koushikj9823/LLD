package com.koushik.codeathon.repository;

import com.koushik.codeathon.entity.Coder;

import java.util.Optional;

public interface CoderRepository extends BaseRepository<Coder> {
    Optional<Coder>findByUsername (String username);
}
