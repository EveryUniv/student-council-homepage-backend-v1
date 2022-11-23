package com.rtsoju.dku_council_homepage.domain.event.model.entity;

import com.rtsoju.dku_council_homepage.domain.base.BaseEntity;
import com.rtsoju.dku_council_homepage.domain.event.model.dto.CharacterRequestDto;
import com.rtsoju.dku_council_homepage.domain.event.model.enums.Character;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


/**
 * 잠깐 사용하고 버릴 테이블임.
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Vote extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "vote_id")
    private Long id;

    private Long userId;

    @Enumerated(EnumType.STRING)
    private Character characters;

    public Vote(Long userId, Character type){
        this.userId = userId;
        this.characters = type;
    }
}
