package com.rtsoju.dku_council_homepage.domain.petition.entity;

import com.rtsoju.dku_council_homepage.domain.base.PetitionStatus;
import com.rtsoju.dku_council_homepage.domain.base.Post;

import javax.persistence.*;

@Entity
@DiscriminatorColumn(name = "P")
public class Petition extends Post {

    @Enumerated(EnumType.STRING)
    private PetitionStatus status;

}
