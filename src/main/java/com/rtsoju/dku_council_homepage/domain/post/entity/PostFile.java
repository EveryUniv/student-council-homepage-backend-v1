package com.rtsoju.dku_council_homepage.domain.post.entity;

import com.rtsoju.dku_council_homepage.domain.base.BaseEntity;
import com.rtsoju.dku_council_homepage.domain.post.entity.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "post_file")
@Getter
@NoArgsConstructor
public class PostFile extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "post_file_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @Column(name = "file_url")
    private String url;

    public PostFile(String url) {
        this.url = url;
    }

    public void putPost(Post post) {
        this.post = post;
    }
}
