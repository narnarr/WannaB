package dev.nars.wannab.user.dto;

import dev.nars.wannab.check.Sex;
import dev.nars.wannab.domain.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class PatchUserResDto {
    private String email;
    private String password;
    private String nickname;
    private LocalDate birthday;
    private Sex sex;

    public static PatchUserResDto from(User user) {
        return new PatchUserResDto(user.getEmail(), user.getPassword(), user.getNickname(), user.getBirthday(), user.getSex());
    }
}
