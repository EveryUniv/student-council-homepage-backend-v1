package com.rtsoju.dku_council_homepage.common;

public enum Messages {
    ERROR_INCORRECT_PHONE("정확한 휴대폰 번호를 입력해주세요."),
    ERROR_SMS_AUTH_EXPIRED("SMS 인증 유효시간이 지났습니다. 인증을 다시 시작해주세요."),
    ERROR_INCORRECT_SMS_CODE("올바른 코드를 입력하세요."),
    ERROR_SMS_SEND("SMS를 전송하지 못했습니다."),
    AUTH_SMS_BODY("단국대학교 총학생회 사이트 회원가입 인증번호는 [%s]입니다."),
    SUCCESS_SMS_AUTH("인증 성공"),

    SUCCESS_EMAIL_SEND("이메일 요청 성공");

    private final String message;

    Messages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String getFormatMessage(Object... args) {
        return String.format(message, args);
    }
}
