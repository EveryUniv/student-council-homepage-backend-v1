package com.rtsoju.dku_council_homepage.common;

import org.springframework.cglib.core.internal.Function;

public class ExternalURLs {
    /**
     * appKey를 넣으면 NHN Cloud SMS API 주소를 뱉어준다
     */
    public static final Function<String, String> NHN_CLOUD_SMS =
            (appKey) -> String.format("https://api-sms.cloud.toast.com/sms/v3.0/appKeys/%s/sender/sms", appKey);
}
