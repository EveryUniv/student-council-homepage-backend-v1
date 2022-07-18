package com.rtsoju.dku_council_homepage.domain.post.entity.dto;

import com.rtsoju.dku_council_homepage.domain.base.PetitionStatus;
import com.rtsoju.dku_council_homepage.domain.post.entity.subentity.Petition;
import com.rtsoju.dku_council_homepage.domain.user.model.entity.User;
import lombok.Data;

@Data
public class PetitionDto {
    private String  petitionStatus;
    private String title;
    //우선 작성자도 나중에 연동.. test하기 어려움.
//    private User user;

    //추천 수랑 댓글 수는 나중에 연동..
//    private int hitCount;
//    private int commentCount;

    public PetitionDto(Petition petition){
        this.petitionStatus = petition.getStatus().toString();
        this.title = petition.getTitle();
//        this.user = petition.getUser();
//        this.hitCount = petition.getPostHits().size();
//        this.commentCount = petition.getComments().size();
    }
}
