package com.rtsoju.dku_council_homepage.domain.post.repository;

import com.rtsoju.dku_council_homepage.domain.post.entity.subentity.Petition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetitionRepository extends JpaRepository<Petition, Long> {
}
