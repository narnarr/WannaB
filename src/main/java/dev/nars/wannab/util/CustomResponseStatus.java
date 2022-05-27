package dev.nars.wannab.util;

import lombok.Getter;

@Getter
public enum CustomResponseStatus {
    //code 값은 Http Status Code 값을 따른다.
    /**
     * 200 : Success
     */
    SUCCESS(true, 200, "요청에 성공하였습니다."),

    /**
     * 400 : Client Errors (유효하지 않은 요청)
     */
    // 이메일
    EMPTY_EMAIL(false, 400, "이메일을 입력해주세요"),
    INVALID_EMAIL(false, 400, "이메일 형식을 확인해주세요."),
    EXISTING_EMAIL(false, 400, "이미 사용 중인 이메일입니다."),
    DELETED_EMAIL(false, 400, "탈퇴한 아이디는 재가입 및 복구할 수 없습니다."),
    // 비밀번호
    INVALID_PASSWORD(false, 400, "비밀번호는 문자, 숫자, 특수기호를 포함한 8자리 이상이어야 합니다."),
    // jwt
    EMPTY_JWT(false, 400, "JWT를 입력해주세요."),
    INVALID_JWT(false, 400, "유효하지 않은 JWT입니다."),
    INVALID_USER_JWT(false, 400, "권한이 없는 유저의 접근입니다."),
    // 로그인
    USER_NOT_FOUND(false, 400, "존재하지 않는 유저입니다."),
    INCORRECT_PASSWORD(false, 400, "잘못된 비밀번호입니다.");


    private final boolean isSuccess;
    private final int code;
    private final String message;

    private CustomResponseStatus(boolean isSuccess, int code, String message) {
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
    }
}
