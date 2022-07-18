package com.rtsoju.dku_council_homepage.domain.post.controller;

import com.rtsoju.dku_council_homepage.domain.post.entity.dto.ConferenceDto;
import com.rtsoju.dku_council_homepage.domain.post.entity.dto.PageRes;
import com.rtsoju.dku_council_homepage.domain.post.entity.dto.PetitionDto;
import com.rtsoju.dku_council_homepage.domain.post.entity.subentity.Announce;
import com.rtsoju.dku_council_homepage.domain.post.entity.subentity.Conference;
import com.rtsoju.dku_council_homepage.domain.post.service.ConferenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/conference")
public class ConferenceController {
    private final ConferenceService conferenceService;

    @GetMapping
    public PageRes<ConferenceDto> list(Pageable pageable){
        Page<ConferenceDto> map = conferenceService.conferencePage(pageable);
        return new PageRes<>(map.getContent(), map.getPageable(), map.getTotalElements());
    }


}
