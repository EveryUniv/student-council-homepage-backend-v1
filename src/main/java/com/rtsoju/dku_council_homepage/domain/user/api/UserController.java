package com.rtsoju.dku_council_homepage.domain.user.api;

import com.rtsoju.dku_council_homepage.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @GetMapping("confirm-email")
    public String viewConfirmEmail(@Valid @RequestParam String token){
        userService.confirmEmail(token);

        return "redirect:/login";
    }
}
