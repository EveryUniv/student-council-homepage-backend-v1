package com.rtsoju.dku_council_homepage.domain.post.controller;

import com.rtsoju.dku_council_homepage.common.SuccessResponseResult;
import com.rtsoju.dku_council_homepage.common.jwt.JwtProvider;
import com.rtsoju.dku_council_homepage.domain.likes.service.LikesService;
import com.rtsoju.dku_council_homepage.domain.post.entity.Comment;
import com.rtsoju.dku_council_homepage.domain.post.entity.dto.request.CommentRequestDto;
import com.rtsoju.dku_council_homepage.domain.post.entity.subentity.Announce;
import com.rtsoju.dku_council_homepage.domain.post.repository.ConferenceRepository;
import com.rtsoju.dku_council_homepage.domain.post.repository.NewsRepository;
import com.rtsoju.dku_council_homepage.domain.post.repository.PetitionRepository;
import com.rtsoju.dku_council_homepage.domain.post.repository.RuleRepository;
import com.rtsoju.dku_council_homepage.domain.post.service.AnnounceService;
import com.rtsoju.dku_council_homepage.domain.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PostController {

    private final JwtProvider jwtProvider;
    private final PostService postService;
    private final LikesService likesService;

    @PostMapping("/posts/{postId}")
    public ResponseEntity<SuccessResponseResult> createComment(
            @PathVariable("postId") Long postId,
            @Valid @RequestBody CommentRequestDto dto,
            HttpServletRequest request) {
        String token = jwtProvider.getTokenInHttpServletRequest(request);
        Long userId = Long.parseLong(jwtProvider.getUserId(token));

        Comment comment = postService.createComment(postId, dto, userId);

        return ResponseEntity
                .created(URI.create("/api/posts/" + postId + "/" + comment.getId()))
                .body(new SuccessResponseResult("댓글 생성 성공!"));

    }

    @PostMapping("/posts/likes/{postId}")
    public ResponseEntity<SuccessResponseResult> touchLike(
            @PathVariable("postId") Long postId,
            HttpServletRequest request){
        String token = jwtProvider.getTokenInHttpServletRequest(request);
        long userId = Long.parseLong(jwtProvider.getUserId(token));
        boolean isCreate = likesService.touchLikes(userId, postId);
        return ResponseEntity.ok()
                .body(new SuccessResponseResult("isCreate", isCreate));
    }

}
