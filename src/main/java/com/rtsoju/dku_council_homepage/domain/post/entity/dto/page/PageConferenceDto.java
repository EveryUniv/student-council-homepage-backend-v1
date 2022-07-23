package com.rtsoju.dku_council_homepage.domain.post.entity.dto.page;

import com.rtsoju.dku_council_homepage.domain.post.entity.subentity.Conference;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class PageConferenceDto {

    private Long id;
    private int round;
    private LocalDate date;
    private String createDate;
    private String title;
    private String fileUrl;
    public PageConferenceDto(Conference conference){
        this.id = conference.getId();
        this.round = conference.getRound();
        this.date = conference.getDate();
        this.createDate = ConvertDate(conference.getCreateDate());
        this.title = conference.getTitle();
        this.fileUrl = conference.getFileUrl();
    }

    private String ConvertDate(LocalDateTime time){
        DateTimeFormatter patten = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return time.format(patten);
    }

}
