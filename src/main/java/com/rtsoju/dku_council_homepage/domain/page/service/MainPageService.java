package com.rtsoju.dku_council_homepage.domain.page.service;

import com.rtsoju.dku_council_homepage.common.Messages;
import com.rtsoju.dku_council_homepage.common.nhn.service.NHNAuthService;
import com.rtsoju.dku_council_homepage.common.nhn.service.ObjectStorageService;
import com.rtsoju.dku_council_homepage.domain.page.dto.CarouselImageRequestDto;
import com.rtsoju.dku_council_homepage.domain.page.dto.CarouselImageResponse;
import com.rtsoju.dku_council_homepage.domain.page.dto.PetitionSummary;
import com.rtsoju.dku_council_homepage.domain.page.dto.PostSummary;
import com.rtsoju.dku_council_homepage.domain.page.entity.CarouselImage;
import com.rtsoju.dku_council_homepage.domain.page.repository.CarouselImageRepository;
import com.rtsoju.dku_council_homepage.domain.post.service.ConferenceService;
import com.rtsoju.dku_council_homepage.domain.post.service.NewsService;
import com.rtsoju.dku_council_homepage.domain.post.service.PetitionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MainPageService {
    private final NHNAuthService nhnAuthService;
    private final ObjectStorageService s3service;
    private final ConferenceService conferenceService;
    private final NewsService newsService;
    private final PetitionService petitionService;
    private final CarouselImageRepository carouselImageRepository;

    public List<CarouselImageResponse> getCarouselImages() {
        return carouselImageRepository.findAll().stream()
                .map((image) -> new CarouselImageResponse(s3service, image))
                .collect(Collectors.toList());
    }


    @Transactional
    public void addCarouselImage(CarouselImageRequestDto dto) throws IOException {
        MultipartFile file = dto.getImageFile();
        String url = dto.getRedirectUrl();
        String originName = file.getOriginalFilename();
        String ext = originName.substring(originName.lastIndexOf(".") + 1);

        String fileId = "carousel-" + UUID.randomUUID() + "." + ext;
        String token = nhnAuthService.requestToken();
        s3service.uploadObject(token, fileId, file.getInputStream());
        carouselImageRepository.save(new CarouselImage(fileId, url));
    }

    @Transactional
    public void deleteCarouselImage(Long carouselId) {
        String token = nhnAuthService.requestToken();
        Optional<CarouselImage> image = carouselImageRepository.findById(carouselId);
        if (image.isEmpty()) {
            throw new IllegalArgumentException(Messages.ERROR_INVALID_ID.getMessage());
        }
        s3service.deleteObject(token, image.get().getFileId());
        carouselImageRepository.deleteById(carouselId);
    }

    /**
     * 최근 회의록 목록을 가져온다. (5개)
     */
    public List<PostSummary> getRecentConferences() {
        return conferenceService.postPage();
    }

    /**
     * 최근 총학 소식 목록을 가져온다. (5개)
     */
    public List<PostSummary> getRecentNews() {
        return newsService.postPage();
    }

    /**
     * 진행중인 인기청원 목록을 가져온다. (n개) 추후 인기청원 맵핑하면 개선
     * 최근 청원 목록을 가져온다 (5개)
     */
    public List<PetitionSummary> getPopularPetitions() {
        return petitionService.postPage();
    }
}
