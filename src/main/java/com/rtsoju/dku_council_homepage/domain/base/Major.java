
package com.rtsoju.dku_council_homepage.domain.base;

import java.util.Arrays;
import java.util.Optional;

public enum Major {
    KOREAN_LITNLANG(Department.LIBERAL, "국어국문학과"),
    HISTORY(Department.LIBERAL, "사학과"),
    PHILOSOPHY(Department.LIBERAL, "철학과"),
    ENGLISH_HUMANITIES(Department.LIBERAL, "영미인문학과"),
    LAW(Department.LAW, "법학과"),
    POLITICAL(Department.SOCIAL, "정치외교학과"),
    ADMINISTRATION(Department.SOCIAL, "행정학과"),
    SOCIETY(Department.SOCIAL, "도시계획부동산학부"),
    COMMUNICATION(Department.SOCIAL, "커뮤니케이션학부"),
    COUNSELING(Department.SOCIAL, "상담학과"),
    ECONOMICS(Department.BUSINESS_ECONOMICS, "경제학과"),
    COMMERCE(Department.BUSINESS_ECONOMICS, "무역학과"),
    BUSINESS(Department.BUSINESS_ECONOMICS, "경영학부"),
    INDUSTRY(Department.BUSINESS_ECONOMICS, "산업경영학과"),
    INTERNATIONAL(Department.BUSINESS_ECONOMICS, "국제학부"),
    ELECTRON(Department.ENGINEERING, "전자전기공학부"),
    POLYMER(Department.ENGINEERING, "고분자시스템공학부"),
    CIVIL(Department.ENGINEERING, "토목환경공학과"),
    MECHANICAL(Department.ENGINEERING, "기계공학과"),
    CHEMISTRY(Department.ENGINEERING, "화학공학과"),
    ARCHITECTURE(Department.ENGINEERING, "건축학부"),
    SOFTWARE(Department.SOFTWARE, "소프트웨어학과"),
    COMPUTER_SCIENCE(Department.SOFTWARE, "컴퓨터공학과"),
    MOBILE_SYSTEM(Department.SOFTWARE, "모바일시스템공학과"),
    STATISTICS(Department.SOFTWARE, "정보통계학과"),
    SECURITY(Department.SOFTWARE, "산업보안학과"),
    SOFTWARE_CONVERGENCE(Department.SOFTWARE, "SW융합학부"),
    EDU_CHINESE(Department.EDUCATION, "한문교육과"),
    EDU_SPECIAL(Department.EDUCATION, "특수교육과"),
    EDU_MATHEMATICS(Department.EDUCATION, "수학교육과"),
    EDU_SCIENCE(Department.EDUCATION, "과학교육과"),
    EDU_PHYSICAL(Department.EDUCATION, "체육교육과"),
    EDU_TEACHING(Department.EDUCATION, "교직교육과"),
    POTTERY(Department.MUSIC_ART, "도예과"),
    DESIGN(Department.MUSIC_ART, "디자인학부"),
    FILM(Department.MUSIC_ART, "공연영화학부"),
    DANCE(Department.MUSIC_ART, "무용과"),
    MUSIC(Department.MUSIC_ART, "음악학부"),
    LIBERAL_ARTS(Department.LIBERAL_ARTS, "자유교양대학"),
    ADMIN(Department.SOFTWARE, "총학생회"),
    ;

    private final Department department;
    private final String name;

    Major(Department department, String name) {
        this.department = department;
        this.name = name;
    }

    public Department getDepartment() {
        return department;
    }

    public String getName() {
        return name;
    }

    public boolean isAdmin() { return this == ADMIN; }

    /**
     * name으로 Major찾기.
     * 학과 이름별로 이미 unique하므로, department까지 확인해서 찾을 필요 없어보임
     */
    public Optional<Major> of(String name) {
        return Arrays.stream(Major.values())
                .filter((p) -> p.name.equals(name))
                .findFirst();
    }
}
