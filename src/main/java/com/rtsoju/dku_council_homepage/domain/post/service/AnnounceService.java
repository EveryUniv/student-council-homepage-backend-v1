package com.rtsoju.dku_council_homepage.domain.post.service;

import com.rtsoju.dku_council_homepage.common.nhn.service.FileUploadService;
import com.rtsoju.dku_council_homepage.domain.post.entity.PostFile;
import com.rtsoju.dku_council_homepage.domain.post.entity.dto.page.PageAnnounceDto;
import com.rtsoju.dku_council_homepage.domain.post.entity.dto.request.RequestAnnounceDto;
import com.rtsoju.dku_council_homepage.domain.post.entity.dto.response.IdResponseDto;
import com.rtsoju.dku_council_homepage.domain.post.entity.dto.response.ResponseAnnounceDto;
import com.rtsoju.dku_council_homepage.domain.post.entity.subentity.Announce;
import com.rtsoju.dku_council_homepage.domain.post.repository.AnnounceRepository;
import com.rtsoju.dku_council_homepage.domain.user.model.entity.User;
import com.rtsoju.dku_council_homepage.domain.user.repository.UserRepository;
import com.rtsoju.dku_council_homepage.exception.FindUserWithIdNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;


@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class AnnounceService {
    private final AnnounceRepository announceRepository;
    private final UserRepository userRepository;
    private final FileUploadService fileUploadService;

    public Page<PageAnnounceDto> announcePageByTitleAndText(String title, String text, Pageable pageable) {
        Page<Announce> page;
        if (title == null) {
            page = announceRepository.findAll(pageable);
        } else {
            page = announceRepository.findAllByTitleContainsOrTextContains(title, text, pageable);
        }
        return page.map(PageAnnounceDto::new);
    }

    @Transactional
    public IdResponseDto createAnnounce(Long userId, RequestAnnounceDto data) {
        User user = userRepository.findById(userId).orElseThrow(FindUserWithIdNotFoundException::new);
        ArrayList<PostFile> postFiles = fileUploadService.uploadFiles(data.getFiles(), "announce");
        Announce announce = new Announce(user, data, postFiles);
        Announce save = announceRepository.save(announce);
        return new IdResponseDto(save.getId());
    }


    public ResponseAnnounceDto findOne(Long id) {
        Announce announce = announceRepository.findById(id).orElseThrow(FindUserWithIdNotFoundException::new);
        return new ResponseAnnounceDto(announce);
    }

    @Transactional
    public void deleteOne(Long id) {
        announceRepository.deleteById(id);
    }


}
