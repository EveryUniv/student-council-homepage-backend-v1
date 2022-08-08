package com.rtsoju.dku_council_homepage.common;

import com.rtsoju.dku_council_homepage.domain.base.PetitionStatus;
import com.rtsoju.dku_council_homepage.domain.post.repository.PetitionRepository;
import com.rtsoju.dku_council_homepage.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Component
@Slf4j
@RequiredArgsConstructor
@Transactional
public class SchedulePetition {
    private final PetitionRepository petitionRepository;
    private final UserRepository userRepository;
    @Scheduled(cron = "0 0 0 * * *")
    public void updatePetitionStatus(){
        log.info("Petition상태 없데이트");
        petitionRepository.bulkStatusChange(PetitionStatus.기간만료, LocalDateTime.now().minusDays(15), PetitionStatus.진행중);
        log.info("Petition Create 초기화");
        userRepository.bulkPetitionCreateReset();
    }
}
