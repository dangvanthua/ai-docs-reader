package com.viai.ai_docs_reader.service.role;

import com.viai.ai_docs_reader.constant.RoleConstant;
import com.viai.ai_docs_reader.dto.request.RoleRequest;
import com.viai.ai_docs_reader.exception.ErrorCode;
import com.viai.ai_docs_reader.exception.error.BusinessException;
import com.viai.ai_docs_reader.model.RoleModel;
import com.viai.ai_docs_reader.repository.RoleRepository;
import com.viai.ai_docs_reader.service.base.BaseServiceImpl;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoleServiceImpl extends BaseServiceImpl<RoleModel, Long, RoleRepository> implements RoleService {

    public RoleServiceImpl(RoleRepository repository) {
       super(repository);
    }

    @Override
    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public RoleModel createRole(RoleRequest roleRequest) {
        String roleName = RoleConstant.valueOf(roleRequest.getRoleName()).name();
        RoleModel roleModel = RoleModel.builder()
                .roleName(roleName.toUpperCase())
                .description(roleRequest.getDescription())
                .build();
        return super.save(roleModel);
    }

    @Override
    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
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

    @Override
    public RoleModel getByRoleName(String roleName) {
        return super.repository.findByRoleName(roleName)
                .orElseThrow(() -> new BusinessException(ErrorCode.ROLE_NOT_EXISTED));
    }
}
