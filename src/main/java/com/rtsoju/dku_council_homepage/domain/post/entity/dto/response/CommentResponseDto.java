package com.rtsoju.dku_council_homepage.domain.post.entity.dto.response;

import com.rtsoju.dku_council_homepage.domain.post.entity.Comment;
import com.rtsoju.dku_council_homepage.domain.post.entity.Post;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentResponseDto {
    private String name;
    private LocalDateTime time;
    private String text;

    private boolean isMine;

    public CommentResponseDto(Long userId, Comment comment) {
        this.name = comment.getUser().getName();
        this.time = comment.getCreateDate();
        this.text = comment.getText();
        this.isMine = comment.getUser().getId().equals(userId);
    }
}
