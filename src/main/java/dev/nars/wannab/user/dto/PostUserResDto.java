package dev.nars.wannab.user.dto;

import dev.nars.wannab.domain.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class PostUserResDto {
    Long id;

    public static PostUserResDto from(User user) {
        return new PostUserResDto(user.getId());
    }
}
