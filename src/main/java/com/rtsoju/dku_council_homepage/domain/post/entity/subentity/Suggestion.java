package com.rtsoju.dku_council_homepage.domain.post.entity.subentity;

import com.rtsoju.dku_council_homepage.domain.base.SuggestionStatus;
import com.rtsoju.dku_council_homepage.domain.post.entity.Post;
import com.rtsoju.dku_council_homepage.domain.post.entity.PostFile;
import com.rtsoju.dku_council_homepage.domain.post.entity.dto.request.RequestPostDto;
import com.rtsoju.dku_council_homepage.domain.post.entity.dto.request.RequestSuggestionDto;
import com.rtsoju.dku_council_homepage.domain.user.model.entity.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Lob;
import java.util.ArrayList;

@Entity
@DiscriminatorValue("S")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Suggestion extends Post {

    private String category;

    private SuggestionStatus status;

    @Lob
    // 건의게시판 응답
    private String answer;
    public Suggestion(User user, RequestSuggestionDto data, ArrayList<PostFile> files) {
        super(user, data, files);
        this.category = data.getCategory();
        this.status = SuggestionStatus.진행중;
    }

    public void answerSuggestion(String answer) {
        this.answer = answer;
        this.status = SuggestionStatus.답변완료;
    }
}
