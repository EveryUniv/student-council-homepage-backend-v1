package com.rtsoju.dku_council_homepage.domain.post.repository;

import com.rtsoju.dku_council_homepage.domain.post.entity.dto.NewsDto;
import com.rtsoju.dku_council_homepage.domain.post.entity.subentity.Conference;
import com.rtsoju.dku_council_homepage.domain.post.entity.subentity.News;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface NewsRepository extends JpaRepository<News, Long> {
//    @Query("select n from News n order by n.createDate desc")

    @Override
    @EntityGraph(attributePaths = {"comments","postHits"})
    List<News> findAll(); // 연관관계 맵핑된거 다 가져옴..


    List<News> findTop5ByOrderByCreateDateDesc();


}
