package com.rtsoju.dku_council_homepage.domain.post.entity.dto.page;

import com.rtsoju.dku_council_homepage.domain.base.PetitionStatus;
import com.rtsoju.dku_council_homepage.domain.post.entity.subentity.Petition;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PagePetitionDto {
    private Long id;
    private PetitionStatus petitionStatus;
    private String title;
    private String userName;
    private Long commentCount;
    private int postHits;
    private boolean isBlind;


//    public PagePetitionDto(Petition petition){
//        this.id = petition.getId();
//        this.petitionStatus = petition.getStatus();
//        this.title = petition.getTitle();
//        this.userName = petition.getUser().getName();
//        this.commentCount = petition.getComments().size();
//        this.postHits = petition.getHitCount();
//        this.isBlind = petition.isBlind();
//    }
}
