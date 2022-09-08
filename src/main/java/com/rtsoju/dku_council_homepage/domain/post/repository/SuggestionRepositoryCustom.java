package com.rtsoju.dku_council_homepage.domain.post.repository;

import com.rtsoju.dku_council_homepage.domain.base.SuggestionStatus;
import com.rtsoju.dku_council_homepage.domain.post.entity.subentity.Suggestion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SuggestionRepositoryCustom {

    public Page<Suggestion> findSuggestionPage(String query, SuggestionStatus status, String category, Pageable pageable);
}
