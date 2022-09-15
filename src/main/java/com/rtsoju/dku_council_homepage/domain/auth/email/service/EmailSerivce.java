package com.rtsoju.dku_council_homepage.domain.auth.email.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rtsoju.dku_council_homepage.common.TextTemplateEngine;
import com.rtsoju.dku_council_homepage.common.jwt.JwtProvider;
import com.rtsoju.dku_council_homepage.domain.auth.email.dto.NhnMessage;
import com.rtsoju.dku_council_homepage.domain.auth.email.dto.RequestEmailDto;
import com.rtsoju.dku_council_homepage.domain.user.service.UserService;
import com.rtsoju.dku_council_homepage.exception.ClassIdNotMatchException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.regex.Pattern;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmailSerivce {
    private final Pattern classIdCheckPattern = Pattern.compile("^\\d{8}$");
    private final UserService userService;
    private final JavaMailSender javaMailSender;

    private final JwtProvider jwtProvider;

    @Value("${env_port}")
    private int port;
    @Value("${nhn.url}")
    private String url;
    @Value("${nhn.secret.key}")
    private String secretKey;
    @Value("${nhn.sender}")
    private String sender;
    @Value("${nhn.sender.name}")
    private String senderName;


    private final ObjectMapper objectMapper;

    private void checkClassId(String classId) {
        if (!classIdCheckPattern.matcher(classId).matches()) {
            throw new ClassIdNotMatchException("잘못된 형식의 학번입니다.");
        }
    }

    private String makeTemplatedEmail(String studentId, String endpoint, String emailContent, String buttonContent) {
        String emailToken = jwtProvider.createEmailValidationToken(studentId);
        String authLinkUrl = String.format("http://www.dku54play.site:%d/%s?token=%s&id=%s", port, endpoint, emailToken, studentId);
        String text = new TextTemplateEngine.Builder()
                .argument("studentId", studentId)
                .argument("authLinkUrl", authLinkUrl)
                .argument("emailContent", emailContent)
                .argument("linkButtonContent", buttonContent)
                .build()
                .readHtmlFromResource("auth_email_content.html");
        return text;
    }

    private String makeMessage(String studentId, String text) throws JsonProcessingException {
        NhnMessage nhnMessage = NhnMessage.builder()
                .senderAddress(sender)
                .senderName(senderName)
                .title("단국대학교 총학생회 이메일 인증")
                .body(text)
                .receiver(NhnMessage.Receiver.builder()
                        .receiveMailAddr(studentId + "@dankook.ac.kr")
//                        .receiveMailAddr("seoung59@gmail.com")
                        .build())
                .build();
        return objectMapper.writeValueAsString(nhnMessage);

    }

    private void sendMessage(OkHttpClient client, String text) throws IOException {
        RequestBody requestBody = RequestBody
                .create(text, MediaType.get("application/json;charset=utf-8"));
        Request request = new Request.Builder()
                .url(url)
                .addHeader(HttpHeaders.CONTENT_TYPE, "application/json;charset=utf-8")
                .addHeader("X-Secret-Key", secretKey)
                .post(requestBody)
                .build();
        Response execute = client.newCall(request).execute();
        log.info(execute.body().string());
    }

    public void sendEmailForSignUp(RequestEmailDto dto) throws MessagingException, IOException {
        String studentId = dto.getClassId();
        userService.verifyExistMemberWithClassId(studentId);
        checkClassId(studentId);
        String text = makeTemplatedEmail(
                dto.getClassId(),
                "sign-up",
                "단국대학교 재학생 인증을 위해, 아래의 버튼을\n클릭해 주세요.",
                "인증하기");
        String message = makeMessage(dto.getClassId(), text);
        OkHttpClient client = new OkHttpClient();
        sendMessage(client, message);
        return;
    }

    public void sendEmailForChangePW(RequestEmailDto dto) throws MessagingException, IOException {
        String classId = dto.getClassId();
        userService.checkUserExist(classId);
        String text = makeTemplatedEmail(
                dto.getClassId(),
                "password",
                "비밀번호 변경을 하시려면, 아래의 버튼을\n클릭해 주세요.",
                "비밀번호 변경");
        String message = makeMessage(dto.getClassId(), text);
        OkHttpClient client = new OkHttpClient();
        sendMessage(client, message);
        return;
    }

}
