package com.rtsoju.dku_council_homepage.domain.post.service;

import com.rtsoju.dku_council_homepage.common.nhn.service.FileUploadService;
import com.rtsoju.dku_council_homepage.domain.post.entity.PostFile;
import com.rtsoju.dku_council_homepage.domain.post.entity.dto.page.PageSuggestionDto;
import com.rtsoju.dku_council_homepage.domain.post.entity.dto.request.RequestPostDto;
import com.rtsoju.dku_council_homepage.domain.post.entity.dto.response.IdResponseDto;
import com.rtsoju.dku_council_homepage.domain.post.entity.dto.response.ResponseSuggestionDto;
import com.rtsoju.dku_council_homepage.domain.post.entity.subentity.Suggestion;
import com.rtsoju.dku_council_homepage.domain.post.repository.SuggestionRepository;
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
public class SuggestionService {
    private final SuggestionRepository suggestionRepository;
    private final UserRepository userRepository;
    private final FileUploadService fileUploadService;

    public Page<PageSuggestionDto> suggestionPageByTitleAndText(String title, String text, Pageable pageable) {
        Page<Suggestion> page;
        if (title == null) {
            page = suggestionRepository.findAll(pageable);
        } else {
            page = suggestionRepository.findAllByTitleContainsOrTextContains(title, text, pageable);
        }
        return page.map(PageSuggestionDto::new);
    }

    @Transactional
    public IdResponseDto createSuggestion(Long userId, RequestPostDto data) {
        User user = userRepository.findById(userId).orElseThrow(FindUserWithIdNotFoundException::new);
        ArrayList<PostFile> postFiles = fileUploadService.uploadFiles(data.getFiles(), "suggestion");
        Suggestion suggestion = new Suggestion(user, data, postFiles);
        Suggestion save = suggestionRepository.save(suggestion);
        return new IdResponseDto(save.getId());
    }


    public ResponseSuggestionDto findOne(Long id) {
        Suggestion suggestion = suggestionRepository.findById(id).orElseThrow(FindUserWithIdNotFoundException::new);
        return new ResponseSuggestionDto(suggestion);
    }

    @Transactional
    public void deleteOne(Long id) {
        suggestionRepository.deleteById(id);
    }


}
