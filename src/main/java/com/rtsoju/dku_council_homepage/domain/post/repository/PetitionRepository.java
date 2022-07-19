package com.rtsoju.dku_council_homepage.domain.post.repository;

import com.rtsoju.dku_council_homepage.domain.post.entity.subentity.News;
import com.rtsoju.dku_council_homepage.domain.post.entity.subentity.Petition;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PetitionRepository extends JpaRepository<Petition, Long> {
    // 상속 받으면 타입 캐스팅이 되는가?

    @Override
    @EntityGraph(attributePaths = {"comments","postHits"})
    List<Petition> findAll(); // 연관관계 맵핑된거 다 가져옴..
    List<Petition> findTop5ByOrderByCreateDateDesc();
}
