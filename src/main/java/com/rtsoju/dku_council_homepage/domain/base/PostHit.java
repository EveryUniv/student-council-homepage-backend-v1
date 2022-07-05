package com.rtsoju.dku_council_homepage.domain.base;

import com.rtsoju.dku_council_homepage.domain.user.entity.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class PostHit extends BaseEntity{

    @Id
    @GeneratedValue
    @Column(name = "post_hit_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
