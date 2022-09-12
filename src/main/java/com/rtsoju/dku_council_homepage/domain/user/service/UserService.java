package com.rtsoju.dku_council_homepage.domain.user.service;

import com.rtsoju.dku_council_homepage.common.jwt.JwtProvider;
import com.rtsoju.dku_council_homepage.domain.user.model.dto.request.RequestChangePWDto;
import com.rtsoju.dku_council_homepage.domain.user.model.dto.request.RequestLoginDto;
import com.rtsoju.dku_council_homepage.domain.user.model.dto.request.RequestReissueDto;
import com.rtsoju.dku_council_homepage.domain.user.model.dto.request.RequestSignupDto;
import com.rtsoju.dku_council_homepage.domain.user.model.dto.response.RoleAndTokenResponseDto;
import com.rtsoju.dku_council_homepage.domain.user.model.entity.User;
import com.rtsoju.dku_council_homepage.domain.user.model.entity.UserRole;
import com.rtsoju.dku_council_homepage.domain.user.repository.UserRepository;
import com.rtsoju.dku_council_homepage.exception.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    public Long signup(RequestSignupDto dto, String emailValidationToken) {
        // Todo : 학번 중복 검사 -> 학번은 이메일을 통한 회원가입시 중복검사
        // Todo : 이메일 인증
        // Todo : 핸드폰 인증
        // Todo : 학번 제대로 되어 있는지 토큰값으로 확인

        checkEmailValidationToken(emailValidationToken, dto.getClassId());

        String bcryptPwd = passwordEncoder.encode(dto.getPassword());
        dto.setPassword(bcryptPwd);
        User user = dto.toUserEntity();
        user.allocateRole("ROLE_USER");

        userRepository.save(user);
        return user.getId();
    }

    private void checkEmailValidationToken(String token, String classId){
        if(token==null){
            throw new ReissueAccessTokenNotCorrectException("유효하지 않은 토큰입니다. 이메일 재전송 해주세요");
        }

        if(!jwtProvider.validateEmailValidationToken(token, classId)){
            throw new RefreshTokenNotValidateException("학번이 조작되었습니다.");
        }
        if(userRepository.findByClassId(classId.trim()).isPresent()){
            throw new DuplicateSignInException("이미 회원가입이 되었습니다.");
        }

    }

    public RoleAndTokenResponseDto login(RequestLoginDto dto) {
        User findUser = userRepository.findByClassId(dto.getClassId().trim()).orElseThrow(LoginUserNotFoundException::new);

        if (passwordEncoder.matches(dto.getPassword(), findUser.getPassword())) {
            // Todo : 권한 부분 수정
            boolean isAdmin = false;
            List<String> role = getRole(findUser);
            String loginAccessToken = jwtProvider.createLoginAccessToken(findUser.getId(), role);
            String loginRefreshToken = jwtProvider.createLoginRefreshToken(findUser.getId());
            if(role.contains("ROLE_ADMIN")){
                isAdmin = true;
            }
            return new RoleAndTokenResponseDto(loginAccessToken, loginRefreshToken, isAdmin);
        } else {
            throw new LoginPwdDifferentException("Wrong pwd");
        }
    }

    public void verifyExistMemberWithClassId(String classId) {
        userRepository.findByClassId(classId.trim()).ifPresent(user -> {
            throw new EmailUserExistException("이미 존재하는 회원입니다.");
        });
    }

    private List<String> getRole(User user) {
        List<String> role = new ArrayList<>();
        List<UserRole> roles = user.getRoles();
        for (UserRole userRole : roles) {
            role.add(userRole.getRole());
        }

        return role;
    }

    public RoleAndTokenResponseDto tokenReissue(RequestReissueDto dto) {
        String accessToken = dto.getAccessToken();
        String refreshToken = dto.getRefreshToken();
        boolean isAdmin = false;
        if (!jwtProvider.validationToken(refreshToken))
            throw new RefreshTokenNotValidateException();

        Long userId = Long.parseLong(jwtProvider.getUserId(accessToken));
        User user = userRepository.findById(userId).orElseThrow(FindUserWithIdNotFoundException::new);
        List<String> role = getRole(user);

        String newAccessToken = jwtProvider.createLoginAccessToken(userId, role);
        String newRefreshToken = jwtProvider.createLoginRefreshToken(userId);
        if(role.contains("ROLE_ADMIN")){
            isAdmin = true;
        }
        return new RoleAndTokenResponseDto(newAccessToken, newRefreshToken, isAdmin);

    }

    public User addRoleAdmin(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(FindUserWithIdNotFoundException::new);
        user.allocateRole("ROLE_ADMIN");

        return user;
    }

    public void changePW(RequestChangePWDto request, String token) {
        String userId = request.getUserId();
        if(!jwtProvider.validateEmailValidationToken(token, userId)){
            throw new BadRequestException("학번이 조작됐습니다.");
        }
        User user = userRepository.findByClassId(userId.trim()).orElseThrow(FindUserWithIdNotFoundException::new);
        String bcryptPWD = passwordEncoder.encode(request.getNewPW());
        user.changePassword(bcryptPWD);
        return;
    }

    public void checkUserExist(String userId){
        userRepository.findByClassId(userId.trim()).orElseThrow(() -> new FindUserWithIdNotFoundException("회원가입을 진행해주세요"));
        return;
    }
}
