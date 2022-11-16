package com.rtsoju.dku_council_homepage.domain.likes.service;

import com.rtsoju.dku_council_homepage.domain.likes.model.Likes;
import com.rtsoju.dku_council_homepage.domain.likes.repository.LikesRepository;
import com.rtsoju.dku_council_homepage.domain.post.entity.Post;
import com.rtsoju.dku_council_homepage.domain.post.repository.PostRepository;
import com.rtsoju.dku_council_homepage.domain.post.service.PostService;
import com.rtsoju.dku_council_homepage.domain.user.model.entity.User;
import com.rtsoju.dku_council_homepage.domain.user.repository.UserRepository;
import com.rtsoju.dku_council_homepage.domain.user.service.UserService;
import com.rtsoju.dku_council_homepage.exception.FindPostWithIdNotFoundException;
import com.rtsoju.dku_council_homepage.exception.FindUserWithIdNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LikesService {
    private final LikesRepository likesRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Transactional
    public void touchLikes(Long userId, Long postId){
        Likes likes = createOrDeleteLikes(userId, postId);
        if(likes != null){
            likesRepository.save(likes);
        }
    }

    public long countLikes(Long postId){
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new FindPostWithIdNotFoundException("해당 id와 일치하는 Post가 없습니다."));
        return likesRepository.countByPost(post);
    }

    private Likes createOrDeleteLikes(Long userId, Long postId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new FindUserWithIdNotFoundException("해당 id와 일치하는 User가 없습니다."));
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new FindPostWithIdNotFoundException("해당 id와 일치하는 Post가 없습니다."));

        Optional<Likes> byUserAndPost = likesRepository.findByUserAndPost(user, post);
        if(byUserAndPost.isPresent()){
            likesRepository.deleteByUserAndPost(user, post);
            return null;
        }
        return new Likes(user, post);
    }

}
