package com.rtsoju.dku_council_homepage.domain.event.controller;

import com.rtsoju.dku_council_homepage.domain.event.dto.EventImageResponseDto;
import com.rtsoju.dku_council_homepage.domain.event.service.EventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/event")
@RequiredArgsConstructor
public class EventController {
    private final EventService service;

    @GetMapping("/image")
    public ResponseEntity<EventImageResponseDto> getEventImage() {
        EventImageResponseDto result = new EventImageResponseDto();
        result.setImageUrl(service.getEventImageUrl());
        return ResponseEntity.ok(result);
    }
}
