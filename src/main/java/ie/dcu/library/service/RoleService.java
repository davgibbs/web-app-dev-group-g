package ie.dcu.library.service;
import ie.dcu.library.entity.RoleEntity;
import ie.dcu.library.mapper.RoleMapper;
import ie.dcu.library.model.ErrorCode;
import ie.dcu.library.model.Role;
import ie.dcu.library.model.RoleName;
import ie.dcu.library.repository.RoleEntityRepository;
import ie.dcu.library.util.LibraryServiceException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    private final RoleEntityRepository roleEntityRepository;
    private final RoleMapper mapper;

    public RoleService(RoleEntityRepository roleEntityRepository, RoleMapper mapper) {
        this.roleEntityRepository = roleEntityRepository;
        this.mapper = mapper;
    }

    public RoleEntity findRoleByRolename(RoleName roleName){
        return  roleEntityRepository.findRoleEntityByRolename(roleName)
                .orElseThrow(() -> new LibraryServiceException(
                        String.format("Role %s does not exist", roleName.name()), ErrorCode.ROLE_NOT_FOUND)
                );

    }
}
