package com.rtsoju.dku_council_homepage.domain.page.controller;

import com.rtsoju.dku_council_homepage.common.PropertiesReader;
import com.rtsoju.dku_council_homepage.common.nhn.service.ObjectStorageService;
import com.rtsoju.dku_council_homepage.domain.page.service.MainPageService;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

class MainPageControllerTest {

    @Test
    void index() {
        PropertiesReader properties = PropertiesReader.INSTANCE;
        ObjectStorageService osService = new ObjectStorageService(
                properties.getValue("nhn.os.storageAccount"),
                properties.getValue("nhn.os.storageName"));
        MainPageService service = new MainPageService(osService);
        System.out.println(Arrays.toString(service.getCarouselImageURLs()));
    }
}