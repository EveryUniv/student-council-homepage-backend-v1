package com.rtsoju.dku_council_homepage.domain.token.entity;

import com.rtsoju.dku_council_homepage.domain.base.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ConfirmationToken extends BaseEntity {

    private static final long EMAIL_TOKEN_EXPIRATION_TIME_VALUE = 5L; //토큰 만료

    @Id @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name ="uuid2", strategy = "uuid2")
    @Column(length = 36)
    private String id;

    @Column
    private LocalDateTime expirationDate;

    @Column
    private boolean expired;

    @Column
    private String classId;

//    이메일 인증 토큰 생성

    public static ConfirmationToken createEmailConfirmationToken(String classId){
        ConfirmationToken confirmationToken = new ConfirmationToken();
        //5분
        confirmationToken.expirationDate = LocalDateTime.now().plusMinutes(EMAIL_TOKEN_EXPIRATION_TIME_VALUE);
        confirmationToken.classId = classId;
        confirmationToken.expired = false;
        return confirmationToken;
    }

    //토큰 사용 후 만료
    public void useToken(){
        this.expired = true;
    }

}
