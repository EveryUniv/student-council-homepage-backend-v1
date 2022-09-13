package com.rtsoju.dku_council_homepage.domain.post.controller;

import com.rtsoju.dku_council_homepage.common.ResponseResult;
import com.rtsoju.dku_council_homepage.common.SuccessResponseResult;
import com.rtsoju.dku_council_homepage.common.jwt.JwtProvider;
import com.rtsoju.dku_council_homepage.domain.base.SuggestionStatus;
import com.rtsoju.dku_council_homepage.domain.post.entity.Comment;
import com.rtsoju.dku_council_homepage.domain.post.entity.dto.page.PageRes;
import com.rtsoju.dku_council_homepage.domain.post.entity.dto.page.PageSuggestionDto;
import com.rtsoju.dku_council_homepage.domain.post.entity.dto.request.CommentRequestDto;
import com.rtsoju.dku_council_homepage.domain.post.entity.dto.request.RequestPostDto;
import com.rtsoju.dku_council_homepage.domain.post.entity.dto.request.RequestSuggestionDto;
import com.rtsoju.dku_council_homepage.domain.post.entity.dto.response.IdResponseDto;
import com.rtsoju.dku_council_homepage.domain.post.entity.dto.response.ResponseSuggestionDto;
import com.rtsoju.dku_council_homepage.domain.post.service.SuggestionService;
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
@RequestMapping("/api/suggestion")
public class SuggestionController {
    private final SuggestionService suggestionService;
    private final JwtProvider jwtProvider;

    /**
     * 목록조회 (keyWord로 조회하기)추가하기!
     * api/suggestion 호출시 uri param ?page, size, sort, q(query) custom 가능!
     */
    @GetMapping
    public PageRes<PageSuggestionDto> list(@RequestParam(value = "query", required = false) String query,
                                           @RequestParam(value = "category", required = false) String category,
                                           Pageable pageable) {
        Page<PageSuggestionDto> map = suggestionService.suggestionPageByTitleAndText(query, category, pageable);
        return new PageRes<>(map.getContent(), map.getPageable(), map.getTotalElements());
    }

    /**
     * 단건 등록
     *
     * @param data    : title, text, files, category
     * @param request : header
     * @return :suggestion PK
     */
    @PostMapping
    public ResponseEntity<ResponseResult> create(@Valid @ModelAttribute RequestSuggestionDto data, HttpServletRequest request) {
        String token = jwtProvider.getTokenInHttpServletRequest(request);
        Long userId = Long.parseLong(jwtProvider.getUserId(token));
        IdResponseDto suggestion = suggestionService.createSuggestion(userId, data);
        return ResponseEntity.ok()
                .body(new SuccessResponseResult("등록완료", suggestion));
    }

    /**
     * id값으로 단건조회 가능.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ResponseResult> findOne(HttpServletRequest request, @PathVariable("id") Long postId) {
        String token = jwtProvider.getTokenInHttpServletRequest(request);
        Long userId = Long.parseLong(jwtProvider.getUserId(token));
        ResponseSuggestionDto response = suggestionService.findOne(userId, postId);
        return ResponseEntity.ok() //200
                .body(new SuccessResponseResult("성공", response));
    }

    @PostMapping("/comment/{postId}")
    public ResponseEntity<SuccessResponseResult> makeCommentSuggestion(@PathVariable("postId") Long postId, @RequestBody CommentRequestDto dto, HttpServletRequest request) {
        String token = jwtProvider.getTokenInHttpServletRequest(request);
        Long userId = Long.parseLong(jwtProvider.getUserId(token));
        Comment comment = suggestionService.createComment(postId, userId, dto);
        return ResponseEntity
                .created(URI.create("/api/suggestion/comments/" + postId + "/" + comment.getId()))
                .body(new SuccessResponseResult("댓글을 등록하였습니다."));
    }

    @PatchMapping("/comment/{commentId}")
    public ResponseEntity<SuccessResponseResult> updateCommentSuggestion(@PathVariable("commentId") Long commentId, @RequestBody CommentRequestDto dto, HttpServletRequest request) {
        String token = jwtProvider.getTokenInHttpServletRequest(request);
        Long userId = Long.parseLong(jwtProvider.getUserId(token));
        suggestionService.updateComment(commentId, userId, dto);
        return ResponseEntity.ok()
                .body(new SuccessResponseResult("수정완료"));
    }

    @DeleteMapping("/comment/{commentId}")
    public ResponseEntity<SuccessResponseResult> deleteCommentSuggestion(@PathVariable("commentId") Long commentId, HttpServletRequest request) {
        String token = jwtProvider.getTokenInHttpServletRequest(request);
        Long userId = Long.parseLong(jwtProvider.getUserId(token));
        suggestionService.deleteComment(commentId, userId);
        return ResponseEntity.ok()
                .body(new SuccessResponseResult("삭제완료"));
    }

    @DeleteMapping("/comment/admin/{commentId}")
    public ResponseEntity<SuccessResponseResult> deleteCommentSuggestionByAdmin(@PathVariable("commentId") Long commentId, HttpServletRequest request) {
        String token = jwtProvider.getTokenInHttpServletRequest(request);
        Long userId = Long.parseLong(jwtProvider.getUserId(token));
        suggestionService.deleteCommentByAdmin(commentId, userId);
        return ResponseEntity.ok()
                .body(new SuccessResponseResult("중지완료"));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<SuccessResponseResult> deleteSuggestion(@PathVariable("id") Long postId, HttpServletRequest request) {
        String token = jwtProvider.getTokenInHttpServletRequest(request);
        Long userId = Long.parseLong(jwtProvider.getUserId(token));
        suggestionService.deleteSuggestion(postId, userId);

        return ResponseEntity.ok()
                .body(new SuccessResponseResult("삭제 완료"));
    }

    @DeleteMapping("/admin/{id}")
    public ResponseEntity<SuccessResponseResult> deleteSuggestion(@PathVariable("id") Long postId) {
        suggestionService.deleteSuggestionByAdmin(postId);

        return ResponseEntity.ok()
                .body(new SuccessResponseResult("중지완료"));
    }
}
