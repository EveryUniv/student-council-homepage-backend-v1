package com.rtsoju.dku_council_homepage.domain.post.entity.request;

import lombok.Data;

@Data
public class RequestAnnounceDto {
    private String title;
    private String text;
    private String fileUrl;
}
