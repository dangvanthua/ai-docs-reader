package com.viai.ai_docs_reader.service.user;

import com.viai.ai_docs_reader.dto.request.UserRequest;
import com.viai.ai_docs_reader.exception.ErrorCode;
import com.viai.ai_docs_reader.exception.error.BusinessException;
import com.viai.ai_docs_reader.model.UserModel;
import com.viai.ai_docs_reader.repository.UserRepository;
import com.viai.ai_docs_reader.service.base.BaseServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl extends BaseServiceImpl<UserModel, Long, UserRepository> implements UserService{

    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository repository, PasswordEncoder passwordEncoder) {
        super(repository);
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public UserModel createUser(UserRequest userRequest) {
        Optional<UserModel> userModelOptional = super.repository.findByEmail(userRequest.getEmail());
        if(userModelOptional.isPresent()) {
            throw new BusinessException(ErrorCode.EMAIL_EXISTS, userRequest.getEmail());
        }
        String passwordHashed = passwordEncoder.encode(userRequest.getPassword());
        UserModel userModel = UserModel.builder()
                .username(userRequest.getUsername())
                .email(userRequest.getEmail())
                .passwordHash(passwordHashed)
                .timezone(userRequest.getTimezone())
                .build();
        return super.save(userModel);
    }

    @Override
    public UserModel getUserById(Long userId) {
        return super.getById(userId)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_EXISTED));
    }

    @Override
    public Page<UserModel> getAllUsers(Pageable pageable) {
        return super.repository.findAll(pageable);
    }


}