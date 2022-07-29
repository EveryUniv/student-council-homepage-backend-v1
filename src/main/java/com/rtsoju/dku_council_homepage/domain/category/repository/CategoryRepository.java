package com.rtsoju.dku_council_homepage.domain.category.repository;

import com.rtsoju.dku_council_homepage.domain.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByCategory(String category);
}
