package com.rtsoju.dku_council_homepage.domain.post.entity.dto.response;

import com.rtsoju.dku_council_homepage.domain.post.entity.dto.page.PageCommentDto;
import com.rtsoju.dku_council_homepage.domain.post.entity.subentity.News;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class ResponseNewsDto {
    private String title;
    private String text;
    private List<FileUrlWithNameDto> files = new ArrayList<>();
    private List<PageCommentDto> commentList = new ArrayList<>();
    private String writer;

    public ResponseNewsDto(News news) {
        this.writer = news.getUser().getName();
        this.title = news.getTitle();
        this.text = news.getText();
        this.commentList = news.getComments().stream().map(PageCommentDto::new).collect(Collectors.toList());
        this.files = news.getFiles();
    }


}
