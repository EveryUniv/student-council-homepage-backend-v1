package com.rtsoju.dku_council_homepage.domain.post.entity;

import com.rtsoju.dku_council_homepage.domain.base.BaseEntity;
import com.rtsoju.dku_council_homepage.domain.page.dto.PostSummary;
import com.rtsoju.dku_council_homepage.domain.user.model.entity.User;
import lombok.*;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.*;

@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type")
@Getter
@DynamicUpdate //게시글 수정시..
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString
@Entity
public class Post extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "post_id")
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column
    private String title;

    @Column
    @Lob
    private String text;

    private String fileUrl;

    //중복허용하면 fetch join 동시에 못날림 + 어차피 고유값들 들어가서 중복된 값이 들어갈 일도 없음..
    //나중에 필요하면 값비교 overrite할 예정.
    @OneToMany(mappedBy = "post")
    Set<Comment> comments = new HashSet<>();

    @OneToMany(mappedBy = "post")
    Set<PostHit> postHits = new HashSet<>();



    public Post(String title, String text){
        this.title = title;
        this.text = text;
    }

    public Post(User user, String title, String text){
        this.user = user;
        this.title = title;
        this.text = text;
    }

//    public Post(User user, String title, String fileUrl){
//        this.user = user;
//        this.title = title;
//        this.fileUrl = fileUrl;
//    }

    public Post(User user, String title, String text, String fileUrl) {
        this.user = user;
        this.title = title;
        this.text = text;
        this.fileUrl = fileUrl;
    }


    public PostSummary summarize(){
        return new PostSummary(id, title);
    }
}

