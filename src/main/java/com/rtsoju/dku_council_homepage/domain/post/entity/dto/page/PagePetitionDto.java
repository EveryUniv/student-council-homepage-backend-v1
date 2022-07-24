package com.rtsoju.dku_council_homepage.domain.post.entity.dto.page;

import com.rtsoju.dku_council_homepage.domain.post.entity.subentity.Petition;
import lombok.Data;

@Data
public class PagePetitionDto {
    private Long id;
    private String  petitionStatus;
    private String title;
    private String userName;

    private int hitCount;
    private int commentCount;

    public PagePetitionDto(Petition petition){
        this.id = petition.getId();
        this.petitionStatus = petition.getStatus().toString();
        this.title = petition.getTitle();
        this.userName = petition.getUser().getName();
//        this.hitCount = petition.getPostHits().size();
        this.commentCount = petition.getComments().size();
    }
}
