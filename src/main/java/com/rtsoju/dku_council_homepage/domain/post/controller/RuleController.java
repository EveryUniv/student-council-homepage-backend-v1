package com.rtsoju.dku_council_homepage.domain.post.controller;

import com.rtsoju.dku_council_homepage.common.ResponseResult;
import com.rtsoju.dku_council_homepage.common.SuccessResponseResult;
import com.rtsoju.dku_council_homepage.common.jwt.JwtProvider;
import com.rtsoju.dku_council_homepage.domain.post.entity.dto.page.PageRes;
import com.rtsoju.dku_council_homepage.domain.post.entity.dto.page.PageRuleDto;
import com.rtsoju.dku_council_homepage.domain.post.entity.dto.request.RequestRuleDto;
import com.rtsoju.dku_council_homepage.domain.post.entity.dto.response.IdResponseDto;
import com.rtsoju.dku_council_homepage.domain.post.entity.dto.response.ResponseRuleDto;
import com.rtsoju.dku_council_homepage.domain.post.service.RuleService;
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
@RequestMapping("/api/rule")
public class RuleController {
    private final RuleService ruleService;
    private final JwtProvider jwtProvider;


    /**
     * 목록 조회
     * @param query : 제목 및 본문에서 결과 탐색(Nullable)
     * @param pageable
     * @return
     */
    @GetMapping
    public PageRes<PageRuleDto> list(@RequestParam(value = "query", required = false)String query,  Pageable pageable) {
        Page<PageRuleDto> map = ruleService.rulePage(query, query, pageable);
        return new PageRes<>(map.getContent(), map.getPageable(), map.getTotalElements());
    }

    /**
     * 단건 등록
     * @param data : title, text, url(nullable)
     * @param request : header(X-AUTH-TOKEN)
     * @return : Rule PK
     */
    @PostMapping
    public ResponseEntity<ResponseResult> create(@Valid @ModelAttribute RequestRuleDto data, HttpServletRequest request){
        String token = jwtProvider.getTokenInHttpServletRequest(request);
        Long userId = Long.parseLong(jwtProvider.getUserId(token));
        IdResponseDto rule = ruleService.createRule(userId, data);
        return ResponseEntity.created(URI.create("/api/rule/"+rule.getId()))
                .body(new SuccessResponseResult("등록 완료", rule));
    }

    /**
     * 단건 조회
     * @param id : pk..
     * @return : title, text, createDate, fileUrl
     */
    @GetMapping("/{id}")
    public ResponseEntity<ResponseResult> findOne(@PathVariable("id")Long id){
        ResponseRuleDto response = ruleService.findOne(id);
        return ResponseEntity.ok()
                .body(new SuccessResponseResult(response));
    }

    /**
     * 삭제
     * @param id : PK
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseResult> deleteOne(@PathVariable("id") Long id){
        ruleService.deleteOne(id);
        return ResponseEntity.ok()
                .body(new SuccessResponseResult(id+"번 rule 삭제완료"));
    }

}
