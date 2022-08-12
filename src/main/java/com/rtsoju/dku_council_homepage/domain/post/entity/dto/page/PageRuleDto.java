package com.rtsoju.dku_council_homepage.domain.post.entity.dto.page;

import com.rtsoju.dku_council_homepage.domain.post.entity.subentity.Rule;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Data
public class PageRuleDto {
    private Long id;
    private String title;
    private String userName;
    private String createDate;
    private List<String> fileList;
    private int postHits;

    public PageRuleDto(Rule rule){
        this.id = rule.getId();
        this.title = rule.getTitle();
        this.userName = rule.getUser().getName();
        this.fileList = rule.convertUrl();
        this.createDate = rule.convertDate(rule.getCreateDate());
        this.postHits = rule.getHitCount();
    }
}
