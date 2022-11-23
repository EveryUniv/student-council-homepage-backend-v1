package com.rtsoju.dku_council_homepage.domain.event.service;

import com.rtsoju.dku_council_homepage.domain.event.model.dto.CharacterRequestDto;
import com.rtsoju.dku_council_homepage.domain.event.model.entity.Vote;
import com.rtsoju.dku_council_homepage.domain.event.model.enums.Character;
import com.rtsoju.dku_council_homepage.domain.event.repository.VoteRepository;
import com.rtsoju.dku_council_homepage.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class VoteService {
    private final VoteRepository voteRepository;

    public boolean isVote(long userId) {
        List<Vote> byUserId = voteRepository.findByUserId(userId);
        if(byUserId.isEmpty()) return false;
        return true;
    }

    @Transactional
    public void vote(long userId, CharacterRequestDto dto) {
        checkDuplicate(dto);
        if(isVote(userId)) throw new BadRequestException("이미 투표가 되었습니다 :)");
        createVote(userId, dto);
    }

    private void createVote(long userId, CharacterRequestDto dto) {
        if(dto.isFirst()) voteRepository.save(new Vote(userId, Character.first));
        if(dto.isSecond()) voteRepository.save(new Vote(userId, Character.second));
        if(dto.isThird()) voteRepository.save(new Vote(userId, Character.third));
        if(dto.isForth()) voteRepository.save(new Vote(userId, Character.fourth));
        if(dto.isFifth()) voteRepository.save(new Vote(userId, Character.fifth));
        if(dto.isSixth()) voteRepository.save(new Vote(userId, Character.sixth));
    }

    private void checkDuplicate(CharacterRequestDto dto) {
        int cnt = 0;
        if(dto.isFirst()) cnt += 1;
        if(dto.isSecond()) cnt += 1;
        if(dto.isThird()) cnt += 1;
        if(dto.isForth()) cnt += 1;
        if(dto.isFifth()) cnt += 1;
        if(dto.isSixth()) cnt += 1;
        if(cnt > 2 || cnt == 0) throw new BadRequestException("최대 2표까지 가능합니다!!");
    }


    public List<Character> whichVote(long userId) {
        List<Vote> byUserId = voteRepository.findByUserId(userId);
        return byUserId.stream().map(Vote::getCharacters).distinct().collect(Collectors.toList());
    }
}
