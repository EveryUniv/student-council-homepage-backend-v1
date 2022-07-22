package com.rtsoju.dku_council_homepage.domain.post.entity.dto;

import com.rtsoju.dku_council_homepage.domain.post.entity.subentity.Conference;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class ConferenceDto {
    private int round;
    private LocalDate date;
    private String createDate;
    private String title;
    private String fileUrl;
    public ConferenceDto(Conference conference){
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
