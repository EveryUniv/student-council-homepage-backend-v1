package com.rtsoju.dku_council_homepage.domain.post.entity.dto.page;

import com.rtsoju.dku_council_homepage.domain.post.entity.subentity.News;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

//pk값 넣기. -> d-day는 나중에..
@Data
public class PageNewsDto {
    private Long id;
    private String title;
    private String  createDate;
    private String fileUrl;
    public PageNewsDto(News news){
        this.id = news.getId();
        this.title = news.getTitle();
        this.createDate = ConvertDate(news.getCreateDate());
    }

    private String ConvertDate(LocalDateTime time){
        DateTimeFormatter patten = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return time.format(patten);
    }
}
