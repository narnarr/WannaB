package dev.nars.wannab.user.dto;

import dev.nars.wannab.check.Sex;
import dev.nars.wannab.domain.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.time.Period;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class GetUserResDto {
    private String email;
    private String nickname;
    private int age;
    private Sex sex;
    private String profileImg;

    public static GetUserResDto from(User user) {
        int age = Period.between(user.getBirthday(), LocalDate.now()).getYears();
        return new GetUserResDto(user.getEmail(), user.getNickname(), age, user.getSex(), user.getProfileImg());
    }
}
