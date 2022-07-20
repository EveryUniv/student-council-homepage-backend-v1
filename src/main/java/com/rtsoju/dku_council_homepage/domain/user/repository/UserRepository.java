package com.rtsoju.dku_council_homepage.domain.user.repository;

import com.rtsoju.dku_council_homepage.domain.user.model.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByClassId(String classId);

    @Override
    @EntityGraph(attributePaths = {"roles"})
    Optional<User> findById(Long userId);

    User save(User user);
}
