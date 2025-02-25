package com.viai.ai_docs_reader.service.base;

import com.viai.ai_docs_reader.repository.BaseRepository;

import java.util.List;
import java.util.Optional;

public abstract class BaseServiceImpl<T, ID, R extends BaseRepository<T, ID>> implements BaseService<T,ID>{

    protected final R repository;

    public BaseServiceImpl(R repository) {
        this.repository = repository;
    }

    @Override
    public List<T> getAll() {
        return repository.findAll();
    }

    @Override
    public Optional<T> getById(ID id) {
        return repository.findById(id);
    }

    @Override
    public T save(T entity) {
        return repository.save(entity);
    }

    @Override
    public void delete(ID id) {
        repository.deleteById(id);
    }
}
