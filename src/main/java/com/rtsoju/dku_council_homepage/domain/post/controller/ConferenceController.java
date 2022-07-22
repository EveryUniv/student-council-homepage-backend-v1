package com.rtsoju.dku_council_homepage.domain.post.controller;

import com.rtsoju.dku_council_homepage.common.RequestResult;
import com.rtsoju.dku_council_homepage.common.jwt.JwtProvider;
import com.rtsoju.dku_council_homepage.domain.post.entity.dto.ConferenceDto;
import com.rtsoju.dku_council_homepage.domain.post.entity.dto.PageRes;
import com.rtsoju.dku_council_homepage.domain.post.entity.dto.request.RequestAnnounceDto;
import com.rtsoju.dku_council_homepage.domain.post.entity.dto.response.IdResponseDto;
import com.rtsoju.dku_council_homepage.domain.post.service.ConferenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/conference")
public class ConferenceController {
    private final ConferenceService conferenceService;
    private final JwtProvider jwtProvider;

    @GetMapping
    public PageRes<ConferenceDto> list(Pageable pageable){
        Page<ConferenceDto> map = conferenceService.conferencePage(pageable);
        return new PageRes<>(map.getContent(), map.getPageable(), map.getTotalElements());
    }

//    @PostMapping
//    public ResponseEntity<RequestResult> create(@RequestBody RequestAnnounceDto data, HttpServletRequest httpServletRequest){
//        String userToken = httpServletRequest.getHeader("X-AUHT-TOKEN");
//        String userId = jwtProvider.getUserId(userToken);
//        long id = Long.parseLong(userId);
//        IdResponseDto announce = conferenceService.createConference(id, data);
//        return ResponseEntity.ok()
//                .body(new RequestResult("등록완료",announce));
//    }



}
