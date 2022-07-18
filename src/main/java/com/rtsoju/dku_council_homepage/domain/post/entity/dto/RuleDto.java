package com.rtsoju.dku_council_homepage.domain.post.entity.dto;

import com.rtsoju.dku_council_homepage.domain.post.entity.subentity.Rule;
import com.rtsoju.dku_council_homepage.domain.user.model.entity.User;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class RuleDto {
    private String title;
//    private User user;
//    private int hitCount;
    private String createDate;
    private String fileUrl;

    public RuleDto(Rule rule){
        this.title = rule.getTitle();
//        this.user = rule.getUser();
//        this.hitCount = rule.getPostHits().size();
        this.createDate = ConvertDate(rule.getCreateDate());
        this.fileUrl = rule.getFileUrl();
    }

    private String ConvertDate(LocalDateTime time){
        DateTimeFormatter patten = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return time.format(patten);
    }

}
