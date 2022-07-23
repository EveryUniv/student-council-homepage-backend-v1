package com.rtsoju.dku_council_homepage.domain.post.controller;

import com.rtsoju.dku_council_homepage.common.jwt.JwtProvider;
import com.rtsoju.dku_council_homepage.domain.post.entity.dto.page.PageConferenceDto;
import com.rtsoju.dku_council_homepage.domain.post.entity.dto.page.PageRes;
import com.rtsoju.dku_council_homepage.domain.post.service.ConferenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/conference")
public class ConferenceController {
    private final ConferenceService conferenceService;
    private final JwtProvider jwtProvider;

    @GetMapping
    public PageRes<PageConferenceDto> list(Pageable pageable){
        Page<PageConferenceDto> map = conferenceService.conferencePage(pageable);
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
