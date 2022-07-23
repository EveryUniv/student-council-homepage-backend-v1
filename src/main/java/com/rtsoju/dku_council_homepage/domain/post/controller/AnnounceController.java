package com.rtsoju.dku_council_homepage.domain.post.controller;

import com.rtsoju.dku_council_homepage.common.ResponseResult;
import com.rtsoju.dku_council_homepage.common.SuccessResponseResult;
import com.rtsoju.dku_council_homepage.common.jwt.JwtProvider;
import com.rtsoju.dku_council_homepage.domain.post.entity.dto.page.PageAnnounceDto;
import com.rtsoju.dku_council_homepage.domain.post.entity.dto.page.PageRes;
import com.rtsoju.dku_council_homepage.domain.post.entity.dto.request.RequestAnnounceDto;
import com.rtsoju.dku_council_homepage.domain.post.entity.dto.response.IdResponseDto;
import com.rtsoju.dku_council_homepage.domain.post.entity.dto.response.ResponseAnnounceDto;
import com.rtsoju.dku_council_homepage.domain.post.service.AnnounceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/announce")
public class AnnounceController {
    private final AnnounceService announceService;
    private final JwtProvider jwtProvider;

    /**
     * 목록조회 (keyWord로 조회하기)추가하기!
     * api/announce 호출시 uri param ?page, size, sort custom가능
     */
    @GetMapping
    public PageRes<PageAnnounceDto> list(Pageable pageable) {
        Page<PageAnnounceDto> map = announceService.announcePage(pageable);
        return new PageRes<>(map.getContent(), map.getPageable(), map.getTotalElements());
    }

    /**
     * 등록 API는 ADMIN만 허용
     * 반환값은 id
     **/
    @PostMapping
    public ResponseEntity<ResponseResult> create(@RequestBody RequestAnnounceDto data, HttpServletRequest httpServletRequest) {
        String userToken = httpServletRequest.getHeader("X-AUTH-TOKEN");
        String userId = jwtProvider.getUserId(userToken);
        long id = Long.parseLong(userId);
        IdResponseDto announce = announceService.createAnnounce(id, data);
        return ResponseEntity.ok()
                .body(new SuccessResponseResult("등록완료", announce));
    }

    /**
     * id값으로 단건조회 가능.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ResponseResult> findOne(@PathVariable("id") Long id) {
        ResponseAnnounceDto response = announceService.findOne(id);
        return ResponseEntity.ok() //200
                .body(new SuccessResponseResult("성공", response));
    }

    /**
     * 삭제
     * 메시지만? pk값 필요없고, only Message
     */
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<RequestResult> deleteOne(@PathVariable("id") Long id){
//
//
//    }


}
