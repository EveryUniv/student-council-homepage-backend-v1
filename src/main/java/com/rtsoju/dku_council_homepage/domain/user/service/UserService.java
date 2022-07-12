package com.rtsoju.dku_council_homepage.domain.user.service;

import com.rtsoju.dku_council_homepage.common.jwt.JwtProvider;
import com.rtsoju.dku_council_homepage.domain.auth.email.dto.RequestEmailDto;
import com.rtsoju.dku_council_homepage.domain.auth.email.dto.request.EmailResponseDto;
import com.rtsoju.dku_council_homepage.domain.user.model.dto.RequestLoginDto;
import com.rtsoju.dku_council_homepage.domain.user.model.dto.RequestSignupDto;
import com.rtsoju.dku_council_homepage.domain.user.model.dto.response.LoginResponseDto;
import com.rtsoju.dku_council_homepage.domain.user.model.entity.User;
import com.rtsoju.dku_council_homepage.domain.user.model.entity.UserRole;
import com.rtsoju.dku_council_homepage.domain.user.repository.UserInfoRepository;
import com.rtsoju.dku_council_homepage.exception.EmailUserExistException;
import com.rtsoju.dku_council_homepage.exception.LoginPwdDifferentException;
import com.rtsoju.dku_council_homepage.exception.LoginUserNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
@Transactional
public class UserService {
    private final UserInfoRepository userInfoRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    public Optional<User> findById(Long userId) {
        return userInfoRepository.findById(userId);
    }

    public Long signup(RequestSignupDto dto) {
        // Todo : 학번 중복 검사
        // Todo : 이메일 인증
        // Todo : 핸드폰 인증

        String bcryptPwd = passwordEncoder.encode(dto.getPassword());
        dto.setPassword(bcryptPwd);
        User user = dto.toUserEntity();

        user.allocateRole("ROLE_USER");

        userInfoRepository.save(user);
        return user.getId();
    }

    public LoginResponseDto login(RequestLoginDto dto) {
        User findUser = userInfoRepository.findByClassId(dto.getClassId()).orElseThrow(LoginUserNotFoundException::new);

        if (passwordEncoder.matches(dto.getPassword(), findUser.getPassword())) {
            // Todo : 권한 부분 수정
            List<String> role = new ArrayList<>();
            List<UserRole> roles = findUser.getRoles();
            for (UserRole userRole : roles) {
                role.add(userRole.getRole());
            }
            String loginAccessToken = jwtProvider.createLoginAccessToken(findUser.getId(), role);
            String loginRefreshToken = jwtProvider.createLoginRefreshToken(findUser.getId());
            return new LoginResponseDto(loginAccessToken, loginRefreshToken);
        }else{
            throw new LoginPwdDifferentException("Wrong pwd");
        }
    }
    public void verifyExistMemberWithClassId(RequestEmailDto dto){
         userInfoRepository.findByClassId(dto.getClassId()).ifPresent(user -> {throw new EmailUserExistException("이미 존재하는 회원입니다.");});
    }
}
