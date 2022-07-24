package com.rtsoju.dku_council_homepage.domain.post.entity.dto.page;

import com.rtsoju.dku_council_homepage.domain.post.entity.Comment;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
@Data
public class PageCommentDto {
    private String major;
    private String createDate;
    private String text;
    public PageCommentDto(Comment comment){
        this.major = comment.getUser().getMajor().getDepartment().toString();
        this.createDate = comment.ConvertDate(comment.getCreateDate());
        this.text = comment.getText();
    }
}
