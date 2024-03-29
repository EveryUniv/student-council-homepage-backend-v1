package com.rtsoju.dku_council_homepage.domain.post.repository;

import com.rtsoju.dku_council_homepage.domain.post.entity.subentity.Announce;
import com.rtsoju.dku_council_homepage.domain.post.entity.subentity.Petition;
import com.rtsoju.dku_council_homepage.domain.user.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


public interface AnnounceRepository extends JpaRepository<Announce, Long> {

    Page<Announce> findAllByTitleContainsOrTextContains(String title, String text, Pageable pageable);
    Page<Announce> findAllByCategory(String category, Pageable pageable);

    Page<Announce> findAllByCategoryAndTitleContainsOrTextContains(String category, String title, String text, Pageable pageable);



}
