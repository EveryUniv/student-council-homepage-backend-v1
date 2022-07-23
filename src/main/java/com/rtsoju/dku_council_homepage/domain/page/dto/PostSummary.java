package com.rtsoju.dku_council_homepage.domain.page.dto;

import lombok.Data;

/**
 * Post를 간단하게 모델링한 클래스. Post는 타이틀, 컨텐츠 등 많은 정보를 포함하지만
 * 이 클래스는 제목과 그 글로 갈 수 있는 URL을 제공한다.
 */
@Data
public class PostSummary {
    private Long id;
    private String title;

    public PostSummary(Long id, String title) {
        this.id = id;
        this.title = title;
    }
}
