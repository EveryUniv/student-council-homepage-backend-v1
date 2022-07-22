package com.rtsoju.dku_council_homepage.domain.post.repository;

import com.rtsoju.dku_council_homepage.domain.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;


//잠깐 사용하고 지울 예정..
public interface PostRepository extends JpaRepository<Post, Long> {

}
