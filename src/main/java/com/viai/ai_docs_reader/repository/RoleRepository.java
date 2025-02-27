package com.viai.ai_docs_reader.repository;

import com.viai.ai_docs_reader.model.RoleModel;

import java.util.Optional;

public interface RoleRepository extends BaseRepository<RoleModel, Long> {
    Optional<RoleModel> findByRoleName(String roleName);
}
