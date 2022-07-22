package com.rtsoju.dku_council_homepage.domain.post.controller;

import com.rtsoju.dku_council_homepage.domain.post.entity.dto.NewsDto;
import com.rtsoju.dku_council_homepage.domain.post.entity.dto.PageRes;
import com.rtsoju.dku_council_homepage.domain.post.entity.subentity.Conference;
import com.rtsoju.dku_council_homepage.domain.post.entity.subentity.News;
import com.rtsoju.dku_council_homepage.domain.post.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/news")
public class NewsController {
    private final NewsService newsService;

    @GetMapping
    public PageRes<NewsDto> list(Pageable pageable){
        Page<NewsDto> map = newsService.newsPage(pageable);
        List<NewsDto> list = newsService.latestTop5();
        return new PageRes(map.getContent(), map.getPageable(), map.getTotalElements());
    }
}
