package com.viai.ai_docs_reader.service.token;

import com.viai.ai_docs_reader.model.UserModel;
import com.viai.ai_docs_reader.repository.UserRepository;
import com.viai.ai_docs_reader.service.base.BaseServiceImpl;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class TokenServiceImpl extends BaseServiceImpl<UserModel, Long, UserRepository> implements TokenService {

    private final PasswordEncoder passwordEncoder;

    public TokenServiceImpl(UserRepository repository, PasswordEncoder passwordEncoder) {
        super(repository);
        this.passwordEncoder = passwordEncoder;
    }


}
