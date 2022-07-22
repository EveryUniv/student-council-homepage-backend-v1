package com.rtsoju.dku_council_homepage.domain.post.service;

import com.rtsoju.dku_council_homepage.domain.base.PetitionStatus;
import com.rtsoju.dku_council_homepage.domain.page.dto.PostSummary;
import com.rtsoju.dku_council_homepage.domain.post.entity.Post;
import com.rtsoju.dku_council_homepage.domain.post.entity.dto.PetitionDto;
import com.rtsoju.dku_council_homepage.domain.post.entity.dto.request.RequestPetitionDto;
import com.rtsoju.dku_council_homepage.domain.post.entity.dto.response.IdResponseDto;
import com.rtsoju.dku_council_homepage.domain.post.entity.subentity.Petition;
import com.rtsoju.dku_council_homepage.domain.post.repository.PetitionRepository;
import com.rtsoju.dku_council_homepage.domain.user.model.entity.User;
import com.rtsoju.dku_council_homepage.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Id;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class PetitionService {
    private final PetitionRepository petitionRepository;
    private final UserRepository userRepository;

    public Page<PetitionDto> petitionPage(Pageable pageable){
        Page<Petition> page = petitionRepository.findAll(pageable);
        return page.map(PetitionDto::new);
    }

    public Page<PetitionDto> petitionPageOnStatus(String query,  Pageable pageable){
        PetitionStatus status;
        if(query.equals("진행중")){
            status = PetitionStatus.진행중;
        }else if(query.equals("완료")){
            status = PetitionStatus.완료;
        }else if(query.equals("취소")){
            status = PetitionStatus.취소;
        }else{
            status = null;
        }
        Page<Petition> page = petitionRepository.findAllByStatus(status, pageable);
        return page.map(PetitionDto::new);
    }


    public List<PostSummary> postPage(){
        List<Petition> petitionList = petitionRepository.findTop5ByOrderByCreateDateDesc();
        return petitionList.stream().map(Post::summarize).collect(Collectors.toList());
    }


    @Transactional
    public IdResponseDto createPetition(long id, RequestPetitionDto data) {
        Optional<User> user = userRepository.findById(id);
        Petition petition = new Petition(user.get(), data.getTitle(), data.getText(), PetitionStatus.진행중);
        Petition save = petitionRepository.save(petition);
        return new IdResponseDto(save.getId());
    }
}
