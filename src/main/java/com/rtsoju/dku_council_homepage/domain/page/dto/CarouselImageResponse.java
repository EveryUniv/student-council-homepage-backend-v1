package com.rtsoju.dku_council_homepage.domain.page.dto;

import com.rtsoju.dku_council_homepage.common.nhn.service.ObjectStorageService;
import com.rtsoju.dku_council_homepage.domain.page.entity.CarouselImage;
import lombok.Data;

@Data
public class CarouselImageResponse {
    private Long id;
    private String url;

    public CarouselImageResponse(ObjectStorageService s3service, CarouselImage image) {
        this.id = image.getId();
        this.url = s3service.getObjectURL(image.getFileId());
    }
}
