package com.rtsoju.dku_council_homepage.domain.post.repository;

import com.rtsoju.dku_council_homepage.domain.post.entity.subentity.News;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsRepository extends JpaRepository<News, Long> {
}
