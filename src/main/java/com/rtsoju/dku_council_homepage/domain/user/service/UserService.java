package com.rtsoju.dku_council_homepage.domain.user.service;

import com.rtsoju.dku_council_homepage.domain.token.entity.ConfirmationToken;
import com.rtsoju.dku_council_homepage.domain.token.service.ConfirmationTokenService;
import com.rtsoju.dku_council_homepage.domain.user.entity.User;
import com.rtsoju.dku_council_homepage.domain.user.repository.UserInfoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
@Transactional
public class UserService {
    private final UserInfoRepository userInfoRepository;
    private final ConfirmationTokenService confirmationTokenService;

    public void confirmEmail(String token){
        ConfirmationToken findConfirmationToken = confirmationTokenService.findByIdAndExpirationDateAfterAndExpired(token);
        //orelse로 바꿔야함.
        User findUser = userInfoRepository.findByClassId(findConfirmationToken.getClassId()).get();
        findConfirmationToken.useToken();
        findUser.emailVerifiedSuccess();
    }

    public Optional<User> findById(Long userId) {
        return userInfoRepository.findById(userId);
    }
}
