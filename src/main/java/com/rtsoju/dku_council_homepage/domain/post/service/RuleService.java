package com.rtsoju.dku_council_homepage.domain.post.service;

import com.rtsoju.dku_council_homepage.common.nhn.service.FileUploadService;
import com.rtsoju.dku_council_homepage.domain.post.entity.PostFile;
import com.rtsoju.dku_council_homepage.domain.post.entity.dto.page.PageRuleDto;
import com.rtsoju.dku_council_homepage.domain.post.entity.dto.request.RequestRuleDto;
import com.rtsoju.dku_council_homepage.domain.post.entity.dto.response.IdResponseDto;
import com.rtsoju.dku_council_homepage.domain.post.entity.dto.response.ResponseRuleDto;
import com.rtsoju.dku_council_homepage.domain.post.entity.subentity.Rule;
import com.rtsoju.dku_council_homepage.domain.post.repository.RuleRepository;
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

@RequiredArgsConstructor
@Service
@Slf4j
@Transactional(readOnly = true)
public class RuleService {
    private final RuleRepository ruleRepository;
    private final UserRepository userRepository;
    private final FileUploadService fileUploadService;

    public Page<PageRuleDto> rulePage(String title, String text, Pageable pageable) {
        Page<Rule> page;
        if(title == null){
            page = ruleRepository.findAll(pageable);
        }else{
            page = ruleRepository.findAllByTitleContainsOrTextContains(title,text,pageable);
        }
        return page.map(PageRuleDto::new);
    }

    @Transactional
    public IdResponseDto createRule(Long userId, RequestRuleDto data) {
        User user = userRepository.findById(userId).orElseThrow(FindUserWithIdNotFoundException::new);
        ArrayList<PostFile> postFiles = fileUploadService.uploadFiles(data.getFiles(), "rule");
        Rule rule = new Rule(user, data, postFiles);
        Rule save = ruleRepository.save(rule);
        return new IdResponseDto(save.getId());
    }


    public ResponseRuleDto findOne(Long id) {
        Rule rule = ruleRepository.findById(id).orElseThrow(FindUserWithIdNotFoundException::new);
        return new ResponseRuleDto(rule);
    }

    @Transactional
    public void deleteOne(Long id) {
        ruleRepository.deleteById(id);
    }
}
