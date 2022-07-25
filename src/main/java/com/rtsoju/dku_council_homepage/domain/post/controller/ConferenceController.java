package com.rtsoju.dku_council_homepage.domain.post.controller;

import com.rtsoju.dku_council_homepage.common.SuccessResponseResult;
import com.rtsoju.dku_council_homepage.common.jwt.JwtProvider;
import com.rtsoju.dku_council_homepage.domain.post.entity.dto.page.PageConferenceDto;
import com.rtsoju.dku_council_homepage.domain.post.entity.dto.page.PageRes;
import com.rtsoju.dku_council_homepage.domain.post.entity.dto.request.RequestConferenceDto;
import com.rtsoju.dku_council_homepage.domain.post.entity.subentity.Conference;
import com.rtsoju.dku_council_homepage.domain.post.service.ConferenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;

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

    /**
     * 회의록 등록 api
     * @param dto : title, date, round
     */
    @PostMapping
    public ResponseEntity<SuccessResponseResult> create(@Valid  @ModelAttribute RequestConferenceDto dto, HttpServletRequest request){
        String token = jwtProvider.getTokenInHttpServletRequest(request);
        Long userId = Long.parseLong(jwtProvider.getUserId(token));

        Conference conference = conferenceService.createConference(dto, userId);
        return ResponseEntity.created(URI.create("/api/conference/" + conference.getId()))
                .body(new SuccessResponseResult("등록 완료"));
    }



}
