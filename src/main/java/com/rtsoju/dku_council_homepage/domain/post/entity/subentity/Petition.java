package com.rtsoju.dku_council_homepage.domain.post.entity.subentity;

import com.rtsoju.dku_council_homepage.domain.base.PetitionStatus;
import com.rtsoju.dku_council_homepage.domain.post.entity.Post;

import javax.persistence.*;

@Entity
@DiscriminatorValue("P")
public class Petition extends Post {

    @Enumerated(EnumType.STRING)
    private PetitionStatus status;

}
