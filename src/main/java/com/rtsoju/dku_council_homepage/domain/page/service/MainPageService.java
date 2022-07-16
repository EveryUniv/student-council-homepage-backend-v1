package com.rtsoju.dku_council_homepage.domain.page.service;

import com.rtsoju.dku_council_homepage.common.nhn.service.ObjectStorageService;
import com.rtsoju.dku_council_homepage.domain.page.dto.PostSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MainPageService {
    public static final String CAROUSEL_IMAGE_PREFIX = "carousel";
    public static final int CAROUSEL_IMAGE_COUNT = 3;

    private final ObjectStorageService s3service;

    public MainPageService(@Autowired ObjectStorageService s3service) {
        this.s3service = s3service;
    }

    public String[] getCarouselImageURLs() {
        String[] result = new String[CAROUSEL_IMAGE_COUNT];
        for (int i = 0; i < CAROUSEL_IMAGE_COUNT; i++) {
            result[i] = s3service.getObjectURL(CAROUSEL_IMAGE_PREFIX + i);
        }
        return result;
    }

    /**
     * 최근 회의록 목록을 가져온다. (n개)
     */
    public PostSummary[] getRecentConferences() {
        // TODO Implementation
        return null;
    }

    /**
     * 최근 총학 소식 목록을 가져온다. (n개)
     */
    public PostSummary[] getRecentNews() {
        // TODO Implementation
        return null;
    }

    /**
     * 진행중인 인기청원 목록을 가져온다. (n개)
     */
    public PostSummary[] getPopularPetitions() {
        // TODO Implementation
        return null;
    }
}
