package com.rtsoju.dku_council_homepage.domain.event.model.dto;

import com.rtsoju.dku_council_homepage.domain.event.model.enums.Character;
import lombok.Data;

import java.util.List;

@Data
public class CharacterResponseDto {
    private boolean isVote;
    private List<Character> selected;
}
