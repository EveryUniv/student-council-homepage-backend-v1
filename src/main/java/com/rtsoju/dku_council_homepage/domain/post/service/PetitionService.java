package com.rtsoju.dku_council_homepage.domain.post.service;

import com.rtsoju.dku_council_homepage.domain.base.PetitionStatus;
import com.rtsoju.dku_council_homepage.domain.page.dto.PostSummary;
import com.rtsoju.dku_council_homepage.domain.post.entity.Post;
import com.rtsoju.dku_council_homepage.domain.post.entity.dto.PetitionDto;
import com.rtsoju.dku_council_homepage.domain.post.entity.subentity.Petition;
import com.rtsoju.dku_council_homepage.domain.post.repository.PetitionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class PetitionService {
    private final PetitionRepository petitionRepository;

    public Page<PetitionDto> petitionPage(Pageable pageable){
        Page<Petition> page = petitionRepository.findAll(pageable);
        return page.map(PetitionDto::new);
    }

    public Page<PetitionDto> petitionPageOnStatus(String query,  Pageable pageable){
        PetitionStatus status;
        if(query.equals("A")){
            status = PetitionStatus.진행중;
            System.out.println("==========================================");
        }else if(query.equals("B")){
            status = PetitionStatus.완료;
            System.out.println("==========================================");

        }else if(query.equals("C")){
            status = PetitionStatus.취소;
            System.out.println("==========================================");

        }else{
            status = null;
            System.out.println("==========================================");

        }
        Page<Petition> page = petitionRepository.findAllByStatus(status, pageable);
        return page.map(PetitionDto::new);
    }

    public List<PostSummary> postPage(){
        List<Petition> petitionList = petitionRepository.findTop5ByOrderByCreateDateDesc();
        return petitionList.stream().map(Post::summarize).collect(Collectors.toList());
    }


}
