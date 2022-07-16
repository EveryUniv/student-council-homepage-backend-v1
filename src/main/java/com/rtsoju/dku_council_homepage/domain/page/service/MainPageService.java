package com.rtsoju.dku_council_homepage.domain.page.service;

import com.rtsoju.dku_council_homepage.domain.page.dto.PostSummary;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MainPageService {
    public String[] getCarouselImageURLs() {
        // TODO Implementation
        return null;
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
