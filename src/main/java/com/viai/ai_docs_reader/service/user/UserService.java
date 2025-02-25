package com.viai.ai_docs_reader.service.user;

import com.viai.ai_docs_reader.dto.request.UserRequest;
import com.viai.ai_docs_reader.model.UserModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    UserModel createUser(UserRequest userRequest);
    UserModel getUserById(Long userId);
    Page<UserModel> getAllUsers(Pageable pageable);
}
