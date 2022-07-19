package com.rtsoju.dku_council_homepage.domain.page.controller;

import com.rtsoju.dku_council_homepage.domain.page.dto.MainPageResponse;
import com.rtsoju.dku_council_homepage.domain.page.service.MainPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MainPageController {
    private final MainPageService service;

    @GetMapping("/")
    public MainPageResponse index() {
        MainPageResponse response = new MainPageResponse();
//        response.setCarouses(service.getCarouselImageURLs());
        response.setPopularPetitions(service.getPopularPetitions());
        response.setRecentNews(service.getRecentNews());
        response.setRecentConferences(service.getRecentConferences());
        return response;
    }
}
