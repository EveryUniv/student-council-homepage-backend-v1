package com.rtsoju.dku_council_homepage.domain.post.service;

import com.rtsoju.dku_council_homepage.domain.post.entity.dto.NewsDto;
import com.rtsoju.dku_council_homepage.domain.post.entity.subentity.News;
import com.rtsoju.dku_council_homepage.domain.post.repository.NewsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class NewsService {
    private final NewsRepository newsRepository;

    public Page<NewsDto> newsPage(Pageable pageable){
        Page<News> page = newsRepository.findAll(pageable);
        return page.map(NewsDto::new);
    }
}
