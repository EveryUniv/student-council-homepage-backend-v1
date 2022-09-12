package com.rtsoju.dku_council_homepage.domain.post.service;

import com.rtsoju.dku_council_homepage.common.nhn.service.FileUploadService;
import com.rtsoju.dku_council_homepage.domain.base.SuggestionStatus;
import com.rtsoju.dku_council_homepage.domain.log.entity.CommentsLog;
import com.rtsoju.dku_council_homepage.domain.log.repository.CommentsLogRepository;
import com.rtsoju.dku_council_homepage.domain.post.entity.Comment;
import com.rtsoju.dku_council_homepage.domain.post.entity.PostFile;
import com.rtsoju.dku_council_homepage.domain.post.entity.dto.page.PageSuggestionDto;
import com.rtsoju.dku_council_homepage.domain.post.entity.dto.request.CommentRequestDto;
import com.rtsoju.dku_council_homepage.domain.post.entity.dto.request.RequestSuggestionDto;
import com.rtsoju.dku_council_homepage.domain.post.entity.dto.response.IdResponseDto;
import com.rtsoju.dku_council_homepage.domain.post.entity.dto.response.ResponseSuggestionDto;
import com.rtsoju.dku_council_homepage.domain.post.entity.subentity.Suggestion;
import com.rtsoju.dku_council_homepage.domain.post.repository.CommentRepository;
import com.rtsoju.dku_council_homepage.domain.post.repository.SuggestionRepository;
import com.rtsoju.dku_council_homepage.domain.user.model.entity.User;
import com.rtsoju.dku_council_homepage.domain.user.repository.UserRepository;
import com.rtsoju.dku_council_homepage.exception.NotFoundCommentException;
import com.rtsoju.dku_council_homepage.exception.FindPostWithIdNotFoundException;
import com.rtsoju.dku_council_homepage.exception.FindUserWithIdNotFoundException;
import com.rtsoju.dku_council_homepage.exception.NotMatchWriterException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class SuggestionService {
    private final SuggestionRepository suggestionRepository;
    private final UserRepository userRepository;
    private final FileUploadService fileUploadService;
    private final CommentRepository commentRepository;
    private final CommentsLogRepository commentsLogRepository;

    public Page<PageSuggestionDto> suggestionPageByTitleAndText(String query, String category, Pageable pageable) {
        Page<Suggestion> page;
        page = suggestionRepository.findSuggestionPage(query, category, pageable);
        return page.map(PageSuggestionDto::new);
    }

    @Transactional
    public IdResponseDto createSuggestion(Long userId, RequestSuggestionDto data) {
        User user = userRepository.findById(userId).orElseThrow(FindUserWithIdNotFoundException::new);
        ArrayList<PostFile> postFiles = fileUploadService.uploadFiles(data.getFiles(), "suggestion");
        Suggestion suggestion = new Suggestion(user, data, postFiles);
        Suggestion save = suggestionRepository.save(suggestion);
        return new IdResponseDto(save.getId());
    }


    @Transactional
    public ResponseSuggestionDto findOne(Long userId, Long postId) {
        Suggestion suggestion = suggestionRepository.findById(postId).orElseThrow(FindPostWithIdNotFoundException::new);
        suggestion.plusHits();
        return new ResponseSuggestionDto(userId, suggestion);
    }

    @Transactional
    public Comment createComment(Long postId, Long userId, CommentRequestDto dto) {
        Suggestion suggestion = suggestionRepository.findById(postId).orElseThrow(FindPostWithIdNotFoundException::new);
        User user = userRepository.findById(userId).orElseThrow(FindUserWithIdNotFoundException::new);
        Comment comment = new Comment(suggestion, user, dto.getText());
        return commentRepository.save(comment);
    }

    @Transactional
    public void updateComment(Long commentId, Long userId, CommentRequestDto dto) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(NotFoundCommentException::new);
        if(!userId.equals(comment.getUser().getId())) throw new FindUserWithIdNotFoundException();
        CommentsLog commentsLog = new CommentsLog(comment);
        commentsLogRepository.save(commentsLog);
        comment.updateText(dto.getText());
        return;
    }
    @Transactional
    public void deleteComment(Long commentId, Long userId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(NotFoundCommentException::new);
        if(!userId.equals(comment.getUser().getId())) throw new FindUserWithIdNotFoundException();
        comment.deleteComment();
        return;
    }
    @Transactional
    public void deleteCommentByAdmin(Long commentId, Long userId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(NotFoundCommentException::new);
        comment.deleteCommentByAdmin();
        return;
    }

    @Transactional
    public void deleteSuggestion(Long postId, Long userId) {
        Suggestion suggestion = suggestionRepository.findById(postId).orElseThrow(FindPostWithIdNotFoundException::new);
        User user = userRepository.findById(userId).orElseThrow(FindUserWithIdNotFoundException::new);
        if (!user.equals(suggestion.getUser())) {
            throw new NotMatchWriterException();
        }

        suggestion.deletePost();
        return;
    }

    @Transactional
    public void deleteSuggestionByAdmin(Long postId) {
        Suggestion suggestion = suggestionRepository.findById(postId).orElseThrow(FindPostWithIdNotFoundException::new);
        suggestion.deletePostByAdmin();
    }
}
