package com.rtsoju.dku_council_homepage.domain.post.repository;

import com.rtsoju.dku_council_homepage.domain.post.entity.subentity.Suggestion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SuggestionRepository extends JpaRepository<Suggestion, Long> {
    Page<Suggestion> findAllByTitleContainsOrTextContains(String title, String text, Pageable pageable);
}
