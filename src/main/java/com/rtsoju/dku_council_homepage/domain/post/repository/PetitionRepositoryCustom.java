package com.rtsoju.dku_council_homepage.domain.post.repository;

import com.rtsoju.dku_council_homepage.domain.base.PetitionStatus;
import com.rtsoju.dku_council_homepage.domain.post.entity.dto.page.PagePetitionDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PetitionRepositoryCustom {
    Page<PagePetitionDto> findPetitionPage(String query, PetitionStatus status, String category, Pageable pageable);
}
