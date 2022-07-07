package com.rtsoju.dku_council_homepage.domain.user.service;

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

    public void sendSMS(String phone, String body) {
        final HttpHeaders headers = new HttpHeaders(); // 헤더에 key들을 담아준다.
        headers.set("X-Secret-Key", secretKey);
        headers.set("Content-Type", "application/json");

        final JSONObject requestJson = new JSONObject();
        requestJson.put("body", body);
        requestJson.put("sendNo", senderPhone);

        JSONObject recipient = new JSONObject();
        recipient.put("recipientNo", phone);

        ArrayList<JSONObject> recipientList = new ArrayList<>();
        recipientList.add(recipient);
        requestJson.put("recipientList", recipientList);

        final HttpEntity<String> entity = new HttpEntity<>(requestJson.toString(), headers);
        final String url = String.format("https://api-sms.cloud.toast.com/sms/v3.0/appKeys/%s/sender/sms", appKey);

        final String responseJson = restTemplate.postForObject(url, entity, String.class);
        try {
            Object response = jsonParser.parse(responseJson);
            if (!(response instanceof JSONObject)) {
                throw new RuntimeException("Response is not JSONObject");
            }
            JSONObject responseObj = (JSONObject) response;
            responseObj = (JSONObject) responseObj.get("header");
            if (!(boolean) responseObj.get("isSuccessful")) {
                log.error(response.toString());
                throw new IllegalArgumentException(Messages.ERROR_SMS_SEND.getMessage());
            }
        } catch (ParseException | ClassCastException e) {
            throw new RuntimeException(e);
        }
    }
}
