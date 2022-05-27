package dev.nars.wannab.user.dto;

import dev.nars.wannab.check.Sex;
import dev.nars.wannab.domain.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostUserReqDto {
    private String email;
    private String nickname;
    private String password;
    //@DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;
    private Sex sex;

    public User toEntity() {
        return User.builder()
                .email(email)
                .password(password)
                .nickname(nickname)
                .birthday(birthday)
                .sex(sex).build();
    }
}
