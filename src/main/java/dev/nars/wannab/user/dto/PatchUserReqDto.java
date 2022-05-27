package dev.nars.wannab.user.dto;

import dev.nars.wannab.check.Sex;
import dev.nars.wannab.domain.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PatchUserReqDto {
    // email은 프론트에서 입력창 자체가 막혀있다. 즉, 기존 email 값을 함께 보내주는 것.
    private String email;
    private String nickname;
    private String oldPassword;
    private String newPassword;
    private LocalDate birthday;
    private Sex sex;

    public User toEntity() {
        return User.builder()
                .email(email)
                .nickname(nickname)
                .password(newPassword)
                .birthday(birthday)
                .sex(sex)
                .build();
    }
}
