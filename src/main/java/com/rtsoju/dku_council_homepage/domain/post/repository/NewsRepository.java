package com.rtsoju.dku_council_homepage.domain.post.repository;

import com.rtsoju.dku_council_homepage.domain.post.entity.subentity.News;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NewsRepository extends JpaRepository<News, Long> {
//    @Query("select n from News n order by n.createDate desc")



    List<News> findTop5ByOrderByCreateDateDesc();


}
