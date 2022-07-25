package com.rtsoju.dku_council_homepage.domain.post.service;

import com.rtsoju.dku_council_homepage.common.nhn.service.FileUploadService;
import com.rtsoju.dku_council_homepage.common.nhn.service.NHNAuthService;
import com.rtsoju.dku_council_homepage.common.nhn.service.ObjectStorageService;
import com.rtsoju.dku_council_homepage.domain.page.dto.PostSummary;
import com.rtsoju.dku_council_homepage.domain.post.entity.Post;
import com.rtsoju.dku_council_homepage.domain.post.entity.PostFile;
import com.rtsoju.dku_council_homepage.domain.post.entity.dto.page.PageNewsDto;
import com.rtsoju.dku_council_homepage.domain.post.entity.dto.request.RequestNewsDto;
import com.rtsoju.dku_council_homepage.domain.post.entity.dto.response.GetOneNewsResponseDto;
import com.rtsoju.dku_council_homepage.domain.post.entity.subentity.News;
import com.rtsoju.dku_council_homepage.domain.post.repository.NewsRepository;
import com.rtsoju.dku_council_homepage.domain.user.model.entity.User;
import com.rtsoju.dku_council_homepage.domain.user.repository.UserRepository;
import com.rtsoju.dku_council_homepage.exception.FindPostWithIdNotFoundException;
import com.rtsoju.dku_council_homepage.exception.FindUserWithIdNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class NewsService {
    private final NewsRepository newsRepository;
    private final FileUploadService fileUploadService;

    // service로 입력받느냐 repo로 입력받느냐 뭐가 좋을까...?
    private final UserRepository userRepository;

    public Page<PageNewsDto> newsPage(Pageable pageable){
        Page<News> page = newsRepository.findAll(pageable);
        return page.map(PageNewsDto::new);
    }

    public List<PageNewsDto> latestTop5(){
        List<News> newsList = newsRepository.findTop5ByOrderByCreateDateDesc();
        return newsList.stream().map(PageNewsDto::new).collect(Collectors.toList());
    }

    public List<PostSummary> postPage(){
        List<News> newsList = newsRepository.findTop5ByOrderByCreateDateDesc();
        return newsList.stream().map(Post::summarize).collect(Collectors.toList());
    }

    @Transactional
    public News createNews(Long userId, RequestNewsDto dto) {
        User user = userRepository.findById(userId).orElseThrow(FindUserWithIdNotFoundException::new);

        ArrayList<PostFile> postFiles = fileUploadService.uploadFiles(dto.getFiles(),"news");
        News newNews = dto.toNewsEntity();
        newNews.putFiles(postFiles);

        newNews.putUser(user);

        return newsRepository.save(newNews);
    }

    public GetOneNewsResponseDto getOneNews(Long postId) {
        News news = newsRepository.findById(postId).orElseThrow(() -> new FindPostWithIdNotFoundException("id와 일치하는 news가 존재하지 않습니다."));

        GetOneNewsResponseDto response = new GetOneNewsResponseDto(news);
        return response;
    }



}
