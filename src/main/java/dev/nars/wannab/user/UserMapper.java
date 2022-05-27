package dev.nars.wannab.user;

import dev.nars.wannab.domain.User;
import dev.nars.wannab.user.dto.PatchUserReqDto;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper
public interface UserMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUserFromDto(PatchUserReqDto dto, @MappingTarget User user);

    //toDto
}
