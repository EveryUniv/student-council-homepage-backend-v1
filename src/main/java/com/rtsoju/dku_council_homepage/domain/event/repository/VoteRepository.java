package com.rtsoju.dku_council_homepage.domain.event.repository;

import com.rtsoju.dku_council_homepage.domain.event.model.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VoteRepository extends JpaRepository<Vote, Long> {
    List<Vote> findByUserId(long userId);
}
