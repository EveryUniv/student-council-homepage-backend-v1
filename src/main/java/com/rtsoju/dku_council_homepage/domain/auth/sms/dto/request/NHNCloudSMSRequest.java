package com.rtsoju.dku_council_homepage.domain.auth.sms.dto.request;

import lombok.Data;

import java.util.*;

@Data
public class NHNCloudSMSRequest {
    private String body;
    private String sendNo;
    private List<Recipient> recipientList;

    public NHNCloudSMSRequest(String senderPhone, String phone, String body) {
        setBody(body);
        setSendNo(senderPhone);
        setRecipientList(List.of(new Recipient(phone)));
    }

    @Data
    public static class Recipient {
        private String recipientNo;

        public Recipient(String recipientNo) {
            // 전화번호에 하이픈(-)을 넣어도 알아서 빼주기.
            setRecipientNo(recipientNo.replaceAll("-", ""));
        }
    }
}
