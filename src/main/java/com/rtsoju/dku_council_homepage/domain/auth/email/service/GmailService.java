package com.rtsoju.dku_council_homepage.domain.auth.email.service;

import com.rtsoju.dku_council_homepage.common.jwt.JwtProvider;
import com.rtsoju.dku_council_homepage.domain.auth.email.dto.RequestEmailDto;
import com.rtsoju.dku_council_homepage.domain.user.service.UserService;
import com.rtsoju.dku_council_homepage.exception.ClassIdNotMatchException;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class GmailService {
    private final Pattern classIdCheckPattern = Pattern.compile("^\\d{8}$");
    private final UserService userService;
    private final JavaMailSender javaMailSender;

    private final JwtProvider jwtProvider;

    public void sendEmailForSignUp(RequestEmailDto dto){
        userService.verifyExistMemberWithClassId(dto.getClassId());
        checkClassId(dto.getClassId());
        SimpleMailMessage smm = new SimpleMailMessage();
        String emailToken = jwtProvider.createEmailValidationToken(dto.getClassId());
        smm.setTo(dto.getClassId()+"@dankook.ac.kr");
        smm.setSubject("단국대학교 총학생회 이메일 인증");
        smm.setText("http://133.186.132.198/sign-up?token="+emailToken+"&id="+dto.getClassId());
        javaMailSender.send(smm);
        return;
    }

    public void sendEmailForChangePW(RequestEmailDto dto){
        userService.verifyExistMemberWithClassId(dto.getClassId());
        checkClassId(dto.getClassId());
        SimpleMailMessage smm = new SimpleMailMessage();
        String emailToken = jwtProvider.createEmailValidationToken(dto.getClassId());
        smm.setTo(dto.getClassId()+"@dankook.ac.kr");
        smm.setSubject("단국대학교 총학생회 비밀번호 변경");
        smm.setText("http://133.186.132.198/password?token="+emailToken+"&id="+dto.getClassId());
        javaMailSender.send(smm);
        return;
    }




    private void checkClassId(String classId){
        if(!classIdCheckPattern.matcher(classId).matches()){
            throw new ClassIdNotMatchException("잘못된 형식의 학번입니다.");
        }
    }
}
