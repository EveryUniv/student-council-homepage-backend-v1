package com.rtsoju.dku_council_homepage.domain.posthit.entity;

import com.rtsoju.dku_council_homepage.domain.base.BaseEntity;
import com.rtsoju.dku_council_homepage.domain.post.entity.Post;
import com.rtsoju.dku_council_homepage.domain.user.model.entity.User;

import javax.persistence.*;

@Entity
public class PostHit extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "post_hit_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

}
