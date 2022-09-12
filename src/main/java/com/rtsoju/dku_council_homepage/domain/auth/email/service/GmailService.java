package com.rtsoju.dku_council_homepage.domain.auth.email.service;

import com.rtsoju.dku_council_homepage.common.TextTemplateEngine;
import com.rtsoju.dku_council_homepage.common.jwt.JwtProvider;
import com.rtsoju.dku_council_homepage.domain.auth.email.dto.RequestEmailDto;
import com.rtsoju.dku_council_homepage.domain.user.service.UserService;
import com.rtsoju.dku_council_homepage.exception.ClassIdNotMatchException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class GmailService {
    private final Pattern classIdCheckPattern = Pattern.compile("^\\d{8}$");
    private final UserService userService;
    private final JavaMailSender javaMailSender;

    private final JwtProvider jwtProvider;

    @Value("${env_port}")
    private String port;

    //    public void sendEmailForSignUp(RequestEmailDto dto) {
//        userService.verifyExistMemberWithClassId(dto.getClassId());
//        checkClassId(dto.getClassId());
//        SimpleMailMessage smm = new SimpleMailMessage();
//        String emailToken = jwtProvider.createEmailValidationToken(dto.getClassId());
//        smm.setTo(dto.getClassId() + "@dankook.ac.kr");
//        smm.setSubject("단국대학교 총학생회 이메일 인증");
//        String text = "http://www.dku54play.site:" + port + "/sign-up?token=" + emailToken + "&id=" + dto.getClassId();
//        smm.setText(text);
//        javaMailSender.send(smm);
//        return;
//    }
    public void sendEmailForSignUp(RequestEmailDto dto) throws MessagingException {
        String studentId = dto.getClassId();
        userService.verifyExistMemberWithClassId(studentId);
        checkClassId(studentId);
        sendTemplatedEmail(
                dto.getClassId(),
                "sign-up",
                "단국대학교 재학생 인증을 위해, 아래의 버튼을\n클릭해 주세요.",
                "인증하기");
    }

//    public void sendEmailForChangePW(RequestEmailDto dto){
//        userService.verifyExistMemberWithClassId(dto.getClassId());
//        checkClassId(dto.getClassId());
//        SimpleMailMessage smm = new SimpleMailMessage();
//        String emailToken = jwtProvider.createEmailValidationToken(dto.getClassId());
//        smm.setTo(dto.getClassId()+"@dankook.ac.kr");
//        smm.setSubject("단국대학교 총학생회 비밀번호 변경");
//        smm.setText("http://www.dku54play.site:" + port + "/password?token=" + emailToken + "&id=" + dto.getClassId());
//        javaMailSender.send(smm);
//        return;
//    }

    public void sendEmailForChangePW(RequestEmailDto dto) throws MessagingException {
        sendTemplatedEmail(
                dto.getClassId(),
                "password",
                "비밀번호 변경을 하시려면, 아래의 버튼을\n클릭해 주세요.",
                "비밀번호 변경");
    }

    private void sendTemplatedEmail(String studentId, String endpoint, String emailContent, String buttonContent) throws MessagingException {
        MimeMessage mailSenderMimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mail = new MimeMessageHelper(mailSenderMimeMessage, "UTF-8");
        mail.setFrom("단국대학교 총학생회 <54thplay@gmail.com>");
        mail.setTo(studentId + "@dankook.ac.kr");
        mail.setSubject("단국대학교 총학생회 이메일 인증");

        String emailToken = jwtProvider.createEmailValidationToken(studentId);
        String authLinkUrl = String.format("http://www.dku54play.site:%s/%s?token=%s&id=%s", port, endpoint, emailToken, studentId);

        String text = new TextTemplateEngine.Builder()
                .argument("studentId", studentId)
                .argument("authLinkUrl", authLinkUrl)
                .argument("emailContent", emailContent)
                .argument("linkButtonContent", buttonContent)
                .build()
                .readHtmlFromResource("auth_email_content.html");
        mail.setText(text, true);

        javaMailSender.send(mailSenderMimeMessage);
    }


    private void checkClassId(String classId) {
        if (!classIdCheckPattern.matcher(classId).matches()) {
            throw new ClassIdNotMatchException("잘못된 형식의 학번입니다.");
        }
    }
}
