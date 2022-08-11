package com.rtsoju.dku_council_homepage.domain.post.controller;

import com.rtsoju.dku_council_homepage.common.ResponseResult;
import com.rtsoju.dku_council_homepage.common.SuccessResponseResult;
import com.rtsoju.dku_council_homepage.common.jwt.JwtProvider;
import com.rtsoju.dku_council_homepage.domain.base.PetitionStatus;
import com.rtsoju.dku_council_homepage.domain.post.entity.Comment;
import com.rtsoju.dku_council_homepage.domain.post.entity.dto.page.PageAnnounceDto;
import com.rtsoju.dku_council_homepage.domain.post.entity.dto.page.PagePetitionDto;
import com.rtsoju.dku_council_homepage.domain.post.entity.dto.page.PageRes;
import com.rtsoju.dku_council_homepage.domain.post.entity.dto.request.CommentRequestDto;
import com.rtsoju.dku_council_homepage.domain.post.entity.dto.request.RequestAnnounceDto;
import com.rtsoju.dku_council_homepage.domain.post.entity.dto.request.RequestPetitionDto;
import com.rtsoju.dku_council_homepage.domain.post.entity.dto.response.IdResponseDto;
import com.rtsoju.dku_council_homepage.domain.post.entity.dto.response.ResponseAnnounceDto;
import com.rtsoju.dku_council_homepage.domain.post.entity.dto.response.ResponsePetitionDto;
import com.rtsoju.dku_council_homepage.domain.post.entity.subentity.Petition;
import com.rtsoju.dku_council_homepage.domain.post.service.PetitionService;
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
@RequestMapping("/api/petition")
public class PetitionController {
    private final PetitionService petitionService;
    private final JwtProvider jwtProvider;

    /**
     * 페이지 반환
     * @param query : 검색 조건
     * @param status : 진행중, 완료, 취소 검색
     * @param pageable : size, sort, page 설정
     * @return : Paging
     */
    @GetMapping
    public PageRes<PagePetitionDto> list(@RequestParam(value = "query", required = false)String query, @RequestParam(value = "status", required = false)String status, @RequestParam(value = "category", required = false)String category, Pageable pageable) {
        Page<PagePetitionDto> map = petitionService.petitionPage(query, status, category, pageable);
        return new PageRes<>(map.getContent(), map.getPageable(), map.getTotalElements());

    }


    /**
     * 단건 등록
     * @param data : title, text
     * @param request : postPK(ID)
     * @return
     */
    @PostMapping
    public ResponseEntity<SuccessResponseResult> create(@Valid @RequestBody RequestPetitionDto data, HttpServletRequest request) {
        String token = jwtProvider.getTokenInHttpServletRequest(request);
        long userId = Long.parseLong(jwtProvider.getUserId(token));
        IdResponseDto petition = petitionService.createPetition(userId, data);
        return ResponseEntity.created(URI.create("/api/petition/"+petition.getId()))
                .body(new SuccessResponseResult("등록 완료", petition));
    }


    /**
     * 단건 조회
     * @param id : Post PK값
     * @return : 해당 Post의 제목, 본문, 상태(진행중, 취소, 완료), 댓글수, 댓글 목록, 생성일, 종료일
     */
    @GetMapping("/{id}")
    public ResponseEntity<ResponseResult> findOne(@PathVariable("id") Long id) {
        ResponsePetitionDto response = petitionService.findOne(id);
        return ResponseEntity.ok() //200
                .body(new SuccessResponseResult(response));
    }

    /**
     * 단건 삭제
     * @param id : Post PK값
     * @return : message 삭제완료
     * 삭제조건,, 사용자는 삭제 못하는 걸로 들었음.. 맞나?
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseResult> deleteOne(@PathVariable("id") Long id){

        petitionService.deleteOne(id);
        return ResponseEntity.ok()
                .body(new SuccessResponseResult(id+"번 petition 삭제완료"));
    }


    @PostMapping("/comment/{id}")
    public ResponseEntity<ResponseResult> createComment(@PathVariable("id")Long id, @RequestBody CommentRequestDto data, HttpServletRequest request){
        String token = jwtProvider.getTokenInHttpServletRequest(request);
        Long userId = Long.parseLong(jwtProvider.getUserId(token));
        petitionService.checkDuplicateCommentByUser(id, userId);
        Comment comment = petitionService.createComment(id, userId, data);
        return ResponseEntity.created(URI.create("/api/petition/"+id+"/"+comment.getId()))
                .body(new SuccessResponseResult("댓글 생성 성공!"));
    }

    /**
     * ONLY_ADMIN만 호출 가능함.
     * @param id
     * @param data
     * @return
     */
    @PostMapping("/comment/admin/{id}")
    public ResponseEntity<ResponseResult> createCommentByAdmin(@PathVariable("id")Long id, @RequestBody CommentRequestDto data){
        IdResponseDto commentByAdmin = petitionService.createCommentByAdmin(id, data);
        return ResponseEntity.created(URI.create("/api/petition/"+id+"/admin/"+commentByAdmin.getId()))
                .body(new SuccessResponseResult("댓글 생성 성공!", commentByAdmin));

    }

    /**
     * ONLY_ADMIN
     * @param id
     * @return
     */
    @PostMapping("/blind/{id}")
    public ResponseEntity<ResponseResult> blindPetitionByAdmin(@PathVariable("id")Long id){
        Petition petition = petitionService.changeBlind(id);
        return ResponseEntity.ok()
                .body(new SuccessResponseResult("상태 변경 완료! blind = " + petition.isBlind()));
    }

}
