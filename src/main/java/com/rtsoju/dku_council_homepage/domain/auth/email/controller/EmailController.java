package com.rtsoju.dku_council_homepage.domain.auth.email.controller;

import com.rtsoju.dku_council_homepage.common.Messages;
import com.rtsoju.dku_council_homepage.common.SuccessResponseResult;
import com.rtsoju.dku_council_homepage.domain.auth.email.dto.RequestEmailDto;
import com.rtsoju.dku_council_homepage.domain.auth.email.dto.request.EmailResponseDto;
import com.rtsoju.dku_council_homepage.domain.auth.email.service.EmailSerivce;
//import com.rtsoju.dku_council_homepage.domain.auth.email.service.GmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import java.io.IOException;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class EmailController {
//    private final GmailService gmailService;/
    private final EmailSerivce emailSerivce;

    @PostMapping("/email")
    public SuccessResponseResult emailSendForSignUp(@RequestBody RequestEmailDto dto) throws MessagingException, IOException {
//        gmailService.sendEmailForSignUp(dto);
        emailSerivce.sendEmailForSignUp(dto);
        log.info("정상적으로 메일을 보냈습니다. 학번 : " + dto.getClassId());
        return new SuccessResponseResult(Messages.SUCCESS_EMAIL_SEND.getMessage());
    }

    @PostMapping("/email/password")
    public SuccessResponseResult emailSendForChangePW(@RequestBody RequestEmailDto dto) throws MessagingException, IOException {
//        gmailService.sendEmailForChangePW(dto);
        emailSerivce.sendEmailForChangePW(dto);
        return new SuccessResponseResult(Messages.SUCCESS_EMAIL_SEND.getMessage());
    }
}
