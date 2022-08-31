package com.rtsoju.dku_council_homepage.domain.post.entity.dto.response;

import com.rtsoju.dku_council_homepage.domain.post.entity.PostFile;
import com.rtsoju.dku_council_homepage.domain.post.entity.subentity.Announce;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class ResponseAnnounceDto {
    private Long id;
    private String category;
    private String title;
    private String text;
    private List<FileUrlWithNameDto> fileList;
    private String createDate;
    private int postHits;

    public ResponseAnnounceDto(Announce announce){
        this.id = announce.getId();
        this.category = announce.getCategory();
        this.title = announce.getTitle();
        this.text = announce.getText();
        this.fileList = announce.getFiles();
        this.createDate = announce.convertDate(announce.getCreateDate());
        this.postHits = announce.getHitCount();
    }

}
