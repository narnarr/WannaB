package dev.nars.wannab.user.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class PostLoginResDto {
    private String jwt;

    public static PostLoginResDto from(String jwt) {
        return new PostLoginResDto(jwt);
    }
}
