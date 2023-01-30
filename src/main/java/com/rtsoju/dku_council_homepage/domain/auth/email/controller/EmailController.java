package com.rtsoju.dku_council_homepage.domain.auth.email.controller;

import com.rtsoju.dku_council_homepage.common.Messages;
import com.rtsoju.dku_council_homepage.common.ResponseResult;
import com.rtsoju.dku_council_homepage.common.SuccessResponseResult;
import com.rtsoju.dku_council_homepage.domain.auth.email.dto.request.RequestEmailDto;
import com.rtsoju.dku_council_homepage.domain.auth.email.dto.request.RequestEmailValidationDto;
import com.rtsoju.dku_council_homepage.domain.auth.email.service.EmailSerivce;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class EmailController {
//    private final GmailService gmailService;/
    private final EmailSerivce emailSerivce;

    @PostMapping("/email")
    public SuccessResponseResult emailSendForSignUp(@RequestBody RequestEmailDto dto) throws IOException {
        emailSerivce.sendEmailForSignUp(dto);
        log.info("정상적으로 메일을 보냈습니다. 학번 : " + dto.getClassId());
        return new SuccessResponseResult(Messages.SUCCESS_EMAIL_SEND.getMessage());
    }

    @PostMapping("/email/password")
    public SuccessResponseResult emailSendForChangePW(@RequestBody RequestEmailDto dto) throws IOException {
//        gmailService.sendEmailForChangePW(dto);
        emailSerivce.sendEmailForChangePW(dto);
        return new SuccessResponseResult(Messages.SUCCESS_EMAIL_SEND.getMessage());
    }

    @GetMapping("/email/validate")
    public ResponseResult validateToken(@RequestParam String token) {
        return new SuccessResponseResult(emailSerivce.validateToken(token));
    }

    @PostMapping("/m/email")
    public SuccessResponseResult emailSendForMobileSignUp(@RequestBody RequestEmailDto dto) throws IOException {
        emailSerivce.sendEmailForMobileSignUp(dto);
        return new SuccessResponseResult(Messages.SUCCESS_EMAIL_SEND.getMessage());
    }

    @PostMapping("/m/email/validate")
    public SuccessResponseResult validateCode(@RequestBody RequestEmailValidationDto dto){
        String emailToken = emailSerivce.validateEmailCode(dto);
        return new SuccessResponseResult(Messages.SUCCESS_SMS_AUTH.getMessage(), emailToken);
    }
}
