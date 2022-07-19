package com.rtsoju.dku_council_homepage.domain.page.service;

import com.rtsoju.dku_council_homepage.common.nhn.service.ObjectStorageService;
import com.rtsoju.dku_council_homepage.domain.page.dto.PostSummary;
import com.rtsoju.dku_council_homepage.domain.post.service.ConferenceService;
import com.rtsoju.dku_council_homepage.domain.post.service.NewsService;
import com.rtsoju.dku_council_homepage.domain.post.service.PetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MainPageService {
    public static final String CAROUSEL_IMAGE_PREFIX = "carousel";
    public static final int CAROUSEL_IMAGE_COUNT = 3;

    private final ObjectStorageService s3service;
    private final ConferenceService conferenceService;
    private final NewsService newsService;
    private final PetitionService petitionService;

    public MainPageService(@Autowired ObjectStorageService s3service, ConferenceService conferenceService, NewsService newsService, PetitionService petitionService) {
        this.s3service = s3service;
        this.conferenceService = conferenceService;
        this.newsService = newsService;
        this.petitionService = petitionService;
    }

//    public List<String> getCarouselImageURLs() {
//        return result;
//    }

    /**
     * 최근 회의록 목록을 가져온다. (5개)
     */
    public List<PostSummary> getRecentConferences() {
        // TODO Implementation
        return conferenceService.postPage();
    }

    /**
     * 최근 총학 소식 목록을 가져온다. (5개)
     */
    public List<PostSummary> getRecentNews() {
        // TODO Implementation
        return newsService.postPage();
    }

    /**
     * 진행중인 인기청원 목록을 가져온다. (n개) 추후 인기청원 맵핑하면 개선
     * 최근 청원 목록을 가져온다 (5개)
     */
    public List<PostSummary> getPopularPetitions() {
        // TODO Implementation
        return petitionService.postPage();
    }
}
