package com.rtsoju.dku_council_homepage.domain.page.controller;

import com.rtsoju.dku_council_homepage.common.PropertiesReader;
import com.rtsoju.dku_council_homepage.common.nhn.service.ObjectStorageService;
import com.rtsoju.dku_council_homepage.domain.page.service.MainPageService;
import com.rtsoju.dku_council_homepage.domain.post.service.ConferenceService;
import com.rtsoju.dku_council_homepage.domain.post.service.NewsService;
import com.rtsoju.dku_council_homepage.domain.post.service.PetitionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
@SpringBootTest
class MainPageControllerTest {

    @Autowired ConferenceService conferenceService;
    @Autowired
    NewsService newsService;
    @Autowired
    PetitionService petitionService;
    @Test
    void index() {
        PropertiesReader properties = PropertiesReader.INSTANCE;
        ObjectStorageService osService = new ObjectStorageService(
                properties.getValue("nhn.os.storageAccount"),
                properties.getValue("nhn.os.storageName"));
        MainPageService service = new MainPageService(osService, conferenceService, newsService, petitionService);
        //System.out.println(Arrays.toString(service.getca()));
    }
}