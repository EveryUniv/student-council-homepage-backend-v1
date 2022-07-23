package com.rtsoju.dku_council_homepage.domain.post.entity.dto.response;

import com.rtsoju.dku_council_homepage.domain.post.entity.subentity.Announce;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class ResponseAnnounceDto {
    private Long id;
    private String title;
    private String text;
    private String fileUrl;
    private String createDate;

    public ResponseAnnounceDto(Announce announce){
        this.id = announce.getId();
        this.title = announce.getTitle();
        this.text = announce.getText();
        this.fileUrl = announce.getFileUrl();
        this.createDate = ConvertDate(announce.getCreateDate());
    }

    private String ConvertDate(LocalDateTime time){
        DateTimeFormatter patten = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return time.format(patten);
    }
}
