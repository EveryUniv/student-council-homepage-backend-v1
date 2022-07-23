package com.rtsoju.dku_council_homepage.domain.post.entity.dto.page;

import com.rtsoju.dku_council_homepage.domain.post.entity.subentity.Announce;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class PageAnnounceDto {

    private Long id;
    private String title;
    private String createDate;
    private String fileUrl;
    public PageAnnounceDto(Announce announce){
        this.id = announce.getId();
        this.title = announce.getTitle();
        this.createDate = announce.ConvertDate(announce.getCreateDate());
        this.fileUrl = announce.getFileUrl();
    }

}
