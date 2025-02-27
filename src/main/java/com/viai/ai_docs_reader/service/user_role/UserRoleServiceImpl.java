package com.viai.ai_docs_reader.service.user_role;

import com.viai.ai_docs_reader.constant.RoleConstant;
import com.viai.ai_docs_reader.dto.request.UserRoleRequest;
import com.viai.ai_docs_reader.model.RoleModel;
import com.viai.ai_docs_reader.model.UserRoleId;
import com.viai.ai_docs_reader.model.UserRoleModel;
import com.viai.ai_docs_reader.repository.UserRoleRepository;
import com.viai.ai_docs_reader.service.base.BaseServiceImpl;
import com.viai.ai_docs_reader.service.role.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserRoleServiceImpl extends BaseServiceImpl<UserRoleModel, Long, UserRoleRepository>
        implements UserRoleService {

    private final RoleService roleService;

    public UserRoleServiceImpl(UserRoleRepository repository, RoleService roleService) {
        super(repository);
        this.roleService = roleService;
    }

    @Override
    @Transactional
    public void createUserRole(UserRoleRequest userRoleRequest) {
        RoleModel roleModel = roleService.getByRoleName(RoleConstant.USER.name());
        UserRoleId userRoleId = UserRoleId.builder()
                .userId(userRoleRequest.getUserId())
                .roleId(roleModel.getId())
                .build();
        UserRoleModel userRoleModel = UserRoleModel.builder()
                .id(userRoleId)
                .build();
        super.save(userRoleModel);
    }
}
