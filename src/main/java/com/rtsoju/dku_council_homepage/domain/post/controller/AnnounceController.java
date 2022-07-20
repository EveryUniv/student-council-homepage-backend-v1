package com.rtsoju.dku_council_homepage.domain.post.controller;

import com.rtsoju.dku_council_homepage.common.RequestResult;
import com.rtsoju.dku_council_homepage.domain.post.entity.dto.AnnounceDto;
import com.rtsoju.dku_council_homepage.domain.post.entity.dto.PageRes;
import com.rtsoju.dku_council_homepage.domain.post.entity.request.RequestAnnounceDto;
import com.rtsoju.dku_council_homepage.domain.post.entity.subentity.Announce;
import com.rtsoju.dku_council_homepage.domain.post.entity.subentity.Petition;
import com.rtsoju.dku_council_homepage.domain.post.repository.PostRepository;
import com.rtsoju.dku_council_homepage.domain.post.service.AnnounceService;
import com.rtsoju.dku_council_homepage.domain.user.model.entity.User;
import lombok.RequiredArgsConstructor;
import org.hibernate.sql.ANSICaseFragment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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

    @PostMapping
    public RequestResult create(@RequestBody RequestAnnounceDto data, HttpServletRequest httpServletRequest){
        String userToken = httpServletRequest.getHeader("X-AUHT-TOKEN");
        announceService.createAnnounce(userToken, data);
        return new RequestResult("등록완료");
    }
}
