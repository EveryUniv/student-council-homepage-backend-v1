package com.rtsoju.dku_council_homepage.domain.page.dto;

import lombok.Data;

@Data
public class MainPageResponse {
    private String[] carouses;
    private PostSummary[] recentNews;
    private PostSummary[] recentConferences;
    private PostSummary[] popularPetitions;
}
