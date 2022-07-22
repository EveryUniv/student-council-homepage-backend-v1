package com.rtsoju.dku_council_homepage.domain.post.entity.dto;

import com.rtsoju.dku_council_homepage.domain.base.PetitionStatus;
import com.rtsoju.dku_council_homepage.domain.post.entity.subentity.Petition;
import com.rtsoju.dku_council_homepage.domain.user.model.entity.User;
import lombok.Data;

@Data
public class PetitionDto {
    private Long id;
    private String  petitionStatus;
    private String title;
    private String userName;

    private int hitCount;
    private int commentCount;

    public PetitionDto(Petition petition){
        this.id = petition.getId();
        this.petitionStatus = petition.getStatus().toString();
        this.title = petition.getTitle();
        this.userName = petition.getUser().getName();
        this.hitCount = petition.getPostHits().size();
        this.commentCount = petition.getComments().size();
    }
}
