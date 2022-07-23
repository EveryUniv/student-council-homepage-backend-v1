package com.rtsoju.dku_council_homepage.domain.page.controller;

import com.rtsoju.dku_council_homepage.common.Messages;
import com.rtsoju.dku_council_homepage.common.ResponseResult;
import com.rtsoju.dku_council_homepage.common.SuccessResponseResult;
import com.rtsoju.dku_council_homepage.domain.page.dto.MainPageResponse;
import com.rtsoju.dku_council_homepage.domain.page.service.MainPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MainPageController {
    private final MainPageService service;

    @GetMapping("/main")
    public ResponseEntity<ResponseResult> index() {
        MainPageResponse response = new MainPageResponse();
        response.setCarousels(service.getCarouselImageURLs());
        response.setPopularPetitions(service.getPopularPetitions());
        response.setRecentNews(service.getRecentNews());
        response.setRecentConferences(service.getRecentConferences());
        return ResponseEntity.ok().body(new SuccessResponseResult(response));
    }

    @PostMapping("/carousel")
    public ResponseEntity<ResponseResult> uploadCarouselImage(@RequestBody MultipartFile imageFile) throws IOException {
        if (imageFile.isEmpty()) {
            throw new IllegalArgumentException(Messages.ERROR_ARGUMENT_NOT_SPECIFIED.getMessage());
        }
        service.addCarouselImage(imageFile);
        return ResponseEntity.ok().body(new SuccessResponseResult());
    }

    @DeleteMapping("/carousel/{id}")
    public ResponseEntity<ResponseResult> deleteCarouselImage(@PathVariable("id") Long carouselId) {
        service.deleteCarouselImage(carouselId);
        return ResponseEntity.ok().body(new SuccessResponseResult());
    }
}
