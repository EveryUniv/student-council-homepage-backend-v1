package com.rtsoju.dku_council_homepage.domain.post.controller;

import com.rtsoju.dku_council_homepage.domain.post.entity.dto.PageRes;
import com.rtsoju.dku_council_homepage.domain.post.entity.dto.PetitionDto;
import com.rtsoju.dku_council_homepage.domain.post.entity.subentity.Petition;
import com.rtsoju.dku_council_homepage.domain.post.service.PetitionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/petition")
public class PetitionController {
    private final PetitionService petitionService;

    @GetMapping
    public PageRes<PetitionDto> list(Pageable pageable){
        Page<PetitionDto> map = petitionService.petitionPage(pageable);
        return new PageRes(map.getContent(), map.getPageable(), map.getTotalElements());
    }


}
