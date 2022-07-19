package com.rtsoju.dku_council_homepage.domain.post.repository;

import com.rtsoju.dku_council_homepage.domain.post.entity.subentity.Announce;
import com.rtsoju.dku_council_homepage.domain.post.entity.subentity.Petition;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AnnounceRepository extends JpaRepository<Announce, Long> {
    @Override
    @EntityGraph(attributePaths = {"comments","postHits"})
    List<Announce> findAll(); // 연관관계 맵핑된거 다 가져옴..

}
