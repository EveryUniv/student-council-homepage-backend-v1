package com.rtsoju.dku_council_homepage.domain.post.repository;

import com.rtsoju.dku_council_homepage.domain.post.entity.subentity.Announce;
import com.rtsoju.dku_council_homepage.domain.post.entity.subentity.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface NewsRepository extends JpaRepository<News, Long> {
//    @Query("select n from News n order by n.createDate desc")


    Page<News> findAllByTitleContainsOrTextContains(String title, String text, Pageable pageable);

    List<News> findTop5ByOrderByCreateDateDesc();


//    @Query("select distinct n from News n join fetch n.fileList join fetch n.comments join fetch n.user where n.id =:id")
//    Optional<News> findById(@Param("id") Long id);


    @EntityGraph(attributePaths = {"user"})
    Optional<News> findById(Long id);
}
