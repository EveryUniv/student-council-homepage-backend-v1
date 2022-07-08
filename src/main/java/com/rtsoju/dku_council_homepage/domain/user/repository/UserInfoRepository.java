package com.rtsoju.dku_council_homepage.domain.user.repository;

import com.rtsoju.dku_council_homepage.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserInfoRepository extends JpaRepository<User, Long> {
    Optional<User> findByClassId(String classId);

    Optional<User> findById(Long userId);
}
