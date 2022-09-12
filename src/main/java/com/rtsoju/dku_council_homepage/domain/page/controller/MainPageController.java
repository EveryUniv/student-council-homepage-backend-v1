package com.rtsoju.dku_council_homepage.domain.page.controller;

import com.rtsoju.dku_council_homepage.common.Messages;
import com.rtsoju.dku_council_homepage.common.ResponseResult;
import com.rtsoju.dku_council_homepage.common.SuccessResponseResult;
import com.rtsoju.dku_council_homepage.domain.page.dto.CarouselImageRequestDto;
import com.rtsoju.dku_council_homepage.domain.page.dto.CarouselImageResponse;
import com.rtsoju.dku_council_homepage.domain.page.dto.MainPageResponse;
import com.rtsoju.dku_council_homepage.domain.page.service.MainPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MainPageController {
    private final MainPageService service;

    @GetMapping("/main")
    public ResponseEntity<ResponseResult> index() {
        MainPageResponse response = new MainPageResponse();
        response.setCarousels(service.getCarouselImages());
        response.setPopularPetitions(service.getPopularPetitions());
        response.setRecentNews(service.getRecentNews());
        response.setRecentConferences(service.getRecentConferences());
        return ResponseEntity.ok().body(new SuccessResponseResult(response));
    }

    @GetMapping("/carousel")
    public ResponseEntity<List<CarouselImageResponse>> getCarouselImages(){
        List<CarouselImageResponse> response = service.getCarouselImages();

        return ResponseEntity.ok(response);
    }

    @PostMapping("/carousel")
    public ResponseEntity<ResponseResult> uploadCarouselImage(@ModelAttribute CarouselImageRequestDto dto) throws IOException {
        MultipartFile imageFile = dto.getImageFile();
        if (imageFile == null || imageFile.isEmpty()) {
            throw new IllegalArgumentException(Messages.ERROR_ARGUMENT_NOT_SPECIFIED.getMessage());
        }

        String fileName = imageFile.getOriginalFilename();
        if (fileName != null) {
            if (!isImageFile(fileName)) {
                throw new IllegalArgumentException(Messages.ERROR_NOT_SUPPORTED_IMAGE_FILE.getMessage());
            }
        } else {
            throw new IllegalArgumentException(Messages.ERROR_ARGUMENT_NOT_SPECIFIED.getMessage());
        }

        service.addCarouselImage(dto);
        return ResponseEntity.ok().body(new SuccessResponseResult());
    }

    @DeleteMapping("/carousel/{id}")
    public ResponseEntity<ResponseResult> deleteCarouselImage(@PathVariable("id") Long carouselId) {
        service.deleteCarouselImage(carouselId);
        return ResponseEntity.ok().body(new SuccessResponseResult());
    }

    private static boolean isImageFile(String name) {
        return name.endsWith(".jpg") ||
                name.endsWith(".jpeg") ||
                name.endsWith(".png") ||
                name.endsWith(".gif") ||
                name.endsWith(".svg") ||
                name.endsWith(".webp");
    }
}
