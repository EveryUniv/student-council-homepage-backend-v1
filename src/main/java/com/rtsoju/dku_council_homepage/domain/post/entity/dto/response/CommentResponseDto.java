package com.rtsoju.dku_council_homepage.domain.post.entity.dto.response;

import com.rtsoju.dku_council_homepage.domain.post.entity.Comment;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentResponseDto {
    private Long id;
    private String status;
    private String name;
    private LocalDateTime time;
    private String text;
    private boolean isMine;
    private int anonymouseNum = 0;

    public CommentResponseDto(Long userId, Comment comment) {
        this.id = comment.getId();
        this.name = comment.getUser().getName();
        this.time = comment.getCreateDate();
        this.status = comment.getStatus().toString();
        this.text = comment.getText();
        this.isMine = comment.getUser().getId().equals(userId);
    }
}
