package com.rtsoju.dku_council_homepage.domain.event.controller;

import com.rtsoju.dku_council_homepage.common.SuccessResponseResult;
import com.rtsoju.dku_council_homepage.common.jwt.JwtProvider;
import com.rtsoju.dku_council_homepage.domain.event.model.dto.CharacterRequestDto;
import com.rtsoju.dku_council_homepage.domain.event.model.dto.CharacterResponseDto;
import com.rtsoju.dku_council_homepage.domain.event.model.dto.EventImageResponseDto;
import com.rtsoju.dku_council_homepage.domain.event.model.enums.Character;
import com.rtsoju.dku_council_homepage.domain.event.service.VoteService;
import com.rtsoju.dku_council_homepage.domain.event.service.EventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/event")
@RequiredArgsConstructor
public class EventController {
    private final EventService service;
    private final VoteService voteService;
    private final JwtProvider jwtProvider;

    @GetMapping("/image")
    public ResponseEntity<EventImageResponseDto> getEventImage() {
        EventImageResponseDto result = new EventImageResponseDto();
        result.setImageUrl(service.getEventImageUrl());
        return ResponseEntity.ok(result);
    }

    @GetMapping("/character")
    public ResponseEntity<CharacterResponseDto> getVotePage(HttpServletRequest request){
        String token = jwtProvider.getTokenInHttpServletRequest(request);
        long userId = Long.parseLong(jwtProvider.getUserId(token));
        CharacterResponseDto result = new CharacterResponseDto();
        result.setVote(voteService.isVote(userId));
        result.setSelected(voteService.whichVote(userId));
        return ResponseEntity.ok(result);
    }

    @PostMapping("/character")
    public ResponseEntity<SuccessResponseResult> voteCharacter(HttpServletRequest request, @RequestBody CharacterRequestDto dto){
        String token = jwtProvider.getTokenInHttpServletRequest(request);
        long userId = Long.parseLong(jwtProvider.getUserId(token));
        voteService.vote(userId, dto);
        return ResponseEntity.ok().body(new SuccessResponseResult("투표 성공!"));
    }
}
