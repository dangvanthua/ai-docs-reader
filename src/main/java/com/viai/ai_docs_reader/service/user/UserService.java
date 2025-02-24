package com.viai.ai_docs_reader.service.user;

import com.viai.ai_docs_reader.dto.request.UserRequest;
import com.viai.ai_docs_reader.model.UserModel;
import com.viai.ai_docs_reader.service.base.BaseService;

public interface UserService extends BaseService<UserModel, Long> {
    UserModel createUser(UserRequest userRequest);
}
