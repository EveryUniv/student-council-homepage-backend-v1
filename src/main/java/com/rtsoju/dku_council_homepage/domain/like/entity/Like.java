package com.rtsoju.dku_council_homepage.domain.like.entity;

import com.rtsoju.dku_council_homepage.domain.base.BaseEntity;
import com.rtsoju.dku_council_homepage.domain.post.entity.POST;
import com.rtsoju.dku_council_homepage.domain.user.entity.USER;

import javax.persistence.*;

@Entity
public class Like extends BaseEntity {
    @Id @GeneratedValue
    @Column(name= "like_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private POST post;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private USER user;

}
