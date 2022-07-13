package com.rtsoju.dku_council_homepage.domain.base;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Register {
    IN("재학"),
    REST("휴학"),
    GRADUATE("졸업"),
    ;

    private String name;

}
