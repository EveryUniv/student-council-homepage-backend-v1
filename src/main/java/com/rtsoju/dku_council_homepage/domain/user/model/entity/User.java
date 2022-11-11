package com.rtsoju.dku_council_homepage.domain.user.model.entity;

import com.rtsoju.dku_council_homepage.domain.base.*;
import com.rtsoju.dku_council_homepage.domain.post.entity.Post;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    @Column(name = "petition_create")
    private boolean petitionCreate;

    //초기값 해당 일의 00:00:00으로 저장. -> Critical한 Issue는 아니기에 이대로 사용한다.
    private LocalDateTime suggestionCreate;

    //권한 들어가야함.
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserRole> roles = new ArrayList<>();


    //연관관계 매핑
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Post> postList = new ArrayList<>();

    public void createPetition() {
        this.petitionCreate = true;
    }

    public void createSuggestion(){
        this.suggestionCreate = LocalDateTime.now();
    }

    public User(String classId, String password, String name, Major major, Register register, String phone) {
        this.classId = classId;
        this.password = password;
        this.name = name;
        this.major = major;
        this.register = register;
        this.phone = phone;
    }


    public void allocateRole(String role) {
        UserRole userRole = new UserRole(this, role);
        roles.add(userRole);
        return;
    }

    public void changePassword(String pw){
        this.password = pw;
        return;
    }
    public boolean isAdmin(){
        List<UserRole> roles = this.getRoles();
        List<String> collect = roles.stream().map(role -> role.getRole()).collect(Collectors.toList());
        if(collect.contains("ROLE_ADMIN")){
            return true;
        }
        return false;
    }
}

