package dev.nars.wannab.user;

import dev.nars.wannab.check.Status;
import dev.nars.wannab.domain.User;
import dev.nars.wannab.user.dto.PatchUserReqDto;
import dev.nars.wannab.util.CustomException;
import static dev.nars.wannab.util.CustomResponseStatus.*;
import static dev.nars.wannab.util.ValidationRegex.*;

import dev.nars.wannab.util.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final JwtService jwtService;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, UserMapper userMapper, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
        this.jwtService = jwtService;
    }

    /**
     * 회원가입
     */
    public Long join(User user) throws CustomException {
        // 유효성 검사 - 이메일 형식 확인
        if(!isRegexEmail(user.getEmail())) {
            throw new CustomException(INVALID_EMAIL);
        }

        // 유효성 검사 - 이메일 중복 확인
        Optional<User> u = userRepository.findByEmail(user.getEmail());
        if(u.isPresent()) {
            if(u.get().getStatusCk().equals("DELETED")) {
                throw new CustomException(DELETED_EMAIL);
           }
            throw new CustomException(EXISTING_EMAIL);
        }

        // 유효성 검사 - 비밀번호 형식 확인(영문+숫자+특수기호+8자이상)
        if(!isRegexPassword(user.getPassword())) {
            throw new CustomException(INVALID_PASSWORD);
        }

        // 비밀번호 암호화
        String encodePassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodePassword);

        userRepository.save(user);

        return user.getId();
    }

    /**
     * 로그인
     */
    public String login(String email, String password) throws CustomException {
        Optional<User> user = userRepository.findByEmail(email);

        // 가입한 적 없는 유저
        if(!user.isPresent()) {
            throw new CustomException(USER_NOT_FOUND);
        }

        User findUser = user.get();

        // 탈퇴한 유저
        if(findUser.getStatusCk().equals(Status.DELETED)) {
            throw new CustomException(USER_NOT_FOUND);
        }

        // 비밀번호 복호화
        if(passwordEncoder.matches(password, findUser.getPassword())) {
            return jwtService.createJwt(findUser.getId());
        } else {
            throw new CustomException(INCORRECT_PASSWORD);
        }
    }

    /**
     * 회원 조회
     */
    public User findOne(Long userId) throws CustomException {
        Optional<User> findUser = userRepository.findByUserId(userId);

        // 잘못된 userId
        if(!findUser.isPresent()) {
            throw new CustomException(USER_NOT_FOUND);
        }

        return findUser.get();
    }

    public User updateInfo(Long userId, String oldPassword, User user) throws CustomException {
        // 유저 확인
        Long userIdByJwt = jwtService.getUserId();
        if(userId != userIdByJwt) {
            throw new CustomException(INVALID_USER_JWT);
        }

        User findUser = userRepository.findByUserId(userIdByJwt).get();

        // 기존 비밀번호 확인
        if(!passwordEncoder.matches(oldPassword, findUser.getPassword())) {
            throw new CustomException(INCORRECT_PASSWORD);
        }

        // 비밀번호 암호화
        String encodePassword = passwordEncoder.encode(user.getPassword());

        findUser.setNickname(user.getNickname());
        findUser.setPassword(encodePassword);
        findUser.setBirthday(user.getBirthday());
        findUser.setSex(user.getSex());

        return userRepository.save(findUser);
    }

    /**
     * [Deprecated] 회원 정보 수정
     * 그저 Mapper를 이용해보고 싶어서 만들어 본 메소드. 비밀번호 암호화가 되지 않으니 사용하면 안된다.
     */
    @Deprecated(forRemoval = true)
    public User updateInfoByMapper(Long userId, PatchUserReqDto dto) throws CustomException {
        Long userIdByJwt = jwtService.getUserId();
        if(userId != userIdByJwt) {
            throw new CustomException(INVALID_USER_JWT);
        }

        User findUser = userRepository.findByUserId(userIdByJwt).get();

        userMapper.updateUserFromDto(dto, findUser);
        return userRepository.save(findUser);
    }
}
