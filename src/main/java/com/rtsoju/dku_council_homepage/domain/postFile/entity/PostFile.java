package com.rtsoju.dku_council_homepage.domain.postFile.entity;

import com.rtsoju.dku_council_homepage.domain.base.BaseEntity;
import com.rtsoju.dku_council_homepage.domain.post.entity.POST;

import javax.persistence.*;

@Entity(name = "post_file")
public class PostFile extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "post_file_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private POST post;

    @Column(name = "file_url")
    private String url;


}
