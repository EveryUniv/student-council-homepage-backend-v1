package com.rtsoju.dku_council_homepage.domain.post.entity.dto.response;

import com.rtsoju.dku_council_homepage.domain.post.entity.Comment;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentResponseDto {
    private String name;
    private LocalDateTime time;
    private String text;

    public CommentResponseDto(Comment comment) {
        this.name = comment.getUser().getName();
        this.time = comment.getCreateDate();
        this.text = comment.getText();
    }
}
