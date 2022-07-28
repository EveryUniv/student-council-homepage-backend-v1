package com.rtsoju.dku_council_homepage.domain.post.entity.dto.response;

import com.rtsoju.dku_council_homepage.domain.post.entity.subentity.Suggestion;
import lombok.Data;

import java.util.List;

@Data
public class ResponseSuggestionDto {
    private Long id;
    private String title;
    private String text;
    private List<String> fileList;
    private String createDate;
    private int postHits;

    public ResponseSuggestionDto(Suggestion suggestion) {
        this.id = suggestion.getId();
        this.title = suggestion.getTitle();
        this.text = suggestion.getText();
        this.fileList = suggestion.convertUrl();
        this.createDate = suggestion.convertDate(suggestion.getCreateDate());
        this.postHits = suggestion.getHitCount();
    }
}
