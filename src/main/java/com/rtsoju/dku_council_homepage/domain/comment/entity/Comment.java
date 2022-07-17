package com.rtsoju.dku_council_homepage.domain.comment.entity;

import com.rtsoju.dku_council_homepage.domain.base.BaseEntity;
import com.rtsoju.dku_council_homepage.domain.post.entity.Post;
import com.rtsoju.dku_council_homepage.domain.user.model.entity.User;

import javax.persistence.*;

@Entity
public class Comment extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "comment_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;

    @Column(length = 100)
    private String text;

}
