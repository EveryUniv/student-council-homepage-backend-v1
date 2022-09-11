package com.rtsoju.dku_council_homepage.domain.post.entity.dto.page;

import com.rtsoju.dku_council_homepage.domain.base.SuggestionStatus;
import com.rtsoju.dku_council_homepage.domain.post.entity.dto.response.FileUrlWithNameDto;
import com.rtsoju.dku_council_homepage.domain.post.entity.subentity.Suggestion;
import lombok.Data;

import java.util.List;

@Data
public class PageSuggestionDto {
    private Long id;
    private String title;
    private String userName;

    private int postHits;

    private int commentCount;
    private SuggestionStatus status;
    private String category;

    public PageSuggestionDto(Suggestion suggestion) {
        this.id = suggestion.getId();
        this.title = suggestion.getTitle();
        this.userName = suggestion.getUser().getName();
        this.postHits = suggestion.getHitCount();
        this.commentCount = suggestion.getComments().size();
        this.status = suggestion.getStatus();
        this.category = suggestion.getCategory();
    }

}
