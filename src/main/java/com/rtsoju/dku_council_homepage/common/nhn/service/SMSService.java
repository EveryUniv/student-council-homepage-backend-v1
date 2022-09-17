package com.rtsoju.dku_council_homepage.common.nhn.service;

import com.rtsoju.dku_council_homepage.common.ExternalURLs;
import com.rtsoju.dku_council_homepage.common.Messages;
import com.rtsoju.dku_council_homepage.domain.auth.sms.dto.NHNCloudSMSResponse;
import com.rtsoju.dku_council_homepage.domain.auth.sms.dto.request.NHNCloudSMSRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class SMSService {
    private final RestTemplate restTemplate;
    private final String appKey;
    private final String secretKey;
    private final String senderPhone;

    // 테스트하기 쉽게 @Value를 생성자에서 붙여 초기화
    public SMSService(
            @Value("${nhn.sms.appKey}") String appKey,
            @Value("${nhn.sms.secretKey}") String secretKey,
            @Value("${auth.sms.senderPhone}") String senderPhone) {
        this.appKey = appKey;
        this.secretKey = secretKey;
        this.senderPhone = senderPhone;
        this.restTemplate = new RestTemplate();
    }

    /**
     * SMS를 전송합니다.
     *
     * @param phone 전화번호
     * @param body  전송 내용
     */
    public void sendSMS(String phone, String body) {
        final HttpHeaders headers = new HttpHeaders(); // 헤더에 key들을 담아준다.
        headers.set("X-Secret-Key", secretKey);
        headers.set("Content-Type", "application/json");

        // request api
        NHNCloudSMSRequest request = new NHNCloudSMSRequest(senderPhone, phone, body);
        HttpEntity<NHNCloudSMSRequest> entity = new HttpEntity<>(request, headers);
        String url = ExternalURLs.NHNSMS(appKey);
        NHNCloudSMSResponse response = restTemplate.postForObject(url, entity, NHNCloudSMSResponse.class);

        log.info(String.format("Result of sending SMS to %s: %s", phone, response));

        // handle response
        String failReason = null;
        if (response == null || response.getHeader() == null) {
            failReason = "response or header is null";
        } else {
            NHNCloudSMSResponse.Header header = response.getHeader();
            if (!header.getIsSuccessful()) {
                failReason = header.getResultMessage();
            }
        }

        if (failReason != null) {
            log.error("Can't send sms message: {}", failReason);
            if (response != null) {
                log.debug(response.toString());
            }
            throw new IllegalArgumentException(Messages.ERROR_SMS_SEND.getMessage());
        }
    }
}
