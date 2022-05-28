package dev.nars.wannab.user;

import dev.nars.wannab.domain.User;
import dev.nars.wannab.user.dto.*;
import dev.nars.wannab.util.CustomException;
import dev.nars.wannab.util.CustomResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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
    public CustomResponse<Map<String, Object>> userAdd(@RequestBody PostUserReqDto postUserReqDto) {
        try {
            User user = postUserReqDto.toEntity();
            userService.addUser(user);
            return new CustomResponse<>(GetUserResDto.from(user));
        } catch (CustomException exception) {
            return new CustomResponse<>(exception.getResponseStatus());
        }
    }

    /**
     * 로그인 API
     */
    @ResponseBody
    @PostMapping("/login")
    public CustomResponse<Map<String, Object>> logIn(@RequestBody PostLoginReqDto postLoginReqDto) {
        try {
            Map<String, Object> loginInfo = userService.login(postLoginReqDto.getEmail(),postLoginReqDto.getPassword());
            User user = (User) loginInfo.get("user");
            return new CustomResponse<>(GetUserResDto.jwtFrom(user, (String)loginInfo.get("jwt")));
        } catch (CustomException exception) {
            return new CustomResponse<>(exception.getResponseStatus());
        }
    }

    /**
     * 회원 조회 API
     */
    @ResponseBody
    @GetMapping("/{userId}")
    public CustomResponse<Map<String, Object>> userDetails(@PathVariable("userId") Long userId) {
        try {
            User user = userService.findUser(userId);
            return new CustomResponse<>(GetUserResDto.from(user));
        } catch (CustomException exception) {
            return new CustomResponse<>(exception.getResponseStatus());
        }
    }

    /**
     * 회원정보 수정 API
     */
    @PatchMapping("/{userId}")
    public CustomResponse<Map<String, Object>> userModify(@PathVariable("userId") Long userId, @RequestBody PatchUserReqDto patchUserReqDto) {
        try {
            User user = userService.modifyUser(userId, patchUserReqDto.getOldPassword(), patchUserReqDto.toEntity());
            return new CustomResponse<>(GetUserResDto.from(user));
        } catch (CustomException exception) {
            return new CustomResponse<>(exception.getResponseStatus());
        }
    }

    /**
     * 회원 탈퇴 API
     */
    @PatchMapping("/delete/{userId}")
    public CustomResponse<DeleteUserResDto> userRemove(@PathVariable("userId") Long userId) {
        try {
            User user = userService.removeUser(userId);
            return new CustomResponse<>(DeleteUserResDto.from(user));

        } catch (CustomException exception) {
            return new CustomResponse<>(exception.getResponseStatus());
        }
    }



    /**
     * 아이디 찾기
     */

    /**
     * 비밀번호 찾기
     */
}
