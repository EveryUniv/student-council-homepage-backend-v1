package com.rtsoju.dku_council_homepage.domain.post.entity.subentity;

import com.rtsoju.dku_council_homepage.domain.post.entity.Post;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue("A")
public class Announce extends Post {
    public Announce(String title, String text) {
        super(title, text);
    }
}
