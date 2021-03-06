package dev.nars.wannab.util;

import lombok.Getter;

@Getter
public enum CustomResponseStatus {
    /**
     * 2000 : Success
     */
    SUCCESS(true, 2000, "요청에 성공하였습니다."),

    /**
     * 4000 : Client Errors (유효하지 않은 요청)
     */
    // 회원 - 이메일
    EMPTY_EMAIL(false, 4001, "이메일을 입력해주세요"),
    INVALID_EMAIL(false, 4002, "이메일 형식을 확인해주세요."),
    EXISTING_EMAIL(false, 4003, "이미 사용 중인 이메일입니다."),
    DELETED_EMAIL(false, 4004, "탈퇴한 아이디는 재가입 및 복구할 수 없습니다."),
    // 회원 - 비밀번호
    INVALID_PASSWORD(false, 4005, "비밀번호는 문자, 숫자, 특수기호를 포함한 8자리 이상이어야 합니다."),
    // 회원 - jwt
    EMPTY_JWT(false, 4006, "JWT를 입력해주세요."),
    INVALID_JWT(false, 4007, "유효하지 않은 JWT입니다."),
    INVALID_USER_JWT(false, 4008, "권한이 없는 유저의 접근입니다."),
    // 회원 - 로그인
    USER_NOT_FOUND(false, 4009, "존재하지 않는 유저입니다."),
    INCORRECT_PASSWORD(false, 4010, "잘못된 비밀번호입니다."),
    // 클래스
    COURSE_NOT_FOUND(false, 4011, "존재하지 않는 클래스입니다."),
    /**
     * 5000 : Server Errors
     */
    // DB 오류
    DATABASE_ERROR(false, 5001, "데이터베이스 연결에 실패했습니다.");


    private final boolean isSuccess;
    private final int code;
    private final String message;

    CustomResponseStatus(boolean isSuccess, int code, String message) {
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
    }
}
