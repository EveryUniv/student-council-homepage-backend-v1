package com.rtsoju.dku_council_homepage.domain.post.controller;

import com.rtsoju.dku_council_homepage.common.ResponseResult;
import com.rtsoju.dku_council_homepage.common.SuccessResponseResult;
import com.rtsoju.dku_council_homepage.common.jwt.JwtProvider;
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
    public PageRes<PageSuggestionDto> list(@RequestParam(value = "query", required = false) String query, Pageable pageable) {
        Page<PageSuggestionDto> map = suggestionService.suggestionPageByTitleAndText(query, query, pageable);
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
    public ResponseEntity<ResponseResult> findOne(@PathVariable("id") Long id) {
        ResponseSuggestionDto response = suggestionService.findOne(id);
        return ResponseEntity.ok() //200
                .body(new SuccessResponseResult("성공", response));
    }

    /**
     * 삭제
     * 메시지만? pk값 필요없고, only Message
     * 주의!! 공지사항은 ADMIN계정만 삭제할 수 있음!!!
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseResult> deleteOne(@PathVariable("id") Long id) {
        suggestionService.deleteOne(id);
        return ResponseEntity.ok()
                .body(new SuccessResponseResult("삭제완료"));
    }

    /**
     * 답변 등록
     * ONLY ADMIN
     */
    @PostMapping("/comment/admin/{postId}")
    public ResponseEntity<SuccessResponseResult> answerSuggestion(@PathVariable("postId") Long postId, @RequestBody CommentRequestDto dto) {
        suggestionService.answerSuggestion(postId, dto);

        return ResponseEntity.ok()
                .body(new SuccessResponseResult("답변을 등록하였습니다."));
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


}
