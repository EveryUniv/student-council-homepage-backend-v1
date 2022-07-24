package com.rtsoju.dku_council_homepage.domain.post.entity.subentity;

import com.rtsoju.dku_council_homepage.domain.post.entity.Post;
import com.rtsoju.dku_council_homepage.domain.post.entity.PostFile;
import com.rtsoju.dku_council_homepage.domain.post.entity.dto.request.RequestAnnounceDto;
import com.rtsoju.dku_council_homepage.domain.user.model.entity.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.ArrayList;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue("A")
public class Announce extends Post {
    public Announce(String title, String text) {
        super(title, text);
    }

    public Announce(User user, RequestAnnounceDto data, ArrayList<PostFile> files){
        super(user, data, files);
    }

}
