package com.rtsoju.dku_council_homepage.domain.post.entity;

import com.rtsoju.dku_council_homepage.domain.base.BaseEntity;
import com.rtsoju.dku_council_homepage.domain.page.dto.PostSummary;
import com.rtsoju.dku_council_homepage.domain.post.entity.dto.request.RequestPostDto;
import com.rtsoju.dku_council_homepage.domain.post.entity.dto.request.RequestRuleDto;
import com.rtsoju.dku_council_homepage.domain.post.entity.dto.response.FileUrlWithNameDto;
import com.rtsoju.dku_council_homepage.domain.user.model.entity.User;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    List<PostFile> fileList = new ArrayList<>();

    //중복허용하면 fetch join 동시에 못날림 + 어차피 고유값들 들어가서 중복된 값이 들어갈 일도 없음..
    //나중에 필요하면 값비교 overrite할 예정. id값으로 hash처리해서 중복될 일이 없음.
    //댓글이랑 post는 완전 종속적 관계이기에 삭제시 댓글까지 모두 삭제..
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Comment> comments = new ArrayList<>();

    private int hitCount;

    public Post(String title) {
        this.title = title;
    }

    public Post(String title, String text) {
        this.title = title;
        this.text = text;
    }

    public Post(User user, String title, String text) {
        this.user = user;
        this.title = title;
        this.text = text;
    }


    public Post(User user, RequestPostDto data, ArrayList<PostFile> files) {
        this.user = user;
        this.title = data.getTitle();
        this.text = data.getText();
        for (PostFile postFile : files) {
            postFile.putPost(this);
        }
        this.fileList = files;
    }

    public Post(User user, RequestRuleDto data, ArrayList<PostFile> files) {
        this.user = user;
        this.title = data.getTitle();
        this.text = data.getText();
        for (PostFile postFile : files) {
            postFile.putPost(this);
        }
        this.fileList = files;
    }


    public PostSummary summarize() {
        return new PostSummary(id, title);
    }

    public void putFiles(List<PostFile> fileList) {
        // 파일 리스트에 추가해준다..
        for (PostFile postFile : fileList) {
            postFile.putPost(this);
        }
        this.fileList = fileList;
    }

    public void putUser(User user) {
        this.user = user;
        //유저를 세팅하면서 해당 유저가 작성했던 리스트들을 모두 가져온다.
        List<Post> postList = user.getPostList();
        //유저의 포스트 리스트에 해당 포스트도 추가한다..
        postList.add(this);
    }

    public List<FileUrlWithNameDto> getFiles(){
        return this.getFileList()
                .stream().map(FileUrlWithNameDto::new)
                .collect(Collectors.toList());
    }

    public void plusHits() {
        this.hitCount++;
    }

}

