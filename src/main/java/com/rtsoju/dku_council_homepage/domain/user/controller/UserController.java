package com.rtsoju.dku_council_homepage.domain.user.controller;

import com.rtsoju.dku_council_homepage.common.RequestResult;
import com.rtsoju.dku_council_homepage.domain.user.model.dto.RequestLoginDto;
import com.rtsoju.dku_council_homepage.domain.user.model.dto.RequestSignupDto;
import com.rtsoju.dku_council_homepage.domain.user.model.dto.response.LoginResponseDto;
import com.rtsoju.dku_council_homepage.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/users")
    public RequestResult signup(@RequestBody RequestSignupDto dto) {
        Long result = userService.signup(dto);
        return new RequestResult("Sign up Success");
    }

    @PostMapping("/users/login")
    public RequestResult login(@RequestBody RequestLoginDto dto) {
        LoginResponseDto loginResponseDto = new LoginResponseDto();
        loginResponseDto.setAccessToken(userService.login(dto));
        return new RequestResult("Login success", loginResponseDto);
    }

    @GetMapping("/test")
    public RequestResult test(){
        return new RequestResult("Test Success");
    }
}
