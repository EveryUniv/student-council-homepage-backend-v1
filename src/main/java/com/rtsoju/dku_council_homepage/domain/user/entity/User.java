package com.rtsoju.dku_council_homepage.domain.user.entity;

import com.rtsoju.dku_council_homepage.domain.base.*;
import com.rtsoju.dku_council_homepage.domain.like.entity.Like;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@DynamicUpdate //회원 값 변경시.. 우리는 청원등록 때문에 필요
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString
public class User extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    @Column(length = 10, nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    private Department department;

    @Enumerated(EnumType.STRING)
    private Major major;

    @Column(name = "phone_number")
    private String phone;

    @Enumerated(EnumType.STRING)
    private Register register;

    @Column(name = "last_petition_time")
    private LocalDateTime petitionTime;

    //권한 들어가야함.

    //연관관계 매핑
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Post> postList = new ArrayList<>();
}

