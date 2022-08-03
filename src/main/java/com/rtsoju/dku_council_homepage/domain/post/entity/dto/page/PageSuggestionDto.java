package com.rtsoju.dku_council_homepage.domain.post.entity.dto.page;

import com.rtsoju.dku_council_homepage.domain.post.entity.subentity.Suggestion;
import lombok.Data;

import java.util.List;

@Data
public class PageSuggestionDto {
    private Long id;
    private String title;
    private String createDate;

    private List<String> fileList;
    private int postHits;

    public PageSuggestionDto(Suggestion suggestion) {
        this.id = suggestion.getId();
        this.title = suggestion.getTitle();
        this.fileList = suggestion.convertUrl();
        this.createDate = suggestion.convertDate(suggestion.getCreateDate());
        this.postHits = suggestion.getHitCount();
    }

}
