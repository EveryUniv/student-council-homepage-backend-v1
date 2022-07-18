package com.rtsoju.dku_council_homepage.domain.page.service;

import com.rtsoju.dku_council_homepage.common.nhn.service.ObjectStorageService;
import com.rtsoju.dku_council_homepage.domain.page.dto.PostSummary;
import com.rtsoju.dku_council_homepage.domain.post.service.ConferenceService;
import com.rtsoju.dku_council_homepage.domain.post.service.NewsService;
import com.rtsoju.dku_council_homepage.domain.post.service.PetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public String[] getCarouselImageURLs() {
        String[] result = new String[CAROUSEL_IMAGE_COUNT];
        for (int i = 0; i < CAROUSEL_IMAGE_COUNT; i++) {
            result[i] = s3service.getObjectURL(CAROUSEL_IMAGE_PREFIX + i);
        }
        return result;
    }

    /**
     * 최근 회의록 목록을 가져온다. (5개)
     */
    public PostSummary[] getRecentConferences() {
        // TODO Implementation
        conferenceService.latestTop5();
        return null;
    }

    /**
     * 최근 총학 소식 목록을 가져온다. (5개)
     */
    public PostSummary[] getRecentNews() {
        // TODO Implementation
        newsService.latestTop5();
        return null;
    }

    /**
     * 진행중인 인기청원 목록을 가져온다. (n개) 추후 인기청원 맵핑하면 개선
     * 최근 청원 목록을 가져온다 (5개)
     */
    public PostSummary[] getPopularPetitions() {
        // TODO Implementation
        petitionService.latestTop5();
        return null;
    }
}
