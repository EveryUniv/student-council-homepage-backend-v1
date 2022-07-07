package com.rtsoju.dku_council_homepage.domain.user.controller;

import com.rtsoju.dku_council_homepage.common.Messages;
import com.rtsoju.dku_council_homepage.common.RequestResult;
import com.rtsoju.dku_council_homepage.domain.user.model.SMSAuthToken;
import com.rtsoju.dku_council_homepage.domain.user.model.request.VerifyCodeRequest;
import com.rtsoju.dku_council_homepage.domain.user.service.SMSAuthService;
import com.rtsoju.dku_council_homepage.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class UserController {
    private final SMSAuthService service;
    private final UserService userService;

    @ExceptionHandler(IllegalArgumentException.class)
    public RequestResult handleError(IllegalArgumentException e) {
        return new RequestResult(e);
    }

    @GetMapping("confirm-email")
    public String viewConfirmEmail(@Valid @RequestParam String token){
        userService.confirmEmail(token);
        return "redirect:/login";
    }

    @GetMapping("/code")
    public SMSAuthToken sendCode(@RequestParam String phone) {
        log.debug("Request sending SMS auth code: {}", phone);
        return service.sendSMSCode(phone);
    }

    @PostMapping("/code")
    public RequestResult verifyCode(@RequestBody VerifyCodeRequest body) {
        log.debug("Let's verify {} -> {}", body.getIdentifier(), body.getCode());
        service.verifyCode(body.getIdentifier(), body.getCode());
        return new RequestResult(Messages.SUCCESS_SMS_AUTH.getMessage());
    }
}
