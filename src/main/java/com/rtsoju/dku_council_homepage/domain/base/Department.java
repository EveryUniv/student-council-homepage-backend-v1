package com.rtsoju.dku_council_homepage.domain.base;

public enum Department {

    LIBERAL("문과대학"),
    LAW("법과대학"),
    SOCIAL("사회과학대학"),
    BM("경영경제대학"),
    ENGINEERING("공과대학"),
    SOFTWARE("SW융합대학"),
    EDUCATION("사범대학"),
    AM("음악예술대학"),
    LA("자유교양대학"),
    ;

    private String name;

    Department(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

