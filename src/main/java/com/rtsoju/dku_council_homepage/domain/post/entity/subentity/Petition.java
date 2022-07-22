package com.rtsoju.dku_council_homepage.domain.post.entity.subentity;

import com.rtsoju.dku_council_homepage.domain.base.PetitionStatus;
import com.rtsoju.dku_council_homepage.domain.post.entity.Post;
import com.rtsoju.dku_council_homepage.domain.user.model.entity.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue("P")
@Getter
public class Petition extends Post {
    public Petition(String title, String text, PetitionStatus status) {
        super(title, text);
        this.status = status;
    }

    public Petition(User user, String title, String text, PetitionStatus status){
        super(user, title, text);
        this.status = status;

    }

    @Enumerated(EnumType.STRING)
    private PetitionStatus status;

}
