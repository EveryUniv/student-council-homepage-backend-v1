package com.rtsoju.dku_council_homepage.domain.page.dto;

import lombok.Data;

import java.util.List;

@Data
public class MainPageResponse {
    private List<String> carousels;
    private List<PostSummary> recentNews;
    private List<PostSummary> recentConferences;
    private List<PostSummary> popularPetitions;
}
