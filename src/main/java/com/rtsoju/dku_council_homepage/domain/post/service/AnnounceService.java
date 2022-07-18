package com.rtsoju.dku_council_homepage.domain.post.service;

import com.rtsoju.dku_council_homepage.domain.post.entity.dto.AnnounceDto;
import com.rtsoju.dku_council_homepage.domain.post.entity.subentity.Announce;
import com.rtsoju.dku_council_homepage.domain.post.repository.AnnounceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AnnounceService {
    private final AnnounceRepository announceRepository;

    public Page<AnnounceDto> announcePage(Pageable pageable){
        Page<Announce> page = announceRepository.findAll(pageable);
        return page.map(AnnounceDto::new);
    }
}
