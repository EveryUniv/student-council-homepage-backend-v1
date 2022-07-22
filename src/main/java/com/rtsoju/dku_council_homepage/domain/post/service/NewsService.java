package com.rtsoju.dku_council_homepage.domain.post.service;

import com.rtsoju.dku_council_homepage.domain.page.dto.PostSummary;
import com.rtsoju.dku_council_homepage.domain.post.entity.Post;
import com.rtsoju.dku_council_homepage.domain.post.entity.dto.NewsDto;
import com.rtsoju.dku_council_homepage.domain.post.entity.subentity.News;
import com.rtsoju.dku_council_homepage.domain.post.repository.NewsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class NewsService {
    private final NewsRepository newsRepository;

    public Page<NewsDto> newsPage(Pageable pageable){
        Page<News> page = newsRepository.findAll(pageable);
        return page.map(NewsDto::new);
    }

    public List<NewsDto> latestTop5(){
        List<News> newsList = newsRepository.findTop5ByOrderByCreateDateDesc();
        return newsList.stream().map(NewsDto::new).collect(Collectors.toList());
    }

    public List<PostSummary> postPage(){
        List<News> newsList = newsRepository.findTop5ByOrderByCreateDateDesc();
        return newsList.stream().map(Post::summarize).collect(Collectors.toList());
    }

}
