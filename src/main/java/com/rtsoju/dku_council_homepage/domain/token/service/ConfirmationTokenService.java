package com.rtsoju.dku_council_homepage.domain.token.service;

import com.rtsoju.dku_council_homepage.domain.token.entity.ConfirmationToken;
import com.rtsoju.dku_council_homepage.domain.token.repository.ConfirmationTokenRepository;
import com.rtsoju.dku_council_homepage.exception.BadRequestException;
import com.rtsoju.dku_council_homepage.services.EmailSenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.xml.bind.ValidationException;
import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ConfirmationTokenService {
    private final ConfirmationTokenRepository confirmationTokenRepository;
    private final EmailSenderService emailSenderService;

    //토큰 생성
    public String createEmailConfirmationToken(String userId, String receiverEmail){
        Assert.hasText(userId, "userId는 필수입니다.");
        Assert.hasText(receiverEmail, "이메일 입력해주세요");

        ConfirmationToken emailConfirmationToken = ConfirmationToken.createEmailConfirmationToken(userId);
        confirmationTokenRepository.save(emailConfirmationToken);

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(receiverEmail);
        mailMessage.setSubject("회원가입 이메일 인증");
        mailMessage.setText("http://localhost:8090/confirm-email?token="+emailConfirmationToken.getId());
        emailSenderService.sendEmail(mailMessage);

        return emailConfirmationToken.getId();
    }

    public ConfirmationToken findByIdAndExpirationDateAfterAndExpired(String confirmationTokenId){
        Optional<ConfirmationToken> confirmationToken = confirmationTokenRepository.findByIdAndExpirationDateAfterAndExpired(confirmationTokenId, LocalDateTime.now(), false);
        return confirmationToken.orElseThrow(() -> new BadRequestException("TOKEN_NOT_FOUND"));

    }
}
