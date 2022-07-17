package com.rtsoju.dku_council_homepage.domain.user.model.entity;

import com.rtsoju.dku_council_homepage.domain.base.*;
import com.rtsoju.dku_council_homepage.domain.post.entity.Post;
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
@Table(name = "RTUser")
public class User extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    @Column(name = "class_id")
    private String classId;

    private String password;

    @Column(length = 10, nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    private Major major;
    @Column(name = "phone_number")
    private String phone;

    @Enumerated(EnumType.STRING)
    private Register register;

    @Column(name = "last_petition_time")
    private LocalDateTime petitionTime;

    @Column(name = "email_verify")
    private boolean emailVerified;

    //권한 들어가야함.
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserRole> roles = new ArrayList<>();


    //연관관계 매핑
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Post> postList = new ArrayList<>();

    public void emailVerifiedSuccess() {
        this.emailVerified = true;
    }

//    // 2022-07-09 임시 사용 예정
//    public User(String classId, String password, String name, String phone) {
//        this.classId = classId;
//        this.password = password;
//        this.name = name;
//        this.phone = phone;
//    }

    public User(String classId, String password, String name, Major major, Register register, String phone) {
        this.classId = classId;
        this.password = password;
        this.name = name;
        this.major = major;
        this.register = register;
        this.phone = phone;
        this.emailVerified = false;
    }


    public void allocateRole(String role) {
        UserRole userRole = new UserRole(this, role);
        roles.add(userRole);
        return;
    }
}

