package com.rtsoju.dku_council_homepage.domain.post.entity.dto.page;

import com.rtsoju.dku_council_homepage.domain.post.entity.PostFile;
import com.rtsoju.dku_council_homepage.domain.post.entity.subentity.Announce;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
public class PageAnnounceDto {

    private Long id;
    private String title;
    private String createDate;
    private List<String> fileList;
    private int postHits;

    public PageAnnounceDto(Announce announce) {
        this.id = announce.getId();
        this.title = announce.getTitle();
        this.fileList = announce.convertUrl();
        this.createDate = announce.convertDate(announce.getCreateDate());
        this.postHits = announce.getHitCount();
    }

}
