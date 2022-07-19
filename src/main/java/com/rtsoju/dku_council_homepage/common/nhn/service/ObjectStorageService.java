package com.rtsoju.dku_council_homepage.common.nhn.service;

import com.rtsoju.dku_council_homepage.common.ExternalURLs;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;

import java.io.InputStream;

@Service
public class ObjectStorageService {
    private final String storageAccount;
    private final String storageName;
    private final RestTemplate restTemplate;

    public ObjectStorageService(
            @Value("${nhn.os.storageAccount}") String storageAccount,
            @Value("${nhn.os.storageName}") String storageName) {
        this.storageAccount = storageAccount;
        this.storageName = storageName;

        // RequestCallback을 사용할 수 있도록 설정
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setBufferRequestBody(false);
        restTemplate = new RestTemplate(requestFactory);
    }

    public String getObjectURL(String objectName) {
        return ExternalURLs.NHNObjectStorage(storageAccount, storageName, objectName);
    }

    public void uploadObject(String tokenId, String objectName, final InputStream inputStream) {
        // InputStream을 요청 본문에 추가할 수 있도록 RequestCallback 오버라이드
        final RequestCallback requestCallback = request -> {
            request.getHeaders().add("X-Auth-Token", tokenId);
            IOUtils.copy(inputStream, request.getBody());
        };

        HttpMessageConverterExtractor<String> responseExtractor
                = new HttpMessageConverterExtractor<>(String.class, restTemplate.getMessageConverters());

        restTemplate.execute(getObjectURL(objectName), HttpMethod.PUT, requestCallback, responseExtractor);
    }

    public void deleteObject(String tokenId, String objectName) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Auth-Token", tokenId);
        HttpEntity<String> requestHttpEntity = new HttpEntity<String>(null, headers);

        this.restTemplate.exchange(getObjectURL(objectName), HttpMethod.DELETE, requestHttpEntity, String.class);
    }
}
