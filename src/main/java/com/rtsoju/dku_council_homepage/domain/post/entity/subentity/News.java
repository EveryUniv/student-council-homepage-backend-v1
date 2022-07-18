package com.rtsoju.dku_council_homepage.domain.post.entity.subentity;

import com.rtsoju.dku_council_homepage.domain.comment.entity.Comment;
import com.rtsoju.dku_council_homepage.domain.post.entity.Post;
import com.rtsoju.dku_council_homepage.domain.posthit.entity.PostHit;
import com.rtsoju.dku_council_homepage.domain.user.model.entity.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.List;

@Entity
@DiscriminatorValue("N")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class News extends Post {
    public News(String title, String text) {
        super(title, text);
    }
}
