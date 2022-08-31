package com.rtsoju.dku_council_homepage.domain.post.entity.dto.response;

import com.rtsoju.dku_council_homepage.domain.post.entity.subentity.Rule;
import lombok.Data;

import java.util.List;

@Data
public class ResponseRuleDto {
    private Long id;
    private String title;
    private String text;
    private List<FileUrlWithNameDto> fileList;
    private String createDate;
    private int postHits;
    public ResponseRuleDto(Rule rule) {
        this.id = rule.getId();
        this.title = rule.getTitle();
        this.text = rule.getText();
        this.fileList = rule.getFiles();
        this.createDate = rule.convertDate(rule.getCreateDate());
        this.postHits = rule.getHitCount();
    }
}
