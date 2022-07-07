package com.rtsoju.dku_council_homepage.domain.user.service;

import com.rtsoju.dku_council_homepage.domain.user.repository.UserInfoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Slf4j
@Transactional
public class UserService {
    private final UserInfoRepository userInfoRepository;
}
