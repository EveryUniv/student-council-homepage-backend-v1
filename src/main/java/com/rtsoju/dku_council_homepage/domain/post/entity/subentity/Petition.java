package com.rtsoju.dku_council_homepage.domain.post.entity.subentity;

import com.rtsoju.dku_council_homepage.domain.base.PetitionStatus;
import com.rtsoju.dku_council_homepage.domain.category.entity.Category;
import com.rtsoju.dku_council_homepage.domain.post.entity.Post;
import com.rtsoju.dku_council_homepage.domain.user.model.entity.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue("P")
@Getter
public class  Petition extends Post {

    private String  category;
    @Enumerated(EnumType.STRING)
    private PetitionStatus status;
    private String adminComment;
    private boolean isBlind;
    public Petition(User user, String title, String text, String category){
        super(user, title, text);
        this.category = category;
        this.status = PetitionStatus.진행중;
    }
    public void UpdateStandBy(){
        this.status = PetitionStatus.답변대기;
    }

    public void createCommentByAdmin(String comment){
        this.adminComment = comment;
        this.status = PetitionStatus.답변완료;
    }

    public void changeBlind(){
        this.isBlind = this.isBlind ? false:true;
    }
}
