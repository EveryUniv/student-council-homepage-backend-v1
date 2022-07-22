package com.rtsoju.dku_council_homepage.domain.post.controller;

import com.rtsoju.dku_council_homepage.domain.post.entity.dto.PageRes;
import com.rtsoju.dku_council_homepage.domain.post.entity.dto.RuleDto;
import com.rtsoju.dku_council_homepage.domain.post.entity.subentity.Rule;
import com.rtsoju.dku_council_homepage.domain.post.service.RuleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/rule")
public class RuleController {
    private final RuleService ruleService;

    @GetMapping
    public PageRes<RuleDto> list(Pageable pageable){
        Page<RuleDto> map = ruleService.rulePage(pageable);
        return new PageRes(map.getContent(), map.getPageable(), map.getTotalElements());
    }
}
