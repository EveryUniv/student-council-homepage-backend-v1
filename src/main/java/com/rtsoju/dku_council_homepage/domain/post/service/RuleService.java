package com.rtsoju.dku_council_homepage.domain.post.service;

import com.rtsoju.dku_council_homepage.domain.post.entity.dto.page.PageRuleDto;
import com.rtsoju.dku_council_homepage.domain.post.entity.subentity.Rule;
import com.rtsoju.dku_council_homepage.domain.post.repository.RuleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Slf4j
@Transactional(readOnly = true)
public class RuleService {
    private final RuleRepository ruleRepository;

    public Page<PageRuleDto> rulePage(Pageable pageable){
        Page<Rule> page = ruleRepository.findAll(pageable);
        return page.map(PageRuleDto::new);
    }

    public Page<PageRuleDto> rulePageByTitleOrText(String title, String text, Pageable pageable) {
        Page<Rule> page;
        if(title == null){
            page = ruleRepository.findAll(pageable);
        }else{
            page = ruleRepository.findAllByTitleContainsOrTextContains(title,text,pageable);
        }
        return page.map(PageRuleDto::new);
    }
}
