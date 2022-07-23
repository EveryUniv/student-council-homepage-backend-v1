//package com.rtsoju.dku_council_homepage.domain.post.controller;
//
//
//import com.rtsoju.dku_council_homepage.domain.base.Major;
//import com.rtsoju.dku_council_homepage.domain.base.PetitionStatus;
//import com.rtsoju.dku_council_homepage.domain.base.Register;
//import com.rtsoju.dku_council_homepage.domain.post.entity.Comment;
//import com.rtsoju.dku_council_homepage.domain.post.entity.Post;
//import com.rtsoju.dku_council_homepage.domain.post.entity.subentity.*;
//import com.rtsoju.dku_council_homepage.domain.post.repository.AnnounceRepository;
//import com.rtsoju.dku_council_homepage.domain.post.repository.CommentRepository;
//import com.rtsoju.dku_council_homepage.domain.post.repository.PetitionRepository;
//import com.rtsoju.dku_council_homepage.domain.post.repository.PostRepository;
//import com.rtsoju.dku_council_homepage.domain.user.model.entity.User;
//import com.rtsoju.dku_council_homepage.domain.user.repository.UserRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.annotation.PostConstruct;
//import java.util.Optional;
//
//@RestController
//@RequestMapping("/test")
//@RequiredArgsConstructor
//public class test {
//
//    private final PostRepository postRepository;
//    private final UserRepository userRepository;
//    private final CommentRepository commentRepository;
//
//    private final PetitionRepository petitionRepository;
//
//    @PostConstruct
//    public void init(){
//        for (int i=0; i<5; i++){
//            userRepository.save(new User("17011092"+i, "1234", "hwan"+i, Major.ARCHITECTURE, Register.GRADUATE, "1234"));
//            postRepository.save(new News("News"+i, "News"+i));
//            postRepository.save(new Announce("Announce"+i, "Announce"+i));
//            postRepository.save(new Conference("Conference"+i, "Conference"+i));
//            Optional<User> findUser = userRepository.findByClassId("17011092" + i);
//            postRepository.save(new Petition(findUser.get(), "Petition"+i, "hihi"+i, PetitionStatus.진행중));
//            Optional<Petition> findPetiton = petitionRepository.findByTitle("Petition"+i);
//            commentRepository.save(new Comment(findPetiton.get(), findUser.get(), "hi"));
//            postRepository.save(new Rule("Rule"+i, "Rule"+i));
//        }
//    }
//}
