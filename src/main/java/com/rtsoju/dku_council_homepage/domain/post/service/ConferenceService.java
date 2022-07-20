package com.rtsoju.dku_council_homepage.domain.post.service;

import com.rtsoju.dku_council_homepage.common.jwt.JwtProvider;
import com.rtsoju.dku_council_homepage.domain.page.dto.PostSummary;
import com.rtsoju.dku_council_homepage.domain.post.entity.Post;
import com.rtsoju.dku_council_homepage.domain.post.entity.dto.ConferenceDto;
import com.rtsoju.dku_council_homepage.domain.post.entity.dto.NewsDto;
import com.rtsoju.dku_council_homepage.domain.post.entity.request.RequestAnnounceDto;
import com.rtsoju.dku_council_homepage.domain.post.entity.request.RequestConferenceDto;
import com.rtsoju.dku_council_homepage.domain.post.entity.subentity.Announce;
import com.rtsoju.dku_council_homepage.domain.post.entity.subentity.Conference;
import com.rtsoju.dku_council_homepage.domain.post.entity.subentity.News;
import com.rtsoju.dku_council_homepage.domain.post.repository.ConferenceRepository;
import com.rtsoju.dku_council_homepage.domain.user.model.entity.User;
import com.rtsoju.dku_council_homepage.domain.user.model.entity.UserRole;
import com.rtsoju.dku_council_homepage.domain.user.repository.UserRepository;
import com.rtsoju.dku_council_homepage.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ConferenceService {
    private final ConferenceRepository conferenceRepository;
    private final JwtProvider jwtProvider;
    private final UserRepository userRepository;
    public Page<ConferenceDto> conferencePage(Pageable pageable){
        Page<Conference> page = conferenceRepository.findAll(pageable);
        return page.map(ConferenceDto::new);
    }

    //최근 5개 페이지 가져오기
    public List<PostSummary> postPage(){
        List<Conference> conferenceList = conferenceRepository.findTop5ByOrderByCreateDateDesc();
        return conferenceList.stream().map(Post::summarize).collect(Collectors.toList());
    }

    public void createConference(String userToken, RequestConferenceDto data) {
        if(userToken==null){
            throw new BadRequestException("로그인이 필요한 기능입니다.");
        }
        String userId = jwtProvider.getUserId(userToken);
        long id = Long.parseLong(userId);
        Optional<User> user = userRepository.findById(id);
        //토큰에서 user를 찾아오는 과정에 user가 없을 수가 없음..
//        if(user.isEmpty()){
//            throw new BadRequestException("존재하지 않는 회원입니다.");
//        }
        List<UserRole> roles = user.get().getRoles();
        List<String> authroities = roles.stream().map(r -> r.getRole()).collect(Collectors.toList());
        if(authroities.contains("ROLE_ADMIN")){
            conferenceRepository.save(new Conference(user.get(), data.getTitle(), data.getFileUrl()));
        }else{
            throw new BadRequestException("관리자만 등록할 수 있습니다.");
        }
    }

}
