package com.rtsoju.dku_council_homepage.domain.post.controller;

import com.rtsoju.dku_council_homepage.common.SuccessResponseResult;
import com.rtsoju.dku_council_homepage.common.jwt.JwtProvider;
import com.rtsoju.dku_council_homepage.domain.post.entity.dto.page.PageNewsDto;
import com.rtsoju.dku_council_homepage.domain.post.entity.dto.page.PageRes;
import com.rtsoju.dku_council_homepage.domain.post.entity.dto.request.RequestNewsDto;
import com.rtsoju.dku_council_homepage.domain.post.entity.dto.response.GetOneNewsResponseDto;
import com.rtsoju.dku_council_homepage.domain.post.entity.subentity.News;
import com.rtsoju.dku_council_homepage.domain.post.service.NewsService;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/news")
public class NewsController {
    private final NewsService newsService;
    private final JwtProvider jwtProvider;

    @GetMapping
    public PageRes<PageNewsDto> list(Pageable pageable) {
        Page<PageNewsDto> map = newsService.newsPage(pageable);
        List<PageNewsDto> list = newsService.latestTop5();
        return new PageRes<>(map.getContent(), map.getPageable(), map.getTotalElements());
    }

    /**
     * 총학소식 등록
     */
    @PostMapping
    public ResponseEntity<SuccessResponseResult> createNews(@Valid @ModelAttribute RequestNewsDto dto, HttpServletRequest request) {
        String token = jwtProvider.getTokenInHttpServletRequest(request);
        Long userId = Long.parseLong(jwtProvider.getUserId(token));

        News news = newsService.createNews(userId, dto);

        return ResponseEntity.created(URI.create("/api/news/" + news.getId()))
                .body(new SuccessResponseResult("등록 완료"));
    }

    /**
     * 총학소식 단건 조회
     */
    @GetMapping("/{postId}")
    public ResponseEntity<SuccessResponseResult> getOneNews(@PathVariable("postId") Long postId) {
        GetOneNewsResponseDto response = newsService.getOneNews(postId);

        return ResponseEntity.ok().body(new SuccessResponseResult(response));
    }
}
