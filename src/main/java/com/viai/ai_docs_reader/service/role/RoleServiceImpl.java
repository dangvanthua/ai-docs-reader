package com.viai.ai_docs_reader.service.role;

import com.viai.ai_docs_reader.dto.request.RoleRequest;
import com.viai.ai_docs_reader.exception.ErrorCode;
import com.viai.ai_docs_reader.exception.error.BusinessException;
import com.viai.ai_docs_reader.model.RoleModel;
import com.viai.ai_docs_reader.repository.RoleRepository;
import com.viai.ai_docs_reader.service.base.BaseServiceImpl;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public class RoleServiceImpl extends BaseServiceImpl<RoleModel, Long, RoleRepository> implements RoleService {

    public RoleServiceImpl(RoleRepository repository) {
       super(repository);
    }

    @Override
    @Transactional
    public RoleModel createRole(RoleRequest roleRequest) {
        RoleModel roleModel = RoleModel.builder()
                .roleName(roleRequest.getRoleName())
                .description(roleRequest.getDescription())
                .build();
        return super.save(roleModel);
    }

    @Override
    public void deleteRole(Long roleId) {
        RoleModel roleModel = super.getById(roleId)
                .orElseThrow(() ->
                        new BusinessException(ErrorCode.ROLE_NOT_EXISTED));
        super.delete(roleModel.getId());
    }

    @Override
    public List<RoleModel> getAllRole() {
        return super.getAll();
    }
}
