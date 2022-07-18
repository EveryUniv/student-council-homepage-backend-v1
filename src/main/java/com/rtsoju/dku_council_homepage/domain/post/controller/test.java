package com.rtsoju.dku_council_homepage.domain.post.controller;


import com.rtsoju.dku_council_homepage.domain.base.PetitionStatus;
import com.rtsoju.dku_council_homepage.domain.post.entity.Post;
import com.rtsoju.dku_council_homepage.domain.post.entity.subentity.*;
import com.rtsoju.dku_council_homepage.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class test {

    private final PostRepository postRepository;

    @PostConstruct
    public void init(){
        for (int i=0; i<30; i++){
            postRepository.save(new News("News"+i, "News"+i));
            postRepository.save(new Announce("Announce"+i, "Announce"+i));
            postRepository.save(new Conference("Conference"+i, "Conference"+i));
            postRepository.save(new Petition("Petition"+i, "Petition"+i, PetitionStatus.진행중));
            postRepository.save(new Petition("Petition@"+i, "Petition@"+i,PetitionStatus.완료));
            postRepository.save(new Petition("Petition^"+i, "Petition^"+i,PetitionStatus.취소));
            postRepository.save(new Rule("Rule"+i, "Rule"+i));
        }
    }
}
