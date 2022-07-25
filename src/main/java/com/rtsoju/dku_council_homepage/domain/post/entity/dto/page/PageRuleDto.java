package com.rtsoju.dku_council_homepage.domain.post.entity.dto.page;

import com.rtsoju.dku_council_homepage.domain.post.entity.subentity.Rule;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class PageRuleDto {
    private Long id;
    private String title;
    private String userName;
    private int hitCount;
    private String createDate;
    private String fileUrl;

    public PageRuleDto(Rule rule){
        this.id = rule.getId();
        this.title = rule.getTitle();
        this.userName = rule.getUser().getName();
//        this.hitCount = rule.getPostHits().size();
        this.createDate = rule.convertDate(rule.getCreateDate());
    }
}
