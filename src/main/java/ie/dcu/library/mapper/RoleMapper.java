package ie.dcu.library.mapper;
import ie.dcu.library.entity.RoleEntity;
import ie.dcu.library.entity.MemberEntity;
import ie.dcu.library.model.Role;
import ie.dcu.library.model.Members;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface RoleMapper {

    List<Role>mapList(List<RoleEntity> entities);
    Role entityToDto(RoleEntity entity);

    RoleEntity toEntity(Role user);
}
