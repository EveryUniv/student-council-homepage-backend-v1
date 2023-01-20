package com.rtsoju.dku_council_homepage.domain.user.controller;

import com.rtsoju.dku_council_homepage.common.SuccessResponseResult;
import com.rtsoju.dku_council_homepage.common.jwt.JwtProvider;
import com.rtsoju.dku_council_homepage.domain.base.Major;
import com.rtsoju.dku_council_homepage.domain.user.model.dto.request.RequestChangePWDto;
import com.rtsoju.dku_council_homepage.domain.user.model.dto.request.RequestLoginDto;
import com.rtsoju.dku_council_homepage.domain.user.model.dto.request.RequestReissueDto;
import com.rtsoju.dku_council_homepage.domain.user.model.dto.request.RequestSignupDto;
import com.rtsoju.dku_council_homepage.domain.user.model.dto.response.MajorListResponse;
import com.rtsoju.dku_council_homepage.domain.user.model.entity.User;
import com.rtsoju.dku_council_homepage.domain.user.model.dto.response.RoleAndTokenResponseDto;
import com.rtsoju.dku_council_homepage.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;
    private final JwtProvider jwtProvider;

    @GetMapping
    public ResponseEntity<SuccessResponseResult> healthCheck() {
        return ResponseEntity.ok(new SuccessResponseResult("health checking success"));
    }

    @PostMapping("/users")
    public ResponseEntity<SuccessResponseResult> signup(@RequestBody RequestSignupDto dto, HttpServletRequest request) {
        String token = request.getHeader("EMAIL-VALIDATION-TOKEN");
        Long result = userService.signup(dto, token);
        log.info("유저가 회원가입을 하였습니다. 학번: " + dto.getClassId());
        return ResponseEntity.ok().body(new SuccessResponseResult("Sign up Success"));
    }

    @PostMapping("/m/users")
    public ResponseEntity<SuccessResponseResult> singUpForMobile(@RequestBody RequestSignupDto dto, HttpServletRequest request) {
        String token = request.getHeader("EMAIL-VALIDATION-TOKEN");
        Long result = userService.signup(dto, token);
        log.info("유저가 회원가입을 하였습니다. 학번: " + dto.getClassId());
        return ResponseEntity.ok().body(new SuccessResponseResult("Sign up Success"));
    }


    @PostMapping("/users/login")
    public ResponseEntity<SuccessResponseResult> login(@RequestBody RequestLoginDto dto) {
/*
        HttpHeaders headers = new HttpHeaders();
        headers.add("test", "1234");
*/
        RoleAndTokenResponseDto roleAndTokenResponseDto = userService.login(dto);
        SuccessResponseResult result = new SuccessResponseResult("Login Success", roleAndTokenResponseDto);
        return new ResponseEntity<>(result, HttpStatus.valueOf(200));
//        return ResponseEntity.ok()
//                .body(new RequestResult("Login Success", loginResponseDto)).
    }

//    @PostMapping("/users/reissue")
//    public ResponseEntity<RequestResult> tokenReissue(@RequestBody RequestReissueDto dto) {
//        userService.tokenReissue(dto);
//    }


    @PostMapping("/users/reissue")
    public ResponseEntity<SuccessResponseResult> reissue(@RequestBody RequestReissueDto dto) {
        RoleAndTokenResponseDto roleAndTokenResponseDto = userService.tokenReissue(dto);
        return ResponseEntity.ok()
                .body(new SuccessResponseResult("Reissue Success", roleAndTokenResponseDto));
    }

//    @PostMapping("/users/upgrade")
//    public ResponseEntity<SuccessResponseResult> addRoleToAdmin(HttpServletRequest request) {
//        String token = jwtProvider.getTokenInHttpServletRequest(request);
//        Long userId = Long.parseLong(jwtProvider.getUserId(token));
//        User user = userService.addRoleAdmin(userId);
//
//        return ResponseEntity.ok()
//                .body(new SuccessResponseResult(user.getClassId() + "유저는 ADMIN 권한을 가지게 되었습니다"));
//    }

    @PatchMapping("/users/password")
    public ResponseEntity<SuccessResponseResult> changePW(@RequestBody @Valid RequestChangePWDto request, HttpServletRequest header) {
        String token = header.getHeader("EMAIL-VALIDATION-TOKEN");
        userService.changePW(request, token);
        return ResponseEntity.ok().body(new SuccessResponseResult("성공~!~!"));
    }

    @GetMapping("/users/major")
    public ResponseEntity<SuccessResponseResult> majorList() {
        return ResponseEntity.ok(new SuccessResponseResult(new MajorListResponse()));
    }
}
