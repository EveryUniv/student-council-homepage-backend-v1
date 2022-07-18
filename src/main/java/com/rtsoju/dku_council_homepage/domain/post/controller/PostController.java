package com.rtsoju.dku_council_homepage.domain.post.controller;

import com.rtsoju.dku_council_homepage.domain.post.entity.subentity.Announce;
import com.rtsoju.dku_council_homepage.domain.post.repository.ConferenceRepository;
import com.rtsoju.dku_council_homepage.domain.post.repository.NewsRepository;
import com.rtsoju.dku_council_homepage.domain.post.repository.PetitionRepository;
import com.rtsoju.dku_council_homepage.domain.post.repository.RuleRepository;
import com.rtsoju.dku_council_homepage.domain.post.service.AnnounceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PostController {

}
