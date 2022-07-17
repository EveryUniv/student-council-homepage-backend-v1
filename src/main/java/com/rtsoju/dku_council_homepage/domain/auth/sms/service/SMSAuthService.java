package com.rtsoju.dku_council_homepage.domain.auth.sms.service;

import com.rtsoju.dku_council_homepage.common.Messages;
import com.rtsoju.dku_council_homepage.common.jwt.JwtProvider;
import com.rtsoju.dku_council_homepage.common.nhn.service.SMSService;
import com.rtsoju.dku_council_homepage.domain.auth.sms.dto.SMSAuthToken;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.regex.Pattern;

@RequiredArgsConstructor
@Service
public class SMSAuthService {
    private static final Random RANDOM = new Random();
    private static final Pattern PHONE_PATTERN = Pattern.compile("^\\d{11}$");
    private final JwtProvider jwtProvider;
    private final SMSService smsService;

    @Value("${auth.sms.digitCount}")
    private int digitCount;


    public SMSAuthToken sendSMSCode(String phone) {
        if (!PHONE_PATTERN.matcher(phone).matches()) {
            throw new IllegalArgumentException(Messages.ERROR_INCORRECT_PHONE.getMessage());
        }

        String code = generateDigitCode(digitCount);
        String token = jwtProvider.createSMSAuthToken(phone, code);
        smsService.sendSMS(phone, Messages.AUTH_SMS_BODY.getFormatMessage(code));
        return new SMSAuthToken(token);
    }

    public void verifyCode(String token, String code) {
        if (!jwtProvider.validateSMSAuthToken(token, code)) {
            throw new IllegalArgumentException(Messages.ERROR_INCORRECT_SMS_CODE.getMessage());
        }
    }

    public static String generateDigitCode(int digitCount) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < digitCount; i++) {
            sb.append(RANDOM.nextInt(10));
        }
        return sb.toString();
    }
}
