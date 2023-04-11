package ie.dcu.library.mapper;
import ie.dcu.library.entity.MemberEntity;
import ie.dcu.library.model.Members;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface MemberMapper {

    List<Members> mapList(List<MemberEntity> entities);
    Members entityToDto(MemberEntity entity);

    MemberEntity toEntity(Members user);
}
