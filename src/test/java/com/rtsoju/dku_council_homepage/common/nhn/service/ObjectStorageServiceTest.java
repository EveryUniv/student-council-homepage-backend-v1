package com.rtsoju.dku_council_homepage.common.nhn.service;

import com.rtsoju.dku_council_homepage.common.PropertiesReader;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

class ObjectStorageServiceTest {

    @Test
    void uploadObject() throws FileNotFoundException {
        PropertiesReader properties = PropertiesReader.INSTANCE;
        AuthService auth = new AuthService(
                properties.getValue("nhn.os.tenantId"),
                properties.getValue("nhn.os.username"),
                properties.getValue("nhn.os.password"));

        ObjectStorageService osService = new ObjectStorageService(properties.getValue("nhn.os.storageAccount"));
        String token = auth.requestToken();

        String testBinary = "/Users/psvm/Desktop/test.jpg";
        String objectName = "mytest.jpg";
        InputStream is = new FileInputStream(testBinary);
        osService.uploadObject(token, "main-storage", objectName, is);
    }
}