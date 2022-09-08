package com.rtsoju.dku_council_homepage.domain.post.entity.dto.response;

import com.rtsoju.dku_council_homepage.domain.base.SuggestionStatus;
import com.rtsoju.dku_council_homepage.domain.post.entity.subentity.Suggestion;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class ResponseSuggestionDto {
    private Long id;
    private String title;
    private String text;
    private List<FileUrlWithNameDto> fileList;
    private String createDate;
    private int postHits;

    private SuggestionStatus status;
    private String category;
    private List<CommentResponseDto> commentList;
    private String answer;


    public ResponseSuggestionDto(Suggestion suggestion) {
        this.id = suggestion.getId();
        this.title = suggestion.getTitle();
        this.text = suggestion.getText();
        this.fileList = suggestion.getFiles();
        this.createDate = suggestion.convertDate(suggestion.getCreateDate());
        this.postHits = suggestion.getHitCount();

        this.status = suggestion.getStatus();
        this.category = suggestion.getCategory();
        this.commentList = suggestion.getComments().stream().map(CommentResponseDto::new).collect(Collectors.toList());
        this.answer = suggestion.getAnswer();
    }
}
