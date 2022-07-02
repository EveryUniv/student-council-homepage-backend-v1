package com.rtsoju.dku_council_homepage.domain.post.entity;

import com.rtsoju.dku_council_homepage.domain.base.BaseEntity;
import com.rtsoju.dku_council_homepage.domain.base.Department;
import com.rtsoju.dku_council_homepage.domain.base.Major;
import com.rtsoju.dku_council_homepage.domain.base.Register;
import com.rtsoju.dku_council_homepage.domain.user.entity.USER;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@DynamicUpdate //게시글 수정시..
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString
public class POST extends BaseEntity {
    @Id @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    @OneToMany(mappedBy = )
    private USER


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

}
