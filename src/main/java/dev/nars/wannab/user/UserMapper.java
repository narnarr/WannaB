package dev.nars.wannab.user;

import dev.nars.wannab.domain.User;
import dev.nars.wannab.user.dto.PatchUserReqDto;
import org.mapstruct.*;

@Mapper
public interface UserMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUserFromDto(PatchUserReqDto dto, @MappingTarget User user);

    //toDto
}
