package com.rtsoju.dku_council_homepage.domain.post.repository;

import com.rtsoju.dku_council_homepage.domain.post.entity.subentity.Conference;
import com.rtsoju.dku_council_homepage.domain.post.entity.subentity.Petition;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConferenceRepository extends JpaRepository<Conference, Long> {
    @Override
    @EntityGraph(attributePaths = {"comments","postHits"})
    List<Conference> findAll(); // 연관관계 맵핑된거 다 가져옴..
    List<Conference> findTop5ByOrderByCreateDateDesc();


}
