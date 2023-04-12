package ie.dcu.library.mapper;
import ie.dcu.library.entity.MemberEntity;
import ie.dcu.library.model.Member;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface MemberMapper {

    List<Member> mapList(List<MemberEntity> entities);
    Member entityToDto(MemberEntity entity);

    MemberEntity toEntity(Member user);
}
