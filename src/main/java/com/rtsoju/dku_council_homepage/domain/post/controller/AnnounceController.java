package com.rtsoju.dku_council_homepage.domain.post.controller;

import com.rtsoju.dku_council_homepage.common.ResponseResult;
import com.rtsoju.dku_council_homepage.common.SuccessResponseResult;
import com.rtsoju.dku_council_homepage.common.jwt.JwtProvider;
import com.rtsoju.dku_council_homepage.domain.post.entity.dto.AnnounceDto;
import com.rtsoju.dku_council_homepage.domain.post.entity.dto.PageRes;
import com.rtsoju.dku_council_homepage.domain.post.entity.dto.request.RequestAnnounceDto;
import com.rtsoju.dku_council_homepage.domain.post.entity.dto.response.IdResponseDto;
import com.rtsoju.dku_council_homepage.domain.post.entity.dto.response.ResponseAnnounceDto;
import com.rtsoju.dku_council_homepage.domain.post.service.AnnounceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/announce")
public class AnnounceController {
    private final AnnounceService announceService;
    private final JwtProvider jwtProvider;

    @GetMapping
    public PageRes<AnnounceDto> list(Pageable pageable) {
        Page<AnnounceDto> map = announceService.announcePage(pageable);
        return new PageRes<>(map.getContent(), map.getPageable(), map.getTotalElements());
    }

    @PostMapping
    public ResponseEntity<ResponseResult> create(@RequestBody RequestAnnounceDto data, HttpServletRequest httpServletRequest) {
        String userToken = httpServletRequest.getHeader("X-AUHT-TOKEN");
        String userId = jwtProvider.getUserId(userToken);
        long id = Long.parseLong(userId);
        IdResponseDto announce = announceService.createAnnounce(id, data);
        return ResponseEntity.ok()
                .body(new SuccessResponseResult("등록완료", announce));
    }

    @GetMapping("/{id}")
    public ResponseAnnounceDto findOne(@PathVariable("id") Long id) {
        ResponseAnnounceDto response = announceService.findOne(id);
//        return ResponseEntity.ok()
//                .body(new RequestResult("성공", response));
        return response;

    }
}
