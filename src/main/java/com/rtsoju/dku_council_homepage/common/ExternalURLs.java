package com.rtsoju.dku_council_homepage.common;

public class ExternalURLs {
    /**
     * appKey를 넣으면 NHN Cloud SMS API 주소를 뱉어준다
     */
    public static String NHNCloudSMS(String appKey) {
        return String.format("https://api-sms.cloud.toast.com/sms/v3.0/appKeys/%s/sender/auth/sms", appKey);
    }
}
