package com.rtsoju.dku_council_homepage.domain.post.controller;

import com.rtsoju.dku_council_homepage.domain.post.entity.dto.AnnounceDto;
import com.rtsoju.dku_council_homepage.domain.post.entity.dto.PageRes;
import com.rtsoju.dku_council_homepage.domain.post.entity.subentity.Announce;
import com.rtsoju.dku_council_homepage.domain.post.entity.subentity.Petition;
import com.rtsoju.dku_council_homepage.domain.post.repository.PostRepository;
import com.rtsoju.dku_council_homepage.domain.post.service.AnnounceService;
import lombok.RequiredArgsConstructor;
import org.hibernate.sql.ANSICaseFragment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/announce")
public class AnnounceController {
    private final AnnounceService announceService;
    private final PostRepository postRepository;

    @GetMapping
    public PageRes<AnnounceDto> list(Pageable pageable){
        Page<AnnounceDto> map = announceService.announcePage(pageable);
        return new PageRes(map.getContent(), map.getPageable(), map.getTotalElements());
    }

    @GetMapping("/test")
    public Page<AnnounceDto> test(Pageable pageable){
       return announceService.announcePage(pageable);
    }
}
