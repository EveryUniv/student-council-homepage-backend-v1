package com.rtsoju.dku_council_homepage.domain.post.repository;

import com.rtsoju.dku_council_homepage.domain.post.entity.subentity.Rule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RuleRepository extends JpaRepository<Rule, Long> {
}
