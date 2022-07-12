package com.rtsoju.dku_council_homepage.domain.auth.email.controller;

import com.rtsoju.dku_council_homepage.common.RequestResult;
import com.rtsoju.dku_council_homepage.domain.auth.email.dto.RequestEmailDto;
import com.rtsoju.dku_council_homepage.domain.auth.email.dto.request.EmailResponseDto;
import com.rtsoju.dku_council_homepage.domain.auth.email.service.GmailService;
import com.rtsoju.dku_council_homepage.domain.auth.sms.dto.SMSAuthToken;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class EmailController {
    private final GmailService gmailService;

    @PostMapping("/email")
    public RequestResult email(@RequestBody RequestEmailDto dto){
        gmailService.send(dto);
        return new RequestResult("Email send Success", new EmailResponseDto(dto.getClassId()));
    }
}
