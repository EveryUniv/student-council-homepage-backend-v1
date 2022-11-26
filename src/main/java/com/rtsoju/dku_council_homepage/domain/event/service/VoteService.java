package com.rtsoju.dku_council_homepage.domain.event.service;

import com.rtsoju.dku_council_homepage.domain.event.model.dto.CharacterRequestDto;
import com.rtsoju.dku_council_homepage.domain.event.model.entity.Vote;
import com.rtsoju.dku_council_homepage.domain.event.model.enums.Character;
import com.rtsoju.dku_council_homepage.domain.event.repository.VoteRepository;
import com.rtsoju.dku_council_homepage.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
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
        checkValid(dto);
        if(isVote(userId)) throw new BadRequestException("이미 투표가 되었습니다 :)");
        createVote(userId, dto);
    }

    private void createVote(long userId, CharacterRequestDto dto) {
        List<Boolean> checkList = dto.getCheckList();
        for(int i=0; i< checkList.size(); i++){
            Boolean check = checkList.get(i);
            if(check){
                voteRepository.save(new Vote(userId, Character.values()[i]));
            }
        }
    }

    private void checkValid(CharacterRequestDto dto) {
        //길이 검사
        List<Boolean> checkList = dto.getCheckList();
        if(checkList.size() != 6) throw new BadRequestException("6가지 응답이 필수입니다.");
        //중복 검사
        long count = checkList.stream().filter(data -> data.booleanValue()).count();
        if(count > 2 || count == 0) throw new BadRequestException("최대 2표까지 가능합니다!");
    }


    public Boolean[] whichVote(long userId) {
        Boolean[] ret = new Boolean[6];
        Arrays.fill(ret, false);
        List<Vote> byUserId = voteRepository.findByUserId(userId);
        List<Character> collect = byUserId.stream().map(Vote::getCharacters).distinct().collect(Collectors.toList());
        for(Character character : collect){
            int index = character.ordinal();
            ret[index] = true;
        }
        return ret;
    }
}
