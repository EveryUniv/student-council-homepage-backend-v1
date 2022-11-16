package com.rtsoju.dku_council_homepage.domain.likes.repository;

import com.rtsoju.dku_council_homepage.domain.likes.model.Likes;
import com.rtsoju.dku_council_homepage.domain.post.entity.Post;
import com.rtsoju.dku_council_homepage.domain.user.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikesRepository extends JpaRepository<Likes, Long> {

    void deleteByUserAndPost(User user, Post post);

    Optional<Likes> findByUserAndPost(User user, Post post);
    Long countByPost(Post post);
}
