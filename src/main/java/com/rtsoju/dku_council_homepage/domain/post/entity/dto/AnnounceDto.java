package com.rtsoju.dku_council_homepage.domain.post.entity.dto;

import com.rtsoju.dku_council_homepage.domain.post.entity.subentity.Announce;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class AnnounceDto {

    private Long id;
    private String title;
    private String createDate;
    private String fileUrl;
    public AnnounceDto(Announce announce){
        this.id = announce.getId();
        this.title = announce.getTitle();
        this.createDate = ConvertDate(announce.getCreateDate());
        this.fileUrl = announce.getFileUrl();
    }

    private String ConvertDate(LocalDateTime time){
        DateTimeFormatter patten = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return time.format(patten);
    }

}
