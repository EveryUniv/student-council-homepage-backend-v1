
package com.rtsoju.dku_council_homepage.domain.base;

public enum Major {
    KL(Department.LIBERAL, "국어국문학과"),
    HISTORY(Department.LIBERAL, "사학과"),
    PHILOSOPHY(Department.LIBERAL, "철학과"),
    ENGLISH(Department.LIBERAL, "영미인문학과"),
    LAW(Department.LAW, "법학과"),
    PSD(Department.SOCIAL, "정치외교학과"),
    ADMINISTRATION(Department.SOCIAL, "행정학과"),
    PROPERTY(Department.SOCIAL, "도시계획부동산학부"),
    COMMUNICATION(Department.SOCIAL, "커뮤니케이션학부"),
    COUNSELING(Department.SOCIAL, "상담학과"),
    ECONOMICS(Department.BM, "경제학과"),
    COMMERCE(Department.BM, "무역학과"),
    BUSINESS(Department.BM, "경영학과"),
    INDUSTRY(Department.BM, "산업경영학과"),
    INTERNATIONAL(Department.BM, "국제학부"),
    ELECTRON(Department.ENGINEERING, "전자전기공학부"),
    POLYMER(Department.ENGINEERING, "고분자시스템공학부"),
    CIVIL(Department.ENGINEERING, "토목환경공학과"),
    MECHANICAL(Department.ENGINEERING, "기계공학과"),
    CHEMICAL(Department.ENGINEERING, "화학공학과"),
    ARCHITECTURE(Department.ENGINEERING, "건축학부"),
    SOFTWARE(Department.SOFTWARE, "소프트웨어학과"),
    COMPUTER(Department.SOFTWARE, "컴퓨터공학과"),
    MOBILE(Department.SOFTWARE, "모바일시스템공학과"),
    STATISTICS(Department.SOFTWARE, "정보통계학과"),
    SECURITY(Department.SOFTWARE, "산업보안학과"),
    SC(Department.SOFTWARE, "SW융합학부"),
    CHINESE(Department.EDUCATION, "한문교육과"),
    SPECIAL(Department.EDUCATION, "특수교육과"),
    MATH(Department.EDUCATION, "수학교육과"),
    SCIENCE(Department.EDUCATION, "과학교육과"),
    PHYSICAL(Department.EDUCATION, "체육교육과"),
    TEACHING(Department.EDUCATION, "교직교육과"),
    POTTERY(Department.AM, "도예과"),
    DESIGN(Department.AM, "디자인학부"),
    FILM(Department.AM, "공연영화학부"),
    DANCE(Department.AM, "무용과"),
    MUSIC(Department.AM, "음악학부"),
    FREE(Department.LA, "자유교양대학"),
    ;

    private Department department;
    private String name;

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
}
