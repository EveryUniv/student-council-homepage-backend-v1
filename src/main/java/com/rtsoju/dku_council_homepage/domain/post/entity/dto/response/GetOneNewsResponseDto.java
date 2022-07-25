package com.rtsoju.dku_council_homepage.domain.post.entity.dto.response;

import com.rtsoju.dku_council_homepage.domain.post.entity.Comment;
import com.rtsoju.dku_council_homepage.domain.post.entity.PostFile;
import com.rtsoju.dku_council_homepage.domain.post.entity.subentity.News;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
public class GetOneNewsResponseDto {
    private String title;
    private String text;
    private List<String> files = new ArrayList<>();
    private List<Comment> commentList = new ArrayList<>();
    private String writer;

    public GetOneNewsResponseDto(News news) {
        this.writer = news.getUser().getName();
        this.title = news.getTitle();
        this.text = news.getText();
        this.commentList = news.getComments();
        this.files = news.convertUrl();
    }


}
