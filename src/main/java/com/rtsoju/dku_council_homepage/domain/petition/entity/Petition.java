package com.rtsoju.dku_council_homepage.domain.petition.entity;

import com.rtsoju.dku_council_homepage.domain.base.PetitionStatus;
import com.rtsoju.dku_council_homepage.domain.post.entity.POST;

import javax.persistence.*;

@Entity
@DiscriminatorColumn("P")
public class Petition extends POST {

    @Enumerated(EnumType.STRING)
    private PetitionStatus status;

}
