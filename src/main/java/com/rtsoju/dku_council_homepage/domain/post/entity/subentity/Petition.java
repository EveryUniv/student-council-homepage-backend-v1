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
    public Petition(User user, String title, String text){
        super(user, title, text);
        this.status = PetitionStatus.진행중;
    }

    @Enumerated(EnumType.STRING)
    private PetitionStatus status;

}
