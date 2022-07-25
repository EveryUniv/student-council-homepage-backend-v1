package com.rtsoju.dku_council_homepage.domain.post.controller;

import com.rtsoju.dku_council_homepage.common.jwt.JwtProvider;
import com.rtsoju.dku_council_homepage.domain.post.entity.dto.page.PageRes;
import com.rtsoju.dku_council_homepage.domain.post.entity.dto.page.PageRuleDto;
import com.rtsoju.dku_council_homepage.domain.post.service.RuleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/rule")
public class RuleController {
    private final RuleService ruleService;
    private final JwtProvider jwtProvider;

    /**
     * 목록조회 (KeyWord로 조회하기)추가하기!
     * @param pageable
     * @return
     */

    @GetMapping
    public PageRes<PageRuleDto> list(Pageable pageable) {
        Page<PageRuleDto> map = ruleService.rulePage(pageable);
        return new PageRes<>(map.getContent(), map.getPageable(), map.getTotalElements());
    }

    @GetMapping
    public PageRes<PageRuleDto> list(@RequestParam(value = "query", required = false)String query, Pageable pageable){
        Page<PageRuleDto> map = ruleService.rulePageByTitleOrText(query, query, pageable);
    }

    /**
     * 본문 및 제목 검색 API
     */
}
