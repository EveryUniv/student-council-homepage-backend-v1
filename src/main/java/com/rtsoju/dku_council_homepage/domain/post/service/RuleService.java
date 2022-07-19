package com.rtsoju.dku_council_homepage.domain.post.service;

import com.rtsoju.dku_council_homepage.domain.post.entity.dto.RuleDto;
import com.rtsoju.dku_council_homepage.domain.post.entity.subentity.Rule;
import com.rtsoju.dku_council_homepage.domain.post.repository.PostRepository;
import com.rtsoju.dku_council_homepage.domain.post.repository.RuleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class RuleService {
    private final RuleRepository ruleRepository;
    private final PostRepository postRepository;

    public Page<RuleDto> rulePage(Pageable pageable){
        Page<Rule> page = ruleRepository.findAll(pageable);
        return page.map(RuleDto::new);
    }
}
