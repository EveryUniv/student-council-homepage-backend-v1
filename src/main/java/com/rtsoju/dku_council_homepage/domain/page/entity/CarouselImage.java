package com.rtsoju.dku_council_homepage.domain.page.entity;


import com.rtsoju.dku_council_homepage.domain.base.BaseEntity;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * CarouselImage Entity. CRUD할 때 편하게 하기위해 id추가
 */
@Entity
@Getter
public class CarouselImage extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "carousel_id")
    private Long id;

    @Column(name = "carousel_file_id")
    private String fileId;

    private String redirectUrl;

    public CarouselImage() {
    }

    public CarouselImage(String fileId) {
        this.fileId = fileId;
    }

    public CarouselImage(String fileId, String url) {
        this.fileId = fileId;
        this.redirectUrl = url;
    }
}
