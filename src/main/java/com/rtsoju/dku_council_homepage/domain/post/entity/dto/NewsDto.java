package com.rtsoju.dku_council_homepage.domain.post.entity.dto;

import com.rtsoju.dku_council_homepage.domain.post.entity.subentity.News;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class NewsDto {
    private String title;
    private String  createDate;
    private String fileUrl;
    public NewsDto(News news){
        this.title = news.getTitle();
        this.createDate = ConvertDate(news.getCreateDate());
        this.fileUrl = news.getFileUrl();
    }

    private String ConvertDate(LocalDateTime time){
        DateTimeFormatter patten = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return time.format(patten);
    }
}
