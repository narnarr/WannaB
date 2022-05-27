package dev.nars.wannab.user;

import dev.nars.wannab.domain.User;
import dev.nars.wannab.user.dto.*;
import dev.nars.wannab.util.CustomException;
import dev.nars.wannab.util.CustomResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 회원가입 API
     */
    @ResponseBody
    @PostMapping("")
    public CustomResponse<PostUserResDto> create(@RequestBody PostUserReqDto postUserReqDto) {
        try {
            User user = postUserReqDto.toEntity();
            userService.join(user);
            return new CustomResponse<>(PostUserResDto.from(user));
        } catch (CustomException exception) {
            return new CustomResponse<>(exception.getResponseStatus());
        }
    }

    /**
     * 로그인 API
     */
    @ResponseBody
    @PostMapping("/login")
    public CustomResponse<PostLoginResDto> logIn(@RequestBody PostLoginReqDto postLoginReqDto) {
        try {
            String token = userService.login(postLoginReqDto.getEmail(),postLoginReqDto.getPassword());
            return new CustomResponse<>(PostLoginResDto.from(token));
        } catch (CustomException exception) {
            return new CustomResponse<>(exception.getResponseStatus());
        }
    }

    /**
     * 회원 조회 API
     */
    @ResponseBody
    @GetMapping("/{userId}")
    public CustomResponse<GetUserResDto> getUser(@PathVariable("userId") Long userId) {
        try {
            User user = userService.findOne(userId);
            return new CustomResponse<>(GetUserResDto.from(user));
        } catch (CustomException exception) {
            return new CustomResponse<>(exception.getResponseStatus());
        }
    }

    /**
     * 회원정보 수정 API
     */
    @PatchMapping("/{userId}")
    public CustomResponse<PatchUserResDto> updateInfo2(@PathVariable("userId") Long userId, @RequestBody PatchUserReqDto patchUserReqDto) {
        try {
            User user = userService.updateInfo(userId, patchUserReqDto.getOldPassword(), patchUserReqDto.toEntity());
            return new CustomResponse<>(PatchUserResDto.from(user));
        } catch (CustomException exception) {
            return new CustomResponse<>(exception.getResponseStatus());
        }
    }

    /**
     * 회원 탈퇴 API
     */
}
