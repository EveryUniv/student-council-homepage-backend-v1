package com.rtsoju.dku_council_homepage.domain.post.entity.dto.page;

import com.rtsoju.dku_council_homepage.domain.post.entity.subentity.Conference;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Data
public class PageConferenceDto {

    private Long id;
    private int round;
    private LocalDate date;
    private String createDate;
    private String title;
    private List<String> files;
    private int postHits;

    public PageConferenceDto(Conference conference){
        this.id = conference.getId();
        this.round = conference.getRound();
        this.date = conference.getDate();
        this.createDate = conference.convertDate(conference.getCreateDate());
        this.title = conference.getTitle();
        this.files = conference.convertUrl();
        this.postHits = conference.getHitCount();
    }

}
