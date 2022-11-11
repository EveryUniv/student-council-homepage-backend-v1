//package com.rtsoju.dku_council_homepage.domain.user.model.entity;
//
//import com.rtsoju.dku_council_homepage.domain.user.repository.UserRepository;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.time.LocalDateTime;
//import java.time.temporal.ChronoUnit;
//import java.time.temporal.TemporalUnit;
//
////@SpringBootTest
//class UserTest {
//
//    @Autowired
//    private UserRepository userRepository;
//
//
//    @Test
//    @Transactional
//    void test(){
//        User user = userRepository.findById(3L).get();
//        boolean admin = user.isAdmin();
//        System.out.println("admin = " + !admin);
//    }
//
//    @Test
//    void test1(){
//        LocalDateTime date1 = LocalDateTime.now();
//        LocalDateTime date2 = LocalDateTime.now().minusMinutes(3L);
//        boolean after = date1.isAfter(date2);
//        System.out.println("after = " + after);
//    }
//
//}