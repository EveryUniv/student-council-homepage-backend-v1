package com.rtsoju.dku_council_homepage.domain.post.service;

import com.rtsoju.dku_council_homepage.domain.post.entity.dto.page.PageAnnounceDto;
import com.rtsoju.dku_council_homepage.domain.post.entity.dto.request.RequestAnnounceDto;
import com.rtsoju.dku_council_homepage.domain.post.entity.dto.response.IdResponseDto;
import com.rtsoju.dku_council_homepage.domain.post.entity.dto.response.ResponseAnnounceDto;
import com.rtsoju.dku_council_homepage.domain.post.entity.subentity.Announce;
import com.rtsoju.dku_council_homepage.domain.post.repository.AnnounceRepository;
import com.rtsoju.dku_council_homepage.domain.user.model.entity.User;
import com.rtsoju.dku_council_homepage.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class AnnounceService {
    private final AnnounceRepository announceRepository;
    private final UserRepository userRepository;

    public Page<PageAnnounceDto> announcePage(Pageable pageable){
        Page<Announce> page = announceRepository.findAll(pageable);
        return page.map(PageAnnounceDto::new);
    }

    @Transactional
    public IdResponseDto createAnnounce(Long userId, RequestAnnounceDto data) {
        Optional<User> user = userRepository.findById(userId);
        Announce announce = new Announce(user.get(), data.getTitle(), data.getText(), data.getFileUrl());
        Announce save = announceRepository.save(announce);
        return new IdResponseDto(save.getId());
    }


    public ResponseAnnounceDto findOne(Long id) {
        Optional<Announce> announce = announceRepository.findById(id);
        //취소 됐을 때, 어떻게 처리할 것인지.. 삭제를 표기한다면 예외처리 필요함
        ResponseAnnounceDto responseAnnounceDto = new ResponseAnnounceDto(announce.get());
        return responseAnnounceDto;
    }

//    public ResponseAnnounceDto
}
