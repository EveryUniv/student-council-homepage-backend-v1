package com.rtsoju.dku_council_homepage.domain.log.repository;

import com.rtsoju.dku_council_homepage.domain.log.entity.CommentsLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentsLogRepository extends JpaRepository<CommentsLog, Long> {
}
