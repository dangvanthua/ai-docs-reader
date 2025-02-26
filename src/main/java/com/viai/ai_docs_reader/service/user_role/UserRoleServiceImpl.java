package com.viai.ai_docs_reader.service.user_role;

import com.viai.ai_docs_reader.model.UserRoleModel;
import com.viai.ai_docs_reader.repository.UserRoleRepository;
import com.viai.ai_docs_reader.service.base.BaseServiceImpl;

public class UserRoleServiceImpl extends BaseServiceImpl<UserRoleModel, Long, UserRoleRepository>
        implements UserRoleService {

    public UserRoleServiceImpl(UserRoleRepository repository) {
        super(repository);
    }
}
