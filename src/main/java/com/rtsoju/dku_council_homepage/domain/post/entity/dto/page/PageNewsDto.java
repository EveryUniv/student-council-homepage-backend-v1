package com.rtsoju.dku_council_homepage.domain.post.entity.dto.page;

import com.rtsoju.dku_council_homepage.domain.post.entity.dto.response.FileUrlWithNameDto;
import com.rtsoju.dku_council_homepage.domain.post.entity.subentity.News;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

//pk값 넣기. -> d-day는 나중에..
@Data
public class PageNewsDto {
    private Long id;
    private String title;
    private String createDate;
    private List<FileUrlWithNameDto> fileList;

    private int postHits;
    public PageNewsDto(News news){
        this.id = news.getId();
        this.title = news.getTitle();
        this.createDate = news.convertDate(news.getCreateDate());
        this.fileList = news.getFiles();
        this.postHits = news.getHitCount();
    }

}
