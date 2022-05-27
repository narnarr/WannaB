package dev.nars.wannab.user.dto;

import dev.nars.wannab.check.Sex;
import dev.nars.wannab.domain.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.HashMap;
import java.util.Map;

@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GetUserResDto {
    private Long userId;
    private String email;
    private String nickname;
    private LocalDate birthday;
    private int age;
    private Sex sex;
    private String profileImg;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static Map<String,Object> from(User user) {
        Map<String,Object> map = new HashMap<>();
        map.put("userInfo", setDto(user));

        return map;
    }

    public static Map<String,Object> jwtFrom(User user, String jwt) {
        Map<String,Object> map = new HashMap<>();
        map.put("userInfo", setDto(user));
        map.put("jwt", jwt);

        return map;
    }

    public static GetUserResDto setDto(User user) {
        int age = Period.between(user.getBirthday(), LocalDate.now()).getYears();

        GetUserResDto dto = new GetUserResDto();
        dto.setUserId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setNickname(user.getNickname());
        dto.setBirthday(user.getBirthday());
        dto.setAge(age);
        dto.setSex(user.getSex());
        dto.setProfileImg(user.getProfileImg());
        dto.setCreatedAt(user.getCreatedAt());
        dto.setUpdatedAt(user.getUpdatedAt());

        return dto;
    }
}
