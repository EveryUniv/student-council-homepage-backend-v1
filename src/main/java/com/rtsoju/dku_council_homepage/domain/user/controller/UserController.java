package com.rtsoju.dku_council_homepage.domain.user.controller;

import com.rtsoju.dku_council_homepage.common.RequestResult;
import com.rtsoju.dku_council_homepage.domain.user.model.dto.request.RequestLoginDto;
import com.rtsoju.dku_council_homepage.domain.user.model.dto.request.RequestReissueDto;
import com.rtsoju.dku_council_homepage.domain.user.model.dto.request.RequestSignupDto;
import com.rtsoju.dku_council_homepage.domain.user.model.dto.response.BothTokenResponseDto;

import com.rtsoju.dku_council_homepage.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/users")
    public RequestResult signup(@RequestBody RequestSignupDto dto, HttpServletRequest request) {
        String token = request.getHeader("EMAIL-VALIDATION-TOKEN");
        Long result = userService.signup(dto, token);
        return new RequestResult("Sign up Success");
    }


    @PostMapping("/users/login")
    public ResponseEntity<RequestResult> login(@RequestBody RequestLoginDto dto) {
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("test", "1234");
        BothTokenResponseDto bothTokenResponseDto = userService.login(dto);
        return new ResponseEntity<RequestResult>(new RequestResult("Login Success",bothTokenResponseDto), HttpStatus.valueOf(200));
//        return ResponseEntity.ok()
//                .body(new RequestResult("Login Success", loginResponseDto)).
    }

//    @PostMapping("/users/reissue")
//    public ResponseEntity<RequestResult> tokenReissue(@RequestBody RequestReissueDto dto) {
//        userService.tokenReissue(dto);
//    }

    @PostMapping("/users/reissue")
    public ResponseEntity<RequestResult> reissue(@RequestBody RequestReissueDto dto) {
        BothTokenResponseDto bothTokenResponseDto = userService.tokenReissue(dto);
        return ResponseEntity.ok()
                .body(new RequestResult("Reissue Success",bothTokenResponseDto));
    }

    @GetMapping("/test")
    public RequestResult test() {
        return new RequestResult("Test Success");
    }
}
