package com.rtsoju.dku_council_homepage.common;

public class ExternalURLs {

    /**
     * appKey를 넣으면 NHN Cloud SMS API 주소를 뱉어준다
     */
    public static String NHNSMS(String appKey) {
        return String.format("https://api-sms.cloud.toast.com/sms/v3.0/appKeys/%s/sender/auth/sms", appKey);
    }

    /**
     * NHN Cloud 인증 토큰 발급 주소
     */
    public static final String NHNAuth = "https://api-identity.infrastructure.cloud.toast.com/v2.0/tokens";

    /**
     * Object Storage Access URL
     */
    public static String NHNObjectStorage(String storageAccount,
                                          String containerName,
                                          String objectName) {
        return String.format("%s/%s/%s", NHNObjectStorage(storageAccount), containerName, objectName);
    }

    public static String NHNObjectStorage(String storageAccount) {
        return String.format("https://api-storage.cloud.toast.com/v1/%s", storageAccount);
    }
}
