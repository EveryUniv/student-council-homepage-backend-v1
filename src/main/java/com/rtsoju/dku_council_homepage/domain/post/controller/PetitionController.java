package com.rtsoju.dku_council_homepage.domain.post.controller;

import com.rtsoju.dku_council_homepage.common.ResponseResult;
import com.rtsoju.dku_council_homepage.common.SuccessResponseResult;
import com.rtsoju.dku_council_homepage.common.jwt.JwtProvider;
import com.rtsoju.dku_council_homepage.domain.post.entity.dto.page.PagePetitionDto;
import com.rtsoju.dku_council_homepage.domain.post.entity.dto.page.PageRes;
import com.rtsoju.dku_council_homepage.domain.post.entity.dto.request.RequestPetitionDto;
import com.rtsoju.dku_council_homepage.domain.post.entity.dto.response.IdResponseDto;
import com.rtsoju.dku_council_homepage.domain.post.service.PetitionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/petition")
public class PetitionController {
    private final PetitionService petitionService;
    private final JwtProvider jwtProvider;

    @GetMapping
    public PageRes<PagePetitionDto> list(Pageable pageable) {
        Page<PagePetitionDto> map = petitionService.petitionPage(pageable);
        return new PageRes<>(map.getContent(), map.getPageable(), map.getTotalElements());
    }

    /**
     * 키워드 검색
     */
    @GetMapping("/status")
    public PageRes<PagePetitionDto> listStatus(@RequestParam(value = "status") String status, Pageable pageable) {
        Page<PagePetitionDto> map = petitionService.petitionPageOnStatus(status, pageable);
        return new PageRes<>(map.getContent(), map.getPageable(), map.getTotalElements());
    }

    @PostMapping
    public ResponseEntity<ResponseResult> create(@RequestBody RequestPetitionDto data, HttpServletRequest httpServletRequest) {
        String userToken = httpServletRequest.getHeader("X-AUHT-TOKEN");
        String userId = jwtProvider.getUserId(userToken);
        long id = Long.parseLong(userId);
        IdResponseDto announce = petitionService.createPetition(id, data);
        return ResponseEntity.ok()
                .body(new SuccessResponseResult("등록완료", announce));
    }


}
