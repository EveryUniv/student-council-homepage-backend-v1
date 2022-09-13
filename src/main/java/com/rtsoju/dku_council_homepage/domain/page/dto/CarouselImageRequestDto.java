package com.rtsoju.dku_council_homepage.domain.page.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class CarouselImageRequestDto {
    private MultipartFile imageFile;
    private String redirectUrl;
}
