package com.rtsoju.dku_council_homepage.domain.post.service;

import com.rtsoju.dku_council_homepage.domain.post.entity.Comment;
import com.rtsoju.dku_council_homepage.domain.post.entity.Post;
import com.rtsoju.dku_council_homepage.domain.post.entity.dto.request.CommentRequestDto;
import com.rtsoju.dku_council_homepage.domain.post.repository.CommentRepository;
import com.rtsoju.dku_council_homepage.domain.post.repository.PostRepository;
import com.rtsoju.dku_council_homepage.domain.user.model.entity.User;
import com.rtsoju.dku_council_homepage.domain.user.repository.UserRepository;
import com.rtsoju.dku_council_homepage.exception.FindPostWithIdNotFoundException;
import com.rtsoju.dku_council_homepage.exception.FindUserWithIdNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public Comment createComment(Long postId, CommentRequestDto dto, Long userId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new FindPostWithIdNotFoundException("해당 id와 일치하는 post가 없습니다."));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new FindUserWithIdNotFoundException("해당 id와 일치하는 User가 없습니다."));

        Comment comment = new Comment(post, user, dto.getText());
        return commentRepository.save(comment);

    }
}
