package com.rtsoju.dku_council_homepage.domain.auth.service;

import com.rtsoju.dku_council_homepage.domain.auth.model.SecurityUser;
import com.rtsoju.dku_council_homepage.domain.user.model.entity.User;
import com.rtsoju.dku_council_homepage.domain.user.repository.UserInfoRepository;
import com.rtsoju.dku_council_homepage.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CustomUserDetailService implements UserDetailsService {

    private final UserInfoRepository userInfoRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        Optional<User> optionalUser = userInfoRepository.findById(Long.parseLong(userId));
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return new SecurityUser(user);
        }
        return null;
    }
}
