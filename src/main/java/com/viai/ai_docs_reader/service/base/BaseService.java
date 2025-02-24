package com.viai.ai_docs_reader.service.base;

import java.util.List;
import java.util.Optional;

public interface BaseService<T, ID> {
    List<T> getAll();
    Optional<T> getById(ID id);
    T save(T entity);
    void delete(ID id);
}
