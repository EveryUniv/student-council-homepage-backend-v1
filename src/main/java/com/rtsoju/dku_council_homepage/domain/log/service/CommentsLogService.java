package com.rtsoju.dku_council_homepage.domain.log.service;

import com.rtsoju.dku_council_homepage.domain.log.repository.CommentsLogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class CommentsLogService {
    private final CommentsLogRepository commentsLogRepository;
}
