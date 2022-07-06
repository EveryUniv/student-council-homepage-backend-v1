package com.rtsoju.dku_council_homepage.domain.like.entity;

import com.rtsoju.dku_council_homepage.domain.base.BaseEntity;
import com.rtsoju.dku_council_homepage.domain.post.entity.Post;
import com.rtsoju.dku_council_homepage.domain.user.entity.User;


import javax.persistence.*;

@Entity(name = "recommend")
public class Like extends BaseEntity {
    @Id @GeneratedValue
    @Column(name= "like_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
