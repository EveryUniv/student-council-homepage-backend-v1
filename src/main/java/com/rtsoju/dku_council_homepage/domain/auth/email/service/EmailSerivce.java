package com.rtsoju.dku_council_homepage.domain.auth.email.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rtsoju.dku_council_homepage.common.TextTemplateEngine;
import com.rtsoju.dku_council_homepage.common.cache.CacheData;
import com.rtsoju.dku_council_homepage.common.cache.CacheService;
import com.rtsoju.dku_council_homepage.common.jwt.JwtProvider;
import com.rtsoju.dku_council_homepage.domain.auth.email.dto.NhnMessage;
import com.rtsoju.dku_council_homepage.domain.auth.email.dto.request.RequestEmailDto;
import com.rtsoju.dku_council_homepage.domain.auth.email.dto.AuthTokenValidationResult;
import com.rtsoju.dku_council_homepage.domain.auth.email.dto.request.RequestEmailValidationDto;
import com.rtsoju.dku_council_homepage.domain.user.service.UserService;
import com.rtsoju.dku_council_homepage.exception.ClassIdNotMatchException;
import com.rtsoju.dku_council_homepage.exception.EmailCodeException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.regex.Pattern;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmailSerivce {
    private final Pattern classIdCheckPattern = Pattern.compile("^\\d{8}$");
    private final UserService userService;
    private final CacheService cacheService;

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

    private String makeTemplatedEmailForMobile(String studentId, String emailContent, String buttonContent){
        String text = new TextTemplateEngine.Builder()
                .argument("studentId", studentId)
                .argument("emailContent", emailContent)
                .argument("linkButtonContent", buttonContent)
                .build()
                .readHtmlFromResource("auth_email_content_mobile.html");
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

    public void sendEmailForSignUp(RequestEmailDto dto) throws IOException {
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

    public void sendEmailForChangePW(RequestEmailDto dto) throws IOException {
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


    public AuthTokenValidationResult validateToken(String token) {
        return new AuthTokenValidationResult(jwtProvider.validationToken(token));
    }

    public void sendEmailForMobileSignUp(RequestEmailDto dto) throws IOException {
        String studentId = dto.getClassId();
        userService.verifyExistMemberWithClassId(studentId);
        checkClassId(studentId);
        //검증 끝

        //인증코드 생성
        CacheData cacheData = cacheService.createCacheData(dto.getClassId());

        String message = makeTemplatedEmailForMobile(
                dto.getClassId(),
                "단국대학교 재학생 인증을 위해, 아래의 코드를\n입력해 주세요.",
                cacheData.getEmailCode()
        );

        OkHttpClient client = new OkHttpClient();
        sendMessage(client, message);
    }

    public String validateEmailCode(RequestEmailValidationDto dto) {
        //존재하지 않는 classId 면 -> throw NotFoundUserWithIdException
        CacheData cacheData = cacheService.getCacheData(dto.getClassId());
        //만료시간 지난 경우 (5분)
        if(cacheService.isExpired(cacheData)){
            throw new EmailCodeException("인증시간 초과. 재인증 요청바랍니다.");
        }
        if(!dto.getCode().equals(cacheData.getEmailCode())){
            throw new EmailCodeException("인증번호가 일치하지 않습니다.");
        }
        return jwtProvider.createEmailValidationToken(dto.getClassId());


    }
}
