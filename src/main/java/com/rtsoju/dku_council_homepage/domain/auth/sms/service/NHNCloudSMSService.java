package com.rtsoju.dku_council_homepage.domain.auth.sms.service;

import com.rtsoju.dku_council_homepage.common.ExternalURLs;
import com.rtsoju.dku_council_homepage.common.Messages;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Service
public class NHNCloudSMSService {
    private final RestTemplate restTemplate = new RestTemplate();
    private final JSONParser jsonParser = new JSONParser();

    @Value("${auth.sms.appKey}")
    private String appKey;

    @Value("${auth.sms.secretKey}")
    private String secretKey;

    @Value("${auth.sms.senderPhone}")
    private String senderPhone;

    /**
     * SMS를 전송합니다.
     * @param phone 전화번호
     * @param body 전송 내용
     */
    public void sendSMS(String phone, String body) {
        final HttpHeaders headers = new HttpHeaders(); // 헤더에 key들을 담아준다.
        headers.set("X-Secret-Key", secretKey);
        headers.set("Content-Type", "application/json");

        final HttpEntity<String> entity = new HttpEntity<>(makeRequestJson(phone, body), headers);
        final String url = ExternalURLs.NHN_CLOUD_SMS.apply(appKey);
        final String responseJson = restTemplate.postForObject(url, entity, String.class);

        try {
            JSONObject responseObj = (JSONObject) jsonParser.parse(responseJson);
            responseObj = (JSONObject) responseObj.get("header");
            if (!(boolean) responseObj.get("isSuccessful")) {
                log.error(responseObj.toString());
                throw new IllegalArgumentException(Messages.ERROR_SMS_SEND.getMessage());
            }
        } catch (ParseException | ClassCastException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * request에 사용할 json을 만든다.
     */
    private String makeRequestJson(String phone, String body){
        final Map<String, Object> request = new HashMap<>();
        request.put("body", body);
        request.put("sendNo", senderPhone);

        Map<String, String> recipient = new HashMap<>();

        // 전화번호에 하이픈(-)을 넣어도 알아서 빼주기.
        recipient.put("recipientNo", phone.replaceAll("-", ""));

        ArrayList<Map<String, String>> recipientList = new ArrayList<>();
        recipientList.add(recipient);
        request.put("recipientList", recipientList);

        return new JSONObject(request).toString();
    }
}
