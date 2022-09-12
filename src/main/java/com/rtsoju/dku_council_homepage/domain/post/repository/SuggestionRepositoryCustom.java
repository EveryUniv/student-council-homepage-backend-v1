package com.rtsoju.dku_council_homepage.domain.post.repository;

import com.rtsoju.dku_council_homepage.domain.base.SuggestionStatus;
import com.rtsoju.dku_council_homepage.domain.post.entity.subentity.Suggestion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SuggestionRepositoryCustom {
    Page<Suggestion> findSuggestionPage(String query, String category, Pageable pageable);
}
