package com.viai.ai_docs_reader.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BaseRepository<T, ID> extends JpaRepository<T, ID> {
}
