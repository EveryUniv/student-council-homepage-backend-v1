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
        NHNAuthService auth = new NHNAuthService(
                properties.getValue("nhn.os.tenantId"),
                properties.getValue("nhn.os.username"),
                properties.getValue("nhn.os.password"));

        ObjectStorageService osService = new ObjectStorageService(
                properties.getValue("nhn.os.storageAccount"),
                properties.getValue("nhn.os.storageName"));
        String token = auth.requestToken();

        String testBinary = "/Users/psvm/Desktop/test.jpg";
        String objectName = "ttttt.png";
        InputStream is = new FileInputStream(testBinary);
        osService.uploadObject(token, objectName, is);
    }
}