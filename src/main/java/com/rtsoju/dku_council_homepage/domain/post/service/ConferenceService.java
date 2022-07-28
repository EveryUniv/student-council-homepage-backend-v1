package com.rtsoju.dku_council_homepage.domain.post.service;

import com.rtsoju.dku_council_homepage.common.jwt.JwtProvider;
import com.rtsoju.dku_council_homepage.common.nhn.service.FileUploadService;
import com.rtsoju.dku_council_homepage.domain.page.dto.PostSummary;
import com.rtsoju.dku_council_homepage.domain.post.entity.Post;
import com.rtsoju.dku_council_homepage.domain.post.entity.PostFile;
import com.rtsoju.dku_council_homepage.domain.post.entity.dto.page.PageConferenceDto;
import com.rtsoju.dku_council_homepage.domain.post.entity.dto.request.RequestConferenceDto;
import com.rtsoju.dku_council_homepage.domain.post.entity.dto.response.IdResponseDto;
import com.rtsoju.dku_council_homepage.domain.post.entity.dto.response.ResponseConferenceDto;
import com.rtsoju.dku_council_homepage.domain.post.entity.subentity.Conference;
import com.rtsoju.dku_council_homepage.domain.post.repository.ConferenceRepository;
import com.rtsoju.dku_council_homepage.domain.user.model.entity.User;
import com.rtsoju.dku_council_homepage.domain.user.repository.UserRepository;
import com.rtsoju.dku_council_homepage.exception.FindPostWithIdNotFoundException;
import com.rtsoju.dku_council_homepage.exception.FindUserWithIdNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class ConferenceService {
    private final ConferenceRepository conferenceRepository;
    private final JwtProvider jwtProvider;
    private final UserRepository userRepository;
    private final FileUploadService fileUploadService;

    public Page<PageConferenceDto> conferencePage(String title, String text, Pageable pageable) {
        Page<Conference> page;
        if(title == null){
            page = conferenceRepository.findAll(pageable);
        }else{
            page = conferenceRepository.findALlByTitleContainsOrTextContains(title, text, pageable);
        }
        return page.map(PageConferenceDto::new);
    }

    //최근 5개 페이지 가져오기
    public List<PostSummary> postPage() {
        List<Conference> conferenceList = conferenceRepository.findTop5ByOrderByCreateDateDesc();
        return conferenceList.stream().map(Post::summarize).collect(Collectors.toList());
    }

    @Transactional
    public IdResponseDto createConference(RequestConferenceDto dto, Long userId) {
        Conference conference = dto.toEntity();

        User user = userRepository.findById(userId).orElseThrow(FindUserWithIdNotFoundException::new);
        conference.putUser(user);

        ArrayList<PostFile> postFiles = fileUploadService.uploadFiles(dto.getFiles(), "conference");
        conference.putFiles(postFiles);
        Conference save = conferenceRepository.save(conference);
        return new IdResponseDto(save.getId());
    }



    @Transactional
    public void deleteConference(Long postId){
        Conference conference = conferenceRepository.findById(postId).orElseThrow(FindPostWithIdNotFoundException::new);
        List<PostFile> fileList = conference.getFileList();
        fileUploadService.deletePostFiles(fileList);
        conferenceRepository.delete(conference);

    }

}
