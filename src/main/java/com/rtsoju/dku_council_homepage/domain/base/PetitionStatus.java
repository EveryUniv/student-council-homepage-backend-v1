package com.rtsoju.dku_council_homepage.domain.base;

import com.rtsoju.dku_council_homepage.exception.BadRequestException;
import org.springframework.lang.Nullable;

public enum PetitionStatus {
    답변대기, 진행중, 답변완료, 기간만료;

    public static PetitionStatus lookup(String status){
        try{
            return PetitionStatus.valueOf(status);
        }catch (IllegalArgumentException e){
            throw new BadRequestException("답변대기, 진행중, 답변완료, 기간만료만 가능합니다.");
        }catch (NullPointerException e){
            throw new BadRequestException("답변대기, 진행중, 답변완료, 기간만료만 가능합니다.");
        }
    }
}
