package com.viai.ai_docs_reader.repository;

import com.viai.ai_docs_reader.model.UserModel;

import java.util.Optional;

public interface UserRepository extends BaseRepository<UserModel, Long> {
    Optional<UserModel> findByEmail(String email);
}
