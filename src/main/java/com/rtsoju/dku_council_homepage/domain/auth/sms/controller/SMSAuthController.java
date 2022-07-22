package com.rtsoju.dku_council_homepage.domain.auth.sms.controller;

import com.rtsoju.dku_council_homepage.common.Messages;
import com.rtsoju.dku_council_homepage.common.SuccessResponseResult;
import com.rtsoju.dku_council_homepage.domain.auth.sms.dto.SMSAuthToken;
import com.rtsoju.dku_council_homepage.domain.auth.sms.dto.request.VerifyCodeRequest;
import com.rtsoju.dku_council_homepage.domain.auth.sms.service.SMSAuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/sms")
@RequiredArgsConstructor
public class SMSAuthController {
    private final SMSAuthService service;

    @GetMapping()
    public SMSAuthToken sendCode(@RequestParam String phone) {
        log.debug("Request sending SMS auth code: {}", phone);
        return service.sendSMSCode(phone);
    }

    @PostMapping()
    public SuccessResponseResult verifyCode(@RequestBody VerifyCodeRequest body) {
        log.debug("Let's verify {} -> {}", body.getToken(), body.getCode());
        service.verifyCode(body.getToken(), body.getCode());
        return new SuccessResponseResult(Messages.SUCCESS_SMS_AUTH.getMessage());
    }
}
